package com.example.cdj.myapplication.Bean;

import java.io.Serializable;

/**
 * Created by cdj onCallBackData 2016/5/17.
 */
public class HouseLoanRateEnty implements Serializable{
    /**
     * message : 处理成功
     * result : {"businessHousingLoan":{"date":"2015-08-26 00:00:00","desc":"15年8月26日基准利率下限(1.1倍)","fmtDate":"2015-08-26","fmtUpdateTime":"2015-08-26 10:24:40","id":"dda5a060-73a9-4980-be0c-7fc6d0031102","index":3,"loanType":"BUSINESS","maxLoanPeriod":9999,"minLoanPeriod":60,"rate":5.67,"rateType":"ADJUSTIVE","updateBy":{"id":110,"name":"姚静"},"updateTime":"2015-08-26 10:24:40"},"housingfundHousingLoan":{"date":"2015-08-26 00:00:00","desc":"15年8月26日基准利率下限(1.1倍)","fmtDate":"2015-08-26","fmtUpdateTime":"2015-08-26 10:24:40","id":"f957cc6a-586a-4bcc-895e-491a82dc2aad","index":3,"loanType":"HOUSING_FUND","maxLoanPeriod":9999,"minLoanPeriod":60,"rate":3.58,"rateType":"ADJUSTIVE","updateBy":{"id":110,"name":"姚静"},"updateTime":"2015-08-26 10:24:40"}}
     * status : C0000
     */

    private String message;
    /**
     * businessHousingLoan : {"date":"2015-08-26 00:00:00","desc":"15年8月26日基准利率下限(1.1倍)","fmtDate":"2015-08-26","fmtUpdateTime":"2015-08-26 10:24:40","id":"dda5a060-73a9-4980-be0c-7fc6d0031102","index":3,"loanType":"BUSINESS","maxLoanPeriod":9999,"minLoanPeriod":60,"rate":5.67,"rateType":"ADJUSTIVE","updateBy":{"id":110,"name":"姚静"},"updateTime":"2015-08-26 10:24:40"}
     * housingfundHousingLoan : {"date":"2015-08-26 00:00:00","desc":"15年8月26日基准利率下限(1.1倍)","fmtDate":"2015-08-26","fmtUpdateTime":"2015-08-26 10:24:40","id":"f957cc6a-586a-4bcc-895e-491a82dc2aad","index":3,"loanType":"HOUSING_FUND","maxLoanPeriod":9999,"minLoanPeriod":60,"rate":3.58,"rateType":"ADJUSTIVE","updateBy":{"id":110,"name":"姚静"},"updateTime":"2015-08-26 10:24:40"}
     */

    private ResultEntity result;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultEntity {
        /**
         * date : 2015-08-26 00:00:00
         * desc : 15年8月26日基准利率下限(1.1倍)
         * fmtDate : 2015-08-26
         * fmtUpdateTime : 2015-08-26 10:24:40
         * id : dda5a060-73a9-4980-be0c-7fc6d0031102
         * index : 3
         * loanType : BUSINESS
         * maxLoanPeriod : 9999
         * minLoanPeriod : 60
         * rate : 5.67
         * rateType : ADJUSTIVE
         * updateBy : {"id":110,"name":"姚静"}
         * updateTime : 2015-08-26 10:24:40
         */

        private BusinessHousingLoanEntity businessHousingLoan;
        /**
         * date : 2015-08-26 00:00:00
         * desc : 15年8月26日基准利率下限(1.1倍)
         * fmtDate : 2015-08-26
         * fmtUpdateTime : 2015-08-26 10:24:40
         * id : f957cc6a-586a-4bcc-895e-491a82dc2aad
         * index : 3
         * loanType : HOUSING_FUND
         * maxLoanPeriod : 9999
         * minLoanPeriod : 60
         * rate : 3.58
         * rateType : ADJUSTIVE
         * updateBy : {"id":110,"name":"姚静"}
         * updateTime : 2015-08-26 10:24:40
         */

        private HousingfundHousingLoanEntity housingfundHousingLoan;

        public BusinessHousingLoanEntity getBusinessHousingLoan() {
            return businessHousingLoan;
        }

        public void setBusinessHousingLoan(BusinessHousingLoanEntity businessHousingLoan) {
            this.businessHousingLoan = businessHousingLoan;
        }

        public HousingfundHousingLoanEntity getHousingfundHousingLoan() {
            return housingfundHousingLoan;
        }

        public void setHousingfundHousingLoan(HousingfundHousingLoanEntity housingfundHousingLoan) {
            this.housingfundHousingLoan = housingfundHousingLoan;
        }

        public static class BusinessHousingLoanEntity {
            private String date;
            private String desc;
            private String fmtDate;
            private String fmtUpdateTime;
            private String id;
            private int index;
            private String loanType;
            private int maxLoanPeriod;
            private int minLoanPeriod;
            private double rate;
            private String rateType;
            /**
             * id : 110
             * name : 姚静
             */

            private UpdateByEntity updateBy;
            private String updateTime;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getFmtDate() {
                return fmtDate;
            }

            public void setFmtDate(String fmtDate) {
                this.fmtDate = fmtDate;
            }

            public String getFmtUpdateTime() {
                return fmtUpdateTime;
            }

            public void setFmtUpdateTime(String fmtUpdateTime) {
                this.fmtUpdateTime = fmtUpdateTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getLoanType() {
                return loanType;
            }

            public void setLoanType(String loanType) {
                this.loanType = loanType;
            }

            public int getMaxLoanPeriod() {
                return maxLoanPeriod;
            }

            public void setMaxLoanPeriod(int maxLoanPeriod) {
                this.maxLoanPeriod = maxLoanPeriod;
            }

            public int getMinLoanPeriod() {
                return minLoanPeriod;
            }

            public void setMinLoanPeriod(int minLoanPeriod) {
                this.minLoanPeriod = minLoanPeriod;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public String getRateType() {
                return rateType;
            }

            public void setRateType(String rateType) {
                this.rateType = rateType;
            }

            public UpdateByEntity getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(UpdateByEntity updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public static class UpdateByEntity {
                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class HousingfundHousingLoanEntity {
            private String date;
            private String desc;
            private String fmtDate;
            private String fmtUpdateTime;
            private String id;
            private int index;
            private String loanType;
            private int maxLoanPeriod;
            private int minLoanPeriod;
            private double rate;
            private String rateType;
            /**
             * id : 110
             * name : 姚静
             */

            private UpdateByEntity updateBy;
            private String updateTime;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getFmtDate() {
                return fmtDate;
            }

            public void setFmtDate(String fmtDate) {
                this.fmtDate = fmtDate;
            }

            public String getFmtUpdateTime() {
                return fmtUpdateTime;
            }

            public void setFmtUpdateTime(String fmtUpdateTime) {
                this.fmtUpdateTime = fmtUpdateTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getLoanType() {
                return loanType;
            }

            public void setLoanType(String loanType) {
                this.loanType = loanType;
            }

            public int getMaxLoanPeriod() {
                return maxLoanPeriod;
            }

            public void setMaxLoanPeriod(int maxLoanPeriod) {
                this.maxLoanPeriod = maxLoanPeriod;
            }

            public int getMinLoanPeriod() {
                return minLoanPeriod;
            }

            public void setMinLoanPeriod(int minLoanPeriod) {
                this.minLoanPeriod = minLoanPeriod;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public String getRateType() {
                return rateType;
            }

            public void setRateType(String rateType) {
                this.rateType = rateType;
            }

            public UpdateByEntity getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(UpdateByEntity updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public static class UpdateByEntity {
                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
