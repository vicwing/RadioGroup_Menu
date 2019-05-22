package com.example.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类描述：
 * 创建人：vicwing
 * 创建时间：2019-05-21 09:39
 * 最后修改人：vicwing
 */
public class RowAndColumm {
    ArrayList<String> mData;
    private int column = 5;
    private int row = 2;

    private List<String> resutData = new ArrayList<>();


    public static List<String> dealData(List<String> sourceList, int row, int column) {
        if (sourceList != null && !sourceList.isEmpty()) {
            int size = sourceList.size();
//            ArrayList<String> resultList = new ArrayList<>(size);
            String[] strings = new String[size];
            int count = 0;
            for (int i = 0; i < size; i++) {
                String source = sourceList.get(i);
                int rowIndex = i % row;
                int columIndex = i % column;
                int rowNumber = i / row;
                int coloumIndex = i % column;
//                System.out.println("rowindex  = " + rowIndex + " columIndex = " + columIndex);
                System.out.println("rowindex  = " + rowIndex + " rowNumber = " + rowNumber);


                int mitrixTotal = row * column;
                int mitrisCount = i / mitrixTotal;
                if (mitrisCount <=0){
                    if (rowNumber <=column - 1) {
//                    int max = i / (row * column - 1);
//                    int maxrow = row * column * max;
//                    int index = maxrow + rowIndex ;
//                    strings[index] = source;
                        int index = rowIndex * column + rowNumber;
                        strings[index] = source;
                    }
                }else {
                    int index = mitrisCount +rowIndex * column + rowNumber;
                    strings[index] = source;
                }

//                if (count == row) {
//                    strings[index + coloumIndex] = source;
//                    count = 0;
//                } else {
//                    strings[index] = source;
//                    count++;
//                }
////                System.out.println("index  = " + index);
//                strings[index] = source;


            }
            return Arrays.asList(strings);
        }
        return null;
    }

    public static void printData(List<String> resutData, int column) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = resutData.size();
        int totalRow = 0;
        totalRow = size / column;
        for (int i = 0; i < size; i++) {
            String s = resutData.get(i);
            stringBuilder.append(s + "   ");
            double i1 = (double) i / column;
            if (i1 <= totalRow) {
                int columnNumber = i % column;
                if (columnNumber == column - 1) {
                    System.out.println(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            } else {
//                if (rest==i%column){
                if (i == size - 1) {
                    System.out.println(stringBuilder.toString());
                }
//                    stringBuilder.setLength(0);
//                }
            }
        }
    }
}
