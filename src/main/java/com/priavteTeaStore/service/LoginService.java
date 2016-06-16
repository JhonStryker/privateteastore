package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.AccessToken;
import com.priavteTeaStore.domain.HAuthAccess;
import com.priavteTeaStore.domain.User;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.repository.HAuthAccesserRepository;
import com.priavteTeaStore.repository.UserRepository;
import com.priavteTeaStore.util.ERR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HAuthAccesserRepository hAuthAccesserRepository;

    public AccessToken getLoginAccessToken(String userName, String app_key) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new ToClientException(ERR.no_such_user);
        }
        HAuthAccess authAccess = createHAuthAccess(app_key, user);
        return new AccessToken(authAccess.getToken(), authAccess.getSecret());
    }

    public HAuthAccess createHAuthAccess(String app_key, User user) {
        HAuthAccess authAccess = new HAuthAccess(user, app_key);
        hAuthAccesserRepository.save(authAccess);
        return authAccess;
    }
}
