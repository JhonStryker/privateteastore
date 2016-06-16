package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.AccessInfo;
import com.priavteTeaStore.domain.HAuthAccess;
import com.priavteTeaStore.domain.User;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.msg.base.ClientRequestMsg;
import com.priavteTeaStore.repository.HAuthAccesserRepository;
import com.priavteTeaStore.repository.UserRepository;
import com.priavteTeaStore.util.ERR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Thoughtworks on 16/6/14.
 */
@Service
public class CheckAccessRightService {

    /**
     * Todo:客户端需要保存这两个值
     */
    static final String app_key = "111";
    static final String app_secret = "222";

    @Autowired
    UserRepository userRepository;

    @Autowired
    HAuthAccesserRepository hAuthAccesserRepository;

    private User getUser(String phoneNum){
        User user = userRepository.findByName(phoneNum);
        if (user == null){
            throw new ToClientException(ERR.no_such_user);
        }
        return user;
    }

    public boolean check(AccessInfo accessInfo,ClientRequestMsg.CheckType checkType){
        if (checkType == ClientRequestMsg.CheckType.check_right_for_access_token){
            return check_right_for_accessToken(accessInfo.getPhone_num(), accessInfo.getApp_key(), accessInfo.getSignature());
        }else if (checkType == ClientRequestMsg.CheckType.check_right_for_md5_token){
            return check_right_for_md5(accessInfo.getApp_key(), accessInfo.getSignature());
        }else{
            return check_right_for_userInfo(accessInfo.getApp_key(), accessInfo.getSignature(), accessInfo.getAccess_token());
        }
    }

    private boolean check_right_for_accessToken(String phoneNum, String app_key, String signature){
        if (app_key == null || signature == null || phoneNum == null){
            throw new ToClientException(ERR.no_enough_acess_info);
        }
        User user = getUser(phoneNum);
        String trueSignature = MD5.encryptMD5(getSecret(app_key) + user.getPassword());
        return signature.equalsIgnoreCase(trueSignature)?Boolean.valueOf(true):Boolean.valueOf(false);
    }


    private boolean check_right_for_md5(String app_key, String signature){
        if (app_key == null || signature == null){
            throw new ToClientException(ERR.no_enough_acess_info);
        }
        return getSecret(app_key).equals(signature);
    }

    private boolean check_right_for_userInfo(String app_key, String signature, String access_token){
        if (app_key == null || signature == null || access_token == null){
            throw new ToClientException(ERR.no_enough_acess_info);
        }
        HAuthAccess access = hAuthAccesserRepository.findByToken(access_token);
        if (access == null){
            throw new ToClientException(ERR.has_no_right);
        }
        String trueSignature = MD5.encryptMD5(getSecret(app_key) + "&" + access.getSecret());
        return trueSignature.equalsIgnoreCase(signature)?Boolean.valueOf(true):Boolean.valueOf(false);
    }

    private String getSecret(String app_key) {
        if (app_key.equals(CheckAccessRightService.app_key)){
            return CheckAccessRightService.app_secret;
        }
        throw new ToClientException(ERR.has_no_right);
    }

}
