package com.example.cdj.myapplication.Bean;

import java.util.List;

/**
 *  二手房列表
 * Created by cdj on 2016/5/11.
 */
public class SecListBean {

    private String message;
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
        private int currentPage;
        private int pageCount;
        private int pageSize;
        private boolean recommend;
        private int recordCount;
        private List<SecListItemEntity> list;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public boolean isRecommend() {
            return recommend;
        }

        public void setRecommend(boolean recommend) {
            this.recommend = recommend;
        }

        public int getRecordCount() {
            return recordCount;
        }

        public void setRecordCount(int recordCount) {
            this.recordCount = recordCount;
        }

        public List<SecListItemEntity> getList() {
            return list;
        }

        public void setList(List<SecListItemEntity> list) {
            this.list = list;
        }


    }
}
