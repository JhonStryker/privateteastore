package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.PresentOwnerRecord;
import com.priavteTeaStore.repository.PresentOwnerRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class PresentOwnerRecordService {
    @Autowired
    PresentOwnerRecordRepository presentOwnerRecordRepository;

    public List<PresentOwnerRecord> find(Long ownerId, Long presentId) {
        List<PresentOwnerRecord> records = new ArrayList<>();
        records.add(presentOwnerRecordRepository.findByOwnerIdAndPresentId(ownerId, presentId));
        return records;
    }

    public PresentOwnerRecord add(PresentOwnerRecord record) {
        return presentOwnerRecordRepository.save(record);
    }
}
