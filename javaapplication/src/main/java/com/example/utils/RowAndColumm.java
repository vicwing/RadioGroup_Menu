package com.example.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


    /**
     * 将结合按照阵列顺序排序
     * 存在数组越界的问题,最后的3列有可能越界.
     *
     * @param sourceList
     * @param row
     * @param column
     * @return
     */
    public static List<String> sortRowAndColumnData(List<String> sourceList, int row, int column) {
        if (sourceList != null && !sourceList.isEmpty()) {
            int size = sourceList.size();
            int totalColumn = currentColumn(row, size);
            System.out.println("columnTotalCount :   " + totalColumn);
//            String[][] strings = new String[row][column];
            String[] strings = new String[size];
            for (int i = 0; i < size; i++) {
                String source = sourceList.get(i);
                int currentColumn = currentColumn(row, i + 1);
                int currentRow = getRowIndex(i, row, column, currentColumn);
                int columnIndex = currentColumn % column == 0 ? 5 : currentColumn % column;
                int resutlIndex = (currentRow - 1) * column + columnIndex - 1;
//                int resutlIndex = (currentRow) * column + columnIndex;
                System.out.println("currentRow  = " + currentRow + " currentColumn = " + currentColumn
                        + " columnIndex = " + columnIndex
                        + " resutlIndex =" + resutlIndex);
//                strings[resutlIndex] = source;
                if (resutlIndex > size - 1) {
                    System.out.println("数组越界了...resutlIndex+" + resutlIndex);
                } else {
                    strings[resutlIndex] = source;
                }
            }
            return Arrays.asList(strings);
        }
        return null;
    }

    /**
     * i 当前i 属于第几列
     *
     * @param row
     * @param i2
     * @return
     */
    private static int currentColumn(int row, int i2) {
        return BigDecimal.valueOf((i2)).divide(BigDecimal.valueOf(row), RoundingMode.UP).intValue();
    }

    private static int getRowIndex(int i, int row, int column, int currentColumn) {
//        if (currentColumn % column == 0) {
//            int rowNumber = BigDecimal.valueOf(currentColumn).divide(BigDecimal.valueOf(column), RoundingMode.UP).intValue();
//            return rowNumber * row;
//        } else {
        int rowLine = i % row + 1;
        int rowNumber = BigDecimal.valueOf(currentColumn).divide(BigDecimal.valueOf(column), RoundingMode.UP).intValue();
        return rowNumber * row - (row - rowLine);
//        }
    }


    static int getTotalRow(int i, int row, int column, int lastColumn) {
//        int rowLine = i % row + 1;
        int rowNumber = BigDecimal.valueOf(lastColumn).divide(BigDecimal.valueOf(column), RoundingMode.UP).intValue();
        return rowNumber * row;
//        return getRowIndex(i, row, column, lastColumn);
    }

    /**
     * 二位数组阵列排序
     *
     * @param sourceList
     * @param row
     * @param column
     * @return
     */
    public static String[][] sortRowAndColumnData1(List<String> sourceList, int row, int column) {
        if (sourceList != null && !sourceList.isEmpty()) {
            System.out.println("数组---------------");
            int size = sourceList.size();
            int totalColumn = currentColumn(row, size);
            int totalRow = getTotalRow(size, row, column, totalColumn);
            String[][] stringArray = new String[totalRow][column];
            System.out.println("totalColumn  = " + totalColumn + " totalRow = " + totalRow);
            for (int i = 0; i < size; i++) {
                String source = sourceList.get(i);
                int currentColumn = currentColumn(row, i + 1);
                int currentRow = getRowIndex(i, row, column, currentColumn);
                int columnIndex = currentColumn % column == 0 ? column : currentColumn % column;
                System.out.println("currentRow  = " + currentRow + " currentColumn = " + currentColumn
                        + " columnIndex = " + columnIndex
                );
                stringArray[currentRow - 1][columnIndex - 1] = source;
            }
            for (int j = 0; j < totalRow; j++) {
                System.out.println(Arrays.toString(stringArray[j]));
            }
            return stringArray;
        }
        return null;
    }

    public static void printDataToend(List<String> resutData, int column) {
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
                if (i == size - 1) {
                    System.out.println(stringBuilder.toString());
                }
            }
        }
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
