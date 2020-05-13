package com.example.bank;

/**
 * 类描述：用户管理类。
 * 创建人：vicwing
 * 创建时间：2019-11-25 20:39
 * 最后修改人：vicwing
 */
public class LoginTask {
    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param pwd      用户密码
     * @return
     */
    private boolean userLogin(String userName, String pwd) {
        if (userName.equals("用户名")) {
            if (pwd.equals("serverUserId")) {
                return true;
            } else {
                System.out.println("密码错误。");
            }
        } else {
            System.out.println("用户名错错误。");
        }
        return false;
    }

    /**
     * 开户
     *
     * @param userName
     * @param newPwd
     * @param idCard
     * @return
     */
    private boolean openAccount(String userName, String newPwd, String idCard) {
        if (Server.checkUserName(userName)) {
            if (Server.checkPwd(newPwd)) {
                if (Server.checkIDcard(idCard)) {

                } else {
                    System.out.println("无效证件。");
                }
            } else {
                System.out.println("密码错误。");
            }
        } else {
            System.out.println("无效用户名");
        }
        return false;
    }

    /**
     * 用户注销
     *
     * @param userName
     * @return
     */
    private boolean logout(String userName) {
        if (Server.logout(userName)) {
            return true;
        }
        System.out.println("用户登出无效.");
        return false;
    }

    /**
     * 冻结账户
     *
     * @param userName
     */
    private void freezAccount(String userName) {
        if (Server.freezAccount(userName)) {
            System.out.println("冻结账户成功");
        }
    }
}
