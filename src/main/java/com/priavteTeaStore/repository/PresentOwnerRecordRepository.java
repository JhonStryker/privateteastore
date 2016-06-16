package com.priavteTeaStore.repository;

import com.priavteTeaStore.domain.PresentOwnerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Created by Thoughtworks on 16/6/9.
 */
@Transactional
@Repository
public interface PresentOwnerRecordRepository extends JpaRepository<PresentOwnerRecord, Long> {
    PresentOwnerRecord findByOwnerId(Long ownerId);

    PresentOwnerRecord findByOwnerIdAndPresentId(Long ownerId, Long presentId);
}
