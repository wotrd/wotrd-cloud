package com.wotrd.dubboprovider.pay.wechatpay.config;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {


    // 格式：年－月－日 小时：分钟：秒
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

    // 格式：年－月－日 小时：分钟
    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

    // 格式：年月日 小时分钟秒
    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

    // 格式：年月日
    public static final String FORMAT_FOUR = "yyyyMMdd";

    // 格式：年－月－日
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

    // 格式：月－日
    public static final String SHORT_DATE_FORMAT = "MM-dd";

    // 格式：小时：分钟：秒
    public static final String LONG_TIME_FORMAT = "HH:mm:ss";

    //格式：年-月
    public static final String MONTG_DATE_FORMAT = "yyyy-MM";

    // 年的加减
    public static final int SUB_YEAR = Calendar.YEAR;

    // 月加减
    public static final int SUB_MONTH = Calendar.MONTH;

    // 天的加减
    public static final int SUB_DAY = Calendar.DATE;

    // 小时的加减
    public static final int SUB_HOUR = Calendar.HOUR;

    // 分钟的加减
    public static final int SUB_MINUTE = Calendar.MINUTE;

    // 秒的加减
    public static final int SUB_SECOND = Calendar.SECOND;

    static final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四",
            "星期五", "星期六"};

    public DateUtil() {
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            // log.error(e);
            d = null;
        }
        return d;
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static Date stringtoDate(String dateStr, String format,
                                    ParsePosition pos) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr, pos);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }

    /**
     * 把日期转换为字符串
     */
    public static String dateToString(Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }

    /**
     * 获取当前时间的指定格式
     */
    public static String getCurrDate(String format) {
        return dateToString(new Date(), format);
    }

    /**
     * @throws
     * @Title: dateSub
     * @Date 2014-1-9 上午10:44:02
     * @Description: 得到指定日期前(后)的日期
     * @param: @param dateKind  例：Calendar.DAY_OF_MONTH
     * @param: @param dateStr  指定日期
     * @param: @param amount   增加(减去)的时间量
     * @param: @return
     * @return: String
     * @author mtf
     */
    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = stringtoDate(dateStr, MONTG_DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return dateToString(calendar.getTime(), FORMAT_ONE);
    }

    /**
     * 昨日日期
     *
     * @return
     */
    public static String yearthDate(String dateStr) {
        Date date = stringtoDate(dateStr, LONG_DATE_FORMAT);//取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        //date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        return dateToString(calendar.getTime(), LONG_DATE_FORMAT);
    }

    /**
     * 两个日期相减
     *
     * @return 相减得到的秒数
     */
    public static long timeSub(String firstTime, String secTime) {
        long first = stringtoDate(firstTime, FORMAT_ONE).getTime();
        long second = stringtoDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }

    /**
     * 两个日期相减
     * 参数地DATE
     * second 两个日期相差的秒
     *
     * @return 相减得到的秒数
     * 后面时间减去前面时间  再减去 相差秒数    如果大于0 返回 FASLE
     */
    public static boolean timeSub(Date firstTime, Date secTime, long secs) {
        long first = firstTime.getTime();
        long second = secTime.getTime();
        // 判断两个时间 是否间隔那么长 secs。
        return (second - first - secs) > 0 ? false : true;
    }

    /**
     * 两个日期相减
     * 参数地DATE
     *
     * @return 相减得到的秒数
     * 后面时间减去前面时间  如果大于0 返回 false
     */
    public static boolean timeSub(Date firstTime, Date secTime) {
        long first = firstTime.getTime();
        long second = secTime.getTime();
        return (second - first) > 0 ? false : true;
    }

    /**
     * 获得某月的天数
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5")
                || month.equals("7") || month.equals("8") || month.equals("10")
                || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9")
                || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
                    || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }

        return days;
    }

    /**
     * 获取某年某月的天数
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前日期
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得当前月份
     */
    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当前年份
     */
    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的天
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的年
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的月份，1-12
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    /**
     * 比较两个日期的年差
     */
    public static int yearDiff(String before, String after) {
        Date beforeDay = stringtoDate(before, LONG_DATE_FORMAT);
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(afterDay) - getYear(beforeDay);
    }

    /**
     * 比较指定日期与当前日期的差
     */
    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(beforeDay) - getYear(afterDay);
    }

    /**
     * 获取每月的第一周
     */
    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取每月的最后一周
     */
    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return dateToString(today.getTime(), FORMAT_ONE);
    }


    /**
     * 判断日期是否有效,包括闰年的情况
     *
     * @param date YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer(
                "^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }


    /*****
     * 时间 增加、减少 n个小时以后时间
     * @param date
     *          YYYY-mm-dd HH:mm:ss
     * @param num>0  小时
     * @param type  增加和减少标志
     * **/
    public static Date adjustDateByHour(Date d, Integer num, int type) {
        Calendar Cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Cal.setTime(d);
        if (type == 0) {
            Cal.add(Calendar.MINUTE, -num);
            // System.out.println("date:"+df.format(Cal.getTime()));

        } else {
            Cal.add(Calendar.MINUTE, num);
            //System.out.println("date:"+df.format(Cal.getTime()));
        }
        return Cal.getTime();
    }

    /*****
     * 时间 增加、减少 n个分钟以后时间
     * @param date
     *          YYYY-mm-dd HH:mm:ss
     * @param num>0  分钟
     * @param type  增加和减少标志
     * **/
    public static Date adjustDateByMinutes(Date d, Integer num, int type) {
        Calendar Cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Cal.setTime(d);
        if (type == 0) {
            Cal.add(Calendar.MINUTE, -num);
            //  System.out.println("date:"+df.format(Cal.getTime()));

        } else {
            Cal.add(Calendar.MINUTE, num);
            //   System.out.println("date:"+df.format(Cal.getTime()));
        }
        return Cal.getTime();
    }


}
