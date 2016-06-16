package com.priavteTeaStore.repository;

import com.priavteTeaStore.domain.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Transactional
@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

}
