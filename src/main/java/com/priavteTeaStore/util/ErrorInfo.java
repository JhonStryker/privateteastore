package com.priavteTeaStore.util;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class ErrorInfo {
    public final String reqUrl;
    public final String errors;

    public ErrorInfo(StringBuffer requestURL, String errorInfo) {
        this.reqUrl = requestURL.toString();
        this.errors = errorInfo;
    }
}
