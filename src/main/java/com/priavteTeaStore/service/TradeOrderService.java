package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.*;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.msg.ClientTradeOrder;
import com.priavteTeaStore.msg.CreateTradeOrderMsg;
import com.priavteTeaStore.msg.ProductInfo;
import com.priavteTeaStore.repository.TeaStoreCardProductRepository;
import com.priavteTeaStore.repository.TeaStoreCardRepository;
import com.priavteTeaStore.repository.TradeOrderRepository;
import com.priavteTeaStore.repository.UserRepository;
import com.priavteTeaStore.util.ERR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Thoughtworks on 16/6/9.
 */

/**
 * 交易订单服务
 */
@Service
@Transactional
public class TradeOrderService {

    private final static Logger logger = LoggerFactory.getLogger(TradeOrderService.class);
    @Autowired
    TradeOrderRepository tradeOrderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeaStoreCardProductRepository teaStoreCardProductRepository;

    @Autowired
    TeaStoreCardRepository teaStoreCardRepository;

    /**
     * 生成新的订单
     * @param user
     * @param msg
     */
    public TradeOrder createAndSaveTradeOrder(User user, CreateTradeOrderMsg msg) {
        TradeOrder order = createOrder(msg, user);
        return tradeOrderRepository.save(order);
    }


    private TradeOrder createOrder(CreateTradeOrderMsg msg, User user) {
        TradeOrder order = new TradeOrder(user, PayType.get(msg.getPayType()), msg.getTotalPrice(), msg.getAddressId());
        addOrderItemsToOrder(order, msg.getProductList());
        return order;
    }


    public void addOrderItemsToOrder(TradeOrder order, List<ProductInfo> productInfos) {
        for (ProductInfo info : productInfos) {
            TeaStoreCardProduct card = teaStoreCardProductRepository.findById(info.getProductId());
            if (card != null) {
                order.addOrderItem(new OrderItem(card, info.getTotalCount()));
            } else {
                logger.warn("can not find the cardProduct id = {}", info.getProductId());
            }
        }
    }

    /**
     * 订单支付成功，完成交易订单
     *
     * @param orderId
     */
    public void confirmOrder(Long orderId) {
        TradeOrder order = tradeOrderRepository.findByOrderId(orderId);
        if (order == null) {
            throw new ToClientException(ERR.no_such_order, orderId.toString());
        }
        order.finish();
        addProductToOwner(order);
    }

    /**
     * 将交易订单中的物品加入到购买者列表中
     *
     * @param order
     */
    //Todo:要注意一致性原则：当传入Entity的是对象，如果设置到Entity中不是实体，而是id的时候，需要保证传入的对象是持久化的(id不为空)
    private void addProductToOwner(TradeOrder order) {
        List<TeaStoreCardProduct> productList = order.getTeaStoreCardProductList();
        for (TeaStoreCardProduct product : productList) {
            TeaStoreCard card = teaStoreCardRepository.save(new TeaStoreCard(product));
            order.getUser().addPresentToOwner(card);
        }
    }

    /**
     * 列出订单列表
     * @param pageRequest
     * @param user
     * @param status
     * @return
     */
    public Page<ClientTradeOrder> listTradeOrders(PageRequest pageRequest, User user, Integer status) {
        //查询全部
        if (status.equals(TradeOrder.OrderStatus.invalid_status)) {
            return tradeOrderRepository.findByUserId(user, pageRequest).map(ClientTradeOrder::convert);
        } else {
            return tradeOrderRepository.findByUserIdAndStatus(user, status, pageRequest).map(ClientTradeOrder::convert);
        }
    }

}
