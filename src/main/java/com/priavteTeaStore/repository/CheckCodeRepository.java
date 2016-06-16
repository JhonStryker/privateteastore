package com.priavteTeaStore.repository;

import com.priavteTeaStore.domain.CheckCode;
import com.priavteTeaStore.domain.CheckCodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Transactional
@Repository
public interface CheckCodeRepository extends JpaRepository<CheckCode, Long> {

    default CheckCode getCheckCode(String phoneNum, CheckCodeType type) {
        return findByPhoneNumAndCheckType(phoneNum, type);
    }

    CheckCode findByPhoneNumAndCheckType(String phoneNum, CheckCodeType type);
}
