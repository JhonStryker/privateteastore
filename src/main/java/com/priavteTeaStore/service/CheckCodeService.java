package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.CheckCode;
import com.priavteTeaStore.domain.CheckCodeType;
import com.priavteTeaStore.repository.CheckCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class CheckCodeService {


    @Autowired
    CheckCodeRepository checkCodeRepository;

    /**
     * 请求生成验证码
     *
     * @param phoneNum
     * @param type
     * @return
     */
    public String getCheckCode(String phoneNum, CheckCodeType type) {
        CheckCode checkCode = checkCodeRepository.getCheckCode(phoneNum, type);
        if (checkCode != null) {
            checkCode.update();
        } else {
            checkCode = new CheckCode(phoneNum, type);
        }
        checkCodeRepository.save(checkCode);
        return checkCode.getContent();
    }

    /**
     * 判断验证码是否有效
     *
     * @param phoneNum
     * @param checkCodeContent
     * @param type
     * @return
     */
    public boolean checkCheckCode(String phoneNum, CheckCodeType type, String checkCodeContent) {
        CheckCode checkCode = checkCodeRepository.getCheckCode(phoneNum, type);
        if (checkCode != null && checkCode.validCheckCode(checkCodeContent)) {
            checkCode.useCheckCode();
            return true;
        }
        return false;
    }
}

