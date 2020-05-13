package com.example.bank;

import java.util.List;

/**
 * 类描述：操作数据库
 * 创建人：vicwing
 * 创建时间：2019-11-26 11:19
 * 最后修改人：vicwing
 */
public class DataBaseTask {

    /**更新用户操作表
     * @param user
     */
    public static void updateUserAccount(User user) {

    }

    /**获取用户数据表
     * @param userId
     * @return
     */
    public static User getUser(String userId) {
        return null;
    }

    /**
     * 查询用户交易记录表
     * @param userId
     * @return
     */
    public static List<Double> getUserDetailList(String userId) {
        return null;
    }

    /**
     * 插入新的用户
     *
     * @param user
     * @return
     */
    public static boolean insertUser(User user) {
        if (user != null) {
            return true;
        }
        return false;
    }
}
