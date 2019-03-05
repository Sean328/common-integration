package com.ironass.concurrent.threadlocal.sample1.domain;

import com.xxl.integration.base.BaseDomain;

/**
 * @author lixin
 * @date 2019-03-04 17:48
 **/
public class ClassRoom extends BaseDomain {

    private static final long serialVersionUID = -1842684623243219092L;
    private String roomId = "";
    private String roomName = "";

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
