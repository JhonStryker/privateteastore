package com.priavteTeaStore.repository;

import com.priavteTeaStore.domain.TradeOrder;
import com.priavteTeaStore.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Transactional
@Repository
public interface TradeOrderRepository extends JpaRepository<TradeOrder, Long> {
    TradeOrder findByOrderId(Long orderId);

    //根据用户名和订单状态查询订单列表，注意第三个参数是Pageable
    Page<TradeOrder> findByUserIdAndStatus(User user, Integer status, Pageable pageRequest);

    Page<TradeOrder> findByUserId(User user, Pageable pageRequest);
}
