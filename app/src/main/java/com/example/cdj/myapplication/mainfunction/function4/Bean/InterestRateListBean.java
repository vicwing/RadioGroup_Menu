package com.example.cdj.myapplication.mainfunction.function4.Bean;

/**
 * 房贷利率
 * Created by cdj on 2016/5/23.
 */
public class InterestRateListBean {

    private String description;
    private String mInterestRate;


    public InterestRateListBean(String description, String interestRate) {

        this.description = description;
        this.mInterestRate = interestRate;
    }

    public String getInterestRate() {
        return mInterestRate;
    }

    public void setInterestRate(String interestRate) {
        mInterestRate = interestRate;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
