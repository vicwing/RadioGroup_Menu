package com.example.bank;

/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019-11-25 20:35
 * 最后修改人：vicwing
 */
public class User {
    /**
     * 用户密码
     */
    private String pwd;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 证件号码
     */
    private String accounNumber;
    /**
     * 用户号码
     */
    private String personId;
    /**
     * 账户余额.
     */
    private double money;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAccounNumber() {
        return accounNumber;
    }

    public void setAccounNumber(String accounNumber) {
        this.accounNumber = accounNumber;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
