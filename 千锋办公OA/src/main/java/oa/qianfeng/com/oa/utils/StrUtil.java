package oa.qianfeng.com.oa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/8.
 */
public class StrUtil {
    /**
     * 取空字符串
     *
     * @param str
     * @return
     */
    public static String isNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 返回时间：2016-12-08 14:00
     *
     * @param time
     * @return
     */
    public static String getStringByTime(long time) {
        Date date = new Date();
        date.setTime(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    /**
     * 计算持续时间
     * 上午是9:00-12:00
     * 下午是1:00-6:00
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getHourByDuration(int type, long startTime, long endTime) {
        String strStart = getStringByTime(startTime);
        String strEnd = getStringByTime(endTime);
        long duration = (endTime - startTime) / (1000 * 60 * 60);
        //如果是加班，就直接返回
        if (type == Constant.TYPE_OVERTIME) {
            return (int) duration;
        }
        String m = strStart.substring(11, 13);
        String n = strEnd.substring(11, 13);
        L.d("m=" + m);
        L.d("n=" + n);
        //有没有跨天,前十个字符串相同，没跨天，不相同，隔天了
        if (strStart.substring(0, 10).equals(strEnd.substring(0, 10))) {
            L.d("同一天：" + strStart.substring(0, 10));
            //同一天
            //开始时间是上午还是下午
            if (Integer.valueOf(m) <= 12 && Integer.valueOf(n) >= 12) {
                //开始在上午，结束在下午，中间减去午休一小时
                int value = (int) duration;
                if (Integer.valueOf(m) <= 9) {
                    value = value - (9 - Integer.valueOf(m));
                }
                if (Integer.valueOf(n) >= 18) {
                    value = value - (18 - Integer.valueOf(n));
                }
                return value - 1;
            } else {
                int value = (int) duration;
                if (Integer.valueOf(m) <= 9) {
                    value = value - (9 - Integer.valueOf(m));
                }
                if (Integer.valueOf(n) >= 18) {
                    value = value + (18 - Integer.valueOf(n));
                }
                return (int) value;
            }
            //结束时间是上午还是下午
        } else {
            //不同天
            int firstDay = 0;//第一天的时间
            int lastDay = 0;//最后一天的时间
            //开始在上午
            if (Integer.valueOf(m) <= 9) {
                firstDay = 8;//如果是9点之前，就一整天，8小时
            } else if (Integer.valueOf(m) <= 12) {
                firstDay = 8 - (9 - Integer.valueOf(m));
            } else if (Integer.valueOf(m) <= 18 && Integer.valueOf(m) > 13) {
                firstDay = 5 + (13 - Integer.valueOf(m));
            } else {
                firstDay = 5;
            }
            //最后一天
            if (Integer.valueOf(n) <= 9) {
                lastDay = 0;
            } else if (Integer.valueOf(n) <= 12) {
                lastDay = 3 - (12 - Integer.valueOf(n));
            } else if (Integer.valueOf(n) <= 18) {
                lastDay = 8 - (18 - Integer.valueOf(n));
            } else {
                lastDay = 8;
            }
            L.d("firstDay=" + firstDay);
            L.d("lastDay=" + lastDay);
           /* int days = 0;
            int startYear = Integer.valueOf(strStart.substring(0, 4));
            int endYear = Integer.valueOf(strEnd.substring(0, 4));
            int startMouth = Integer.valueOf(strStart.substring(5, 7));
            int endMouth = Integer.valueOf(strEnd.substring(5, 7));
            int startDay = Integer.valueOf(strStart.substring(8, 10));
            int endDay = Integer.valueOf(strEnd.substring(8, 10));
            if (endYear - startYear > 0) {
                //不是同一年的，判断月份
                if (endMouth == 1 && startMouth == 12) {
                    //跨年，从12月份到1月份
                }
            }*/
            int days = (int) (duration / 24);
            if (firstDay == 8) {
                days = days - 1;
            }
           /* if (lastDay == 8) {
                days--;
            }*/
            if (days < 0) {
                days = 0;
            }
            return days * 8 + firstDay + lastDay;
        }
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }
}
