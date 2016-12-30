package com.example.gaotengfei.retrofitwrapperdemo.bean;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/28
 * @tel: 18511913443
 * @desc:
 */

public class UserInfo {
    private String uid;
    private String mobile;
    private String nickname;
    private String location;
    private String city;
    private String company;
    private String avatar;
    private boolean password;

    public String getUid() {
        return uid;
    }

    public String getMobile() {
        return mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    public String getCompany() {
        return company;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password=" + password +
                '}';
    }
}
