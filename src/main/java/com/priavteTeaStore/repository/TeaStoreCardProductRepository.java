package com.priavteTeaStore.repository;

import com.priavteTeaStore.domain.TeaCategory;
import com.priavteTeaStore.domain.TeaStoreCardProduct;
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
public interface TeaStoreCardProductRepository extends JpaRepository<TeaStoreCardProduct, Long> {
    TeaStoreCardProduct findById(Long productId);

    Page<TeaStoreCardProduct> findAll(Pageable pageRequest);

    Page<TeaStoreCardProduct> findByTeaStoreTeaCategory(TeaCategory teaCategory, Pageable pageRequest);
}
