package com.example.bank;

import java.util.List;

/**
 * 类描述：调用服务端接口。
 * 创建人：vicwing
 * 创建时间：2019-11-25 20:43
 * 最后修改人：vicwing
 */
public class Server {
    /**
     * 验证用户名是否有效
     * @param userName
     * @return
     */
    public static boolean checkUserName(String userName) {
        if ("有效".equals(userName)) {
            return true;
        }
        return false;
    }

    /**
     * 验证是否有效密码
     *
     * @param pwd
     * @return
     */
    public static boolean checkPwd(String pwd) {
        if ("有效密码".equals(pwd)) {
            return true;
        }
        return false;
    }

    /**
     * 验证是否有效的id
     *
     * @param idCard
     * @return
     */
    public static boolean checkIDcard(String idCard) {
        if ("有效证件".equals(idCard)) {
            return true;
        }
        return false;
    }

    /**
     * 用户注销登出
     *
     * @param userName
     * @return
     */
    public static boolean logout(String userName) {
        if ("有效用户名".equals(userName)) {
            return true;
        }
        return false;
    }

    /**
     * 冻结账户
     *
     * @param userName
     * @return
     */
    public static boolean freezAccount(String userName) {
        if ("用户名称".equals(userName)) {
            return true;
        }
        return false;
    }

    /**
     * 保存用户的数据
     *
     * @param user
     */
    public static void updateUserAccount(User user) {
        DataBaseTask.updateUserAccount(user);
    }

    /**
     * 获取用户数据
     *
     * @param userId 用户id
     * @return
     */
    public static User getUser(String userId) {
        return DataBaseTask.getUser(userId);
    }

    /**
     * 获取用户详细交易数据
     *
     * @param userId 用户id
     * @return
     */
    public static List<Double> getUseDetailList(String userId) {
        return DataBaseTask.getUserDetailList(userId);
    }
}
