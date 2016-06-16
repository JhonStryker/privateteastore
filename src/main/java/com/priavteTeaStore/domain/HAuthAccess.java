package com.priavteTeaStore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class HAuthAccess {

    @Id
    @GeneratedValue
    Long id;
    String app_key;

    @ManyToOne
    User user;
    Date createTime;
    private String token;
    private String secret;

    public HAuthAccess(User user, String app_key) {
        this.user = user;
        this.app_key = app_key;
        this.token = creatRandomUUID();
        this.secret = creatRandomUUID();
        this.createTime = new Date();
    }

    public HAuthAccess() {
    }

    private String creatRandomUUID() {
        return (UUID.randomUUID().toString().replaceAll("-", ""));
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public String getSecret() {
        return secret;
    }
}
