package com.priavteTeaStore.repository;

import com.priavteTeaStore.domain.TeaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Repository
@Transactional
public interface TeaProductRepository extends JpaRepository<TeaProduct, Long> {
    TeaProduct findById(Long id);
}
