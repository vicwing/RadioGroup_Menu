package com.example.cdj.myapplication.mainfunction.taxcaculator;

/**
 * Created by cdj on 2016/5/26.
 */

public enum TaxType {
     HOUSE_TYPE, SALE_ONLY, LATEST_SALE, PAYTAX_TYPE, FIRST_BUY;
     private String name;

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }
}