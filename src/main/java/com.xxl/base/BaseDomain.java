package com.xxl.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author lixin
 * @date 2019-01-17 18:08
 **/
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 3671828524261446730L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
