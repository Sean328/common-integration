package com.ironass.concurrent.threadlocal.sample1.domain;

import com.ironass.base.BaseDomain;

/**
 * @author lixin
 * @date 2019-03-04 17:47
 **/
public class Student extends BaseDomain {

    private static final long serialVersionUID = -2354764376769759320L;

    private String sNo = "";
    private String sName = "";
    private String sAge = "";
    private String gender = "";
    private String sbirth = "";
    private String classRoomId = "";
    private String classRoomName = "";

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAge() {
        return sAge;
    }

    public void setsAge(String sAge) {
        this.sAge = sAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getSbirth() {
        return sbirth;
    }

    public void setSbirth(String sbirth) {
        this.sbirth = sbirth;
    }
}
