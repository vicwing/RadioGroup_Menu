package com.example.date;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

/**
 * 类描述：java 8 新日期类测试
 * 创建人：vicwing
 * 创建时间：2019-10-22 10:51
 * 最后修改人：vicwing
 */
public class NewJavaDateUtils {
    public static void main(String[] args) {


        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
//        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
//        System.out.println("Date = " + date);
//        System.out.println("LocalDate = " + localDate);
//        System.out.println("localtime" + LocalDateTime.now());
//        System.out.println("localtime" + System.currentTimeMillis());
//        System.out.println("localtime" + 1571068800000L);
//        timeToLocalTime(1571714939234L);

//        getDateByTime(1571068800000L);'
//        localTimeTest();
//        getDateByTime(1571714939234L);
//        String s = formatInformationListDate(1571816662347L);
//        System.out.println(" sssss = " + s);
//        dateTimeFormatter();


//        Instant toInstant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
//        System.out.println("当地时间 :  " + toInstant.toString());
//
//        LocalDateTime localDateTime = timeToLocalTime(1586966400000L);
//        System.out.println("转化当前时间  = " + localDateTime.toString());
//        durationBetween(toInstant, Instant.ofEpochMilli(1586966400000L));


        //测试两个时间的差距.
//        LocalDate startDate = LocalDate.of(1993, Month.OCTOBER, 19);
//        System.out.println("开始时间  : " + startDate);

//        LocalDate localDate = LocalDateTime.now().toLocalDate();
//        LocalDate endDate = LocalDate.of(2017, Month.JUNE, 16);
        Instant now = Instant.now();
        long nowTime = now.toEpochMilli();

        LocalDateTime endTime = timeToLocalTime(1586966400000L);
        Instant.ofEpochMilli(1586966400000L);
//        Instant instant3 = localDateTime3.atZone(ZoneId.systemDefault()).toInstant();
        getTimeBetween(nowTime, 1586966400000L);
//        System.out.println("结束时间 : " + endDate);

//        long daysDiff = ChronoUnit.DAYS.between(nowTime, endTime);
//        System.out.println("两天之间的差在天数   : " + daysDiff);


//        LocalDate today = LocalDate.now();
//        System.out.println("Today : " + today);
//        LocalDate birthDate = timeToLocalDate(1586966400000L);
//        System.out.println("BirthDate : " + birthDate);
//
//        Period p = Period.between(today, birthDate);
//        System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());

//        localDateToDate(LocalDate.now());
//        LocalDate localDate1 = timeToLocalDate(1571068800000L);
//        LocalDate today = LocalDate.now();
//        Period period = Period.between(localDate1, today);
//        System.out.println("Period = " + period.getDays());

    }

    /**
     * 计算两个日子的倒计时
     *
     * @param startTime
     * @param endTime
     */
    private static void getTimeBetween(long startTime, long endTime) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
        long between, year = 0, mouth = 0, day = 0, hour = 0, min = 0, s = 0;
        try {
//            java.util.Date date = df.parse(df.format(Long.parseLong(startTime)));
            between = endTime - startTime;
            System.out.println(endTime + " %d endTime ");
            System.out.println(startTime + " %d startTime ");
            System.out.println(between + " %d between ");
            year = between / (365 * 24 * 60 * 60 * 1000);
            mouth = between / (30 * 24 * 60 * 60 * 1000) - year * 12;
            day = between / (24 * 60 * 60 * 1000) - mouth * 30;
            hour = (between / (60 * 60 * 1000) - day * 24);
            min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
            s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf(" %d 年 %d 月 %d 日 %d小时 %d 分 %d 秒", year, mouth, day, hour, min, s);
    }

    /**
     * 比较两个日期之前时间差
     *
     * @param startTemporal
     * @param endTemporal1
     * @return
     */
    private static long durationBetween(Instant startTemporal, Instant endTemporal1) {
        Duration duration = Duration.between(startTemporal, endTemporal1);
        System.out.println(" 天   =" + duration.toDays());
        System.out.println(" 小时 =" + duration.toHours());
        System.out.println(" 分   =" + duration.toMinutes());
        return duration.toHours();
    }

    private static void localTimeTest() {
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime test ::" + ZonedDateTime.now());
        System.out.println("localTime test ::" + localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL).withZone(ZoneId.systemDefault())));
        System.out.println("localTime test ::" + localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG).withZone(ZoneId.systemDefault())));
        System.out.println("localTime test ::" + localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault())));
        System.out.println("localTime test ::" + localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withZone(ZoneId.systemDefault())));
    }

    private static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDate = " + localDate);
        System.out.println("Date = " + date);

        return date;
    }

    private static LocalDateTime timeToLocalTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zoneId = ZoneId.systemDefault();
