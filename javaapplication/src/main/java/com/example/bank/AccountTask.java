package com.example.bank;

import java.util.List;

/**
 * 类描述：账户操作类
 * 创建人：vicwing
 * 创建时间：2019-11-26 11:11
 * 最后修改人：vicwing
 */
public class AccountTask {

    /**
     * 存款
     *
     * @param user  用户
     * @param money 存款金额.
     */
    public void saveMoney(User user, double money) {
        if (user != null) {
            if (user.getAccounNumber().equals("获取账户")) {
                double acountMoney = user.getMoney();
                user.setMoney(acountMoney + money);
                Server.updateUserAccount(user);
            }
        }
    }

    /**
     * 取款
     *
     * @param user      用户
     * @param needMoney 取款金额
     */
    public void getMoney(User user, double needMoney) {
        if (user != null) {
            if (needMoney > 0) {
                double accountMoney = user.getMoney();
                if (accountMoney > needMoney) {
                    double newAccountMoney = accountMoney - needMoney;
                    user.setMoney(newAccountMoney);
                    Server.updateUserAccount(user);
                }
            }
        }
    }

    /**
     * 转账
     *
     * @param sourceUser    转账账户
     * @param targetUser    目标账户
     * @param transferMoney 转账金额
     */
    public void transferMoney(User sourceUser, User targetUser, double transferMoney) {
        if (sourceUser != null && targetUser != null) {
            double sourceUserMoney = sourceUser.getMoney();
            if (sourceUserMoney > transferMoney) {
                String targetAccounNumber = targetUser.getAccounNumber();
                if ("有效账户".equals(targetAccounNumber)) {
                    double sourceUserResutmoney = sourceUserMoney - transferMoney;
                    sourceUser.setMoney(sourceUserResutmoney);
                    Server.updateUserAccount(sourceUser);

                    targetUser.setMoney(targetUser.getMoney() + transferMoney);
                    Server.updateUserAccount(targetUser);
                }
            }
        }
    }


    /**
     * 查询余额.
     *
     * @return
     */
    public User queryAccount(String userId) {
        User user = Server.getUser(userId);
        if (user != null) {
            user.getUserName();
            user.getMoney();
            return user;
        }
        return null;
    }

    /**
     * 查询明细
     *
     * @return
     */
    public List<Double> queryDetailAccount(String userId) {
        List<Double> useDetailList = Server.getUseDetailList(userId);
        if (useDetailList != null && !useDetailList.isEmpty()) {
            return useDetailList;
        }
        return null;
    }
}
