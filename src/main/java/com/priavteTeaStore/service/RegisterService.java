package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.CheckCodeType;
import com.priavteTeaStore.domain.User;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.repository.UserRepository;
import com.priavteTeaStore.util.ERR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class RegisterService {
    private final static Logger logger = LoggerFactory.getLogger(RegisterService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    CheckCodeService checkCodeService;

    public User regist(String check_code, String md5_key, String password, String phone_num) {
        User user = userRepository.findByName(phone_num);
        if (user != null) {
            logger.debug("check_code = {}, phone = {}", check_code, phone_num);
            throw new ToClientException(ERR.user_already_regist);
        }
        if (!checkCodeService.checkCheckCode(phone_num, CheckCodeType.Register, check_code)) {
            throw new ToClientException(ERR.invalid_check_code);
        }
        return userRepository.save(new User(md5_key, password, phone_num));
    }
}
