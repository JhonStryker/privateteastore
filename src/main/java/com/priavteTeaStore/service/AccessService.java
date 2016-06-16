package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.AccessInfo;
import com.priavteTeaStore.domain.User;
import com.priavteTeaStore.repository.HAuthAccesserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
public class AccessService {

    @Autowired
    HAuthAccesserRepository hAuthAccesserRepository;

    public User getUser(AccessInfo accessInfo) {
        return hAuthAccesserRepository.findUserByToken(accessInfo.getAccess_token());
    }

}
