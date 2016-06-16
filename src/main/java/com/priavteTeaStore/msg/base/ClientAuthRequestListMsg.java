package com.priavteTeaStore.msg.base;

import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;

/**
 * Created by Thoughtworks on 16/6/11.
 */
public abstract class ClientAuthRequestListMsg extends ClientAuthRequestMsg {

    @NotNull
    Integer size;
    @NotNull
    Integer pageNo;

    public ClientAuthRequestListMsg() {
    }

    public ClientAuthRequestListMsg(Integer size, Integer pageNo) {
        this.size = size;
        this.pageNo = pageNo;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public PageRequest getPageRequest() {
        return new PageRequest(pageNo, size);
    }

}
