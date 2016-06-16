package com.priavteTeaStore.repository;

import com.priavteTeaStore.domain.TeaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Transactional
@Repository
public interface TeaCategoryRepository extends JpaRepository<TeaCategory, Long> {
    TeaCategory findByName(String name);

    TeaCategory findById(Long id);
}