//        Date date = Date.from(instant);
//        LocalDateTime localDateTime = date.toInstant().atZone(zoneId).toLocalDateTime();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
//        System.out.println("method localDateTime  = " + localDateTime);
        return localDateTime;
    }

    private static LocalDate timeToLocalDate(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(instant);
        LocalDate localDate1 = date.toInstant().atZone(zoneId).toLocalDate();
        System.out.println("date  = " + localDate1);
        return localDate1;
    }

    public static void convertDate() {
        //Date与Instant的相互转化
        Instant instant = Instant.now();
        Date date = Date.from(instant);
        Instant instant2 = date.toInstant();

        //Date转为LocalDateTime
        Date date2 = new Date();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());

        //LocalDateTime转Date
        LocalDateTime localDateTime3 = LocalDateTime.now();
        Instant instant3 = localDateTime3.atZone(ZoneId.systemDefault()).toInstant();
        Date date3 = Date.from(instant);

        //LocalDate转Date
        //因为LocalDate不包含时间，所以转Date时，会默认转为当天的起始时间，00:00:00
        LocalDate localDate4 = LocalDate.now();
        Instant instant4 = localDate4.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date4 = Date.from(instant);
    }

    private static void dateTimeFormatter() {
        LocalDateTime localTime = LocalDateTime.now();
        System.out.println("dateformat   = " + localTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.systemDefault())));
        System.out.println("dateformat   = " + localTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.systemDefault())));
        System.out.println("dateformat   = " + localTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withZone(ZoneId.systemDefault())));
        System.out.println("dateformat   = " + localTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault())));
        System.out.println("dateformat   = " + localTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        System.out.println("dateformat   = " + localTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println("dateformat   = " + localTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        System.out.println("dateformat   = " + localTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
    }

    /**
     * 系统当前时间比较
     *
     * @param millTimes 时间戳
     * @return
     */
    private static String getDateByTime(long millTimes) {
        LocalDateTime localTime = timeToLocalTime(millTimes);
        LocalDateTime startTime = LocalDate.now().atTime(0, 0, 0);
        LocalDateTime endTime = LocalDate.now().atTime(23, 59, 59);
        LocalDateTime startYesterday = LocalDate.now().plusDays(-1).atTime(0, 0, 0);
        LocalDateTime endYesterday = LocalDate.now().plusDays(-1).atTime(23, 59, 59);
        //如果小于昨天的开始日期
        if (localTime.isBefore(startYesterday)) {
            System.out.println("时间是过去");
            Period period = Period.between(localTime.toLocalDate(), startTime.toLocalDate());
            System.out.println("过去了" + period.getDays());
            return String.valueOf(period.getDays());
        }
        //时间是昨天
        if (localTime.isAfter(startYesterday) && localTime.isBefore(endYesterday)) {
            System.out.println("时间是昨天");
            return "昨天";
        }
        //如果大于今天的开始日期，小于今天的结束日期
        if (localTime.isAfter(startTime) && localTime.isBefore(endTime)) {
            String result = "时间是今天 " + localTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            System.out.println(result);
            long between = durationBetween(localTime.atZone(ZoneId.systemDefault()).toInstant(), LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            return String.valueOf(between);
        }
        //如果大于今天的结束日期
        if (localTime.isAfter(endTime)) {
            System.out.println("时间是未来");
            String result = localTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
            return result;
        }
        return null;
    }


    /**
     * 格式化聊天时间的显示方式(消息列表用)
     */
    public static String formatInformationListDate(long formatDate) {

        // 聊天日期
        Calendar calendar = Calendar.getInstance();
        // calendar.setTime(new
        // SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(formatIMChatDate));
        calendar.setTimeInMillis(formatDate);
        // 当前日期
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat dateFormat;
        // 跨年
        if (calendar.get(Calendar.YEAR) < curCalendar.get(Calendar.YEAR)) {
            dateFormat = new SimpleDateFormat(YYYY_MM_DD);
            return dateFormat.format(calendar.getTime());
        } else if (calendar.get(Calendar.DAY_OF_YEAR) + 2 == curCalendar.get(Calendar.DAY_OF_YEAR)) {
            return "前天";
        } else if (calendar.get(Calendar.DAY_OF_YEAR) + 1 == curCalendar.get(Calendar.DAY_OF_YEAR)) {
            return "昨天";
        } else if (calendar.get(Calendar.DAY_OF_YEAR) == curCalendar.get(Calendar.DAY_OF_YEAR)) {
            dateFormat = new SimpleDateFormat(HH_MM);
//            dateFormat = new SimpleDateFormat(HH);

            int i = calendar.get(Calendar.HOUR_OF_DAY);

            long timeInMillis = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

            System.out.println(" timeInMillis = " + timeInMillis + "  time  = " + i);

            return "今天" + " " + dateFormat.format(calendar.getTime());
        } else {// 本年的昨天以前
            dateFormat = new SimpleDateFormat(YYYY_MM_DD);
            return dateFormat.format(calendar.getTime());
        }
    }

    public final static String YYYY_MM_DD = "yyyy/M/d";
    public final static String HH_MM = "HH:mm";
    public final static String HH = "HH";
}
