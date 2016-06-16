package com.priavteTeaStore.domain;

import com.priavteTeaStore.util.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class CheckCode {
    @Id
    @GeneratedValue
    Long id;
    String checkCodeContent;
    Time createTime;
    Time useTime;
    String phoneNum;

    CheckCodeType checkType;

    public CheckCode() {
    }

    public CheckCode(String phoneNum, CheckCodeType checkCodeType) {
        this.checkType = checkCodeType;
        this.phoneNum = phoneNum;
        this.checkCodeContent = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        this.createTime = TimeUtil.getCurrentTime();
    }

    public void useCheckCode() {
        useTime = TimeUtil.getCurrentTime();
    }

    boolean hasUserd() {
        return useTime != null;
    }

    /**
     * 没有使用过，并且在有效期内，并且验证码正确
     *
     * @return
     */
    public boolean validCheckCode(String checkCodeContent) {
        return !hasUserd() && !isOutOfDate(TimeUtil.getCurrentTime()) && this.checkCodeContent.equals(checkCodeContent);
    }

    private boolean isOutOfDate(Time currentTime) {
        //Todo:有效期多久?
        return false;
    }

    public String getContent() {
        return checkCodeContent;
    }

    public void update() {
        this.checkCodeContent = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        this.createTime = TimeUtil.getCurrentTime();
        this.useTime = null;
    }
}
