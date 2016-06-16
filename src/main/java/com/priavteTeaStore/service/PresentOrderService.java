package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.PresentOrder;
import com.priavteTeaStore.domain.ProductType;
import com.priavteTeaStore.domain.User;
import com.priavteTeaStore.domain.inter.Present;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.repository.PresentOrderRepository;
import com.priavteTeaStore.util.ERR;
import com.priavteTeaStore.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */

/**
 * 赠送订单服务
 */
@Service
@Transactional
public class PresentOrderService {

    @Autowired
    PresentOrderRepository presentOrderRepository;

    @Autowired
    PresentManger presentManger;

    /**
     * 生成订单，并处理并调用present接口处理订单与礼品的关系
     * @param present
     * @param user
     * @return
     */
    public PresentOrder sendPresent(Present present, User user, ProductType type) {
        PresentOrder order = createNewOrder(present, user, type);
        present.send(order);
        return order;
    }

    /**
     * 赠送订单被其他用户领取
     *
     * @param order
     * @param user
     */
    public Present receiveBy(PresentOrder order, User user) {
        Present present = presentManger.getPresent(order.getPresentType(), order.getProductId());
        if (present.receiveBy(user, order) == OperationResult.failure) {
            throw new ToClientException(ERR.presentOrder_is_outOfDate);
        }
        return present;
    }

    /**
     * 生成订单
     *
     * @param present
     * @param user
     * @return
     */
    private PresentOrder createNewOrder(Present present, User user, ProductType type) {
        PresentOrder order = new PresentOrder(present.getId(), type, user.getId());
        presentOrderRepository.save(order);
        return order;
    }
}
