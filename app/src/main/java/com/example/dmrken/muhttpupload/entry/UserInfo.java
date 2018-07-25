package com.example.dmrken.muhttpupload.entry;

/**
 * Created by dmrken on 2018/7/25.
 */

public class UserInfo {
    private String userName;
    private String passWord;
    private String sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public UserInfo(String userName, String passWord, String sex) {
        this.userName = userName;
        this.passWord = passWord;
        this.sex = sex;
    }

    public UserInfo() {
    }
}
