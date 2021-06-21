package com.laifeiyang.dev.micro.common.utils;

import com.laifeiyang.dev.micro.common.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
public class WeekdayUtil {
    private static final SimpleDateFormat yMdHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
    private static final SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");//年月日
    private static String inputTime = yMdHms.format(new Date());
    private static String second = "0";//秒不做计算
    private static String inTime = "";
    private static String inDate = "";
    private static int amMiCount = 0;
    private static int pmMiCount = 0;
    private static int workLength = 480;//上班时长--精确到分钟
    private static int restLength = 120;//中午休息时长--精确到分钟
    private static String startTime = "08:00:00";//上班时间
    private static String restStartTime = "12:00:00";//下班时间
    private static String restEndTime = "14:00:00";//上班时间
    private static String endTime = "18:00:00";//下班时间
    private static List holiDayList = new ArrayList();
    private static List workDayList = new ArrayList();

    @Autowired
    private SearchService searchService;

    private static WeekdayUtil weekdayUtil;  // 静态初使化当前类

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        weekdayUtil = this;
        weekdayUtil.searchService = this.searchService;
        // 初使化时将已静态化的testService实例化
    }
    /**
     * 获得日工作时长
     * */
    public int getWorkLength(){
        return workLength;
    }
    /**
     * 给指定的日期添加小时不用剔除非工作日
     */
    public static String addTimeHour(String format, String time, int addHours) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (null == time || "".equals(time) || time.length() != 19) {
            time = yMdHms.format(new Date());
        }
        String currTime = time;
        int days = (int) (addHours / 24);
        int overMinute = (int) (addHours % 24) * 60;
        Date date = null;
        try {
            date = yMdHms.parse(currTime);
        } catch (Exception ex) {
            log.info(ex.getMessage());
            ex.printStackTrace();
        }
        if (date == null){
            return "";
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, overMinute);// 24小时制
        while (days > 0) {
            days--;
            cal.add(Calendar.DATE, 1);// 1天
        }
        ;
        //将秒计算在内
        if ("00".equals(currTime.substring(currTime.lastIndexOf(":") + 1))) {
            cal.add(Calendar.SECOND, Integer.parseInt(second)); //秒计算
            if (!"00".equals(second)) {
                cal.add(Calendar.MINUTE, -1);
            }
        } else {
            cal.add(Calendar.MINUTE, -1);
        }

        date = cal.getTime();
        cal = null;
        return dateFormat.format(date);
    }

    /**
     * 计算两时间之间的工作时间差
     */
    public String computWorkTime(String nowTime, String compareTime, String latn_id) {
        String result = "";
        this.setDateConfigData(latn_id);//查询时间配置表里的信息
        setWorkLength(compareTime);//计算工作时长
        String flag = compareDateTime(nowTime, compareTime);
        long towTimeHour = getDiffDateTime(nowTime, compareTime) / (60 * 60);//计算两时间的小时
        int min = 0;
        if ("1".equals(flag)) {//计算剩余时间
            min = betweenTowTime(nowTime, compareTime);
            result = "剩余" + (min / 60) + "小时" + (min % 60) + "分钟";
        } else if ("-1".equals(flag)) {//计算超时时间
            min = betweenTowTime(compareTime, nowTime);
            result = "超时" + (min / 60) + "小时" + (min % 60) + "分钟";
        }
        return result;
    }

    /**
     * 计算两时间之间的工作时间差 2015-04-14 12:10:20
     */
    public static int betweenTowTime(String startDate, String endDate) {
        int min = 0;
        try {
            Date date1 = yMdHms.parse(startDate);
            Date date2 = yMdHms.parse(endDate);
            String d1 = yMd.format(date1);
            String d2 = yMd.format(date2);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(yMd.parse(d1));
            int f1 = workDayFlag(startDate);
            int f2 = workDayFlag(endDate);
            String _currStartTime = d1 + " " + startTime;
            String _currRestStartTime = d1 + " " + restStartTime;
            String _currRestEndTime = d1 + " " + restEndTime;
            String _currEndTime = d1 + " " + endTime;
            if (d1.equals(d2)) {//为同一天
                if (isWeekday(cal1)) {
                    if (f1 == 1) {
                        if (f2 == 1) {
                            min = 0;
                        } else if (f2 == 2) {
                            min = (int) getDiffDateTime(_currStartTime, endDate) / 60;
                        } else if (f2 == 3) {
                            min = amMiCount;
                        } else if (f2 == 4) {
                            min = (int) (getDiffDateTime(_currRestEndTime, endDate) / 60) + amMiCount;
                        } else if (f2 == 5) {
                            min = amMiCount + pmMiCount;
                        }
                    } else if (f1 == 2) {
                        if (f2 == 2) {
                            min = (int) getDiffDateTime(startDate, endDate) / 60;
                        } else if (f2 == 3) {
                            min = (int) getDiffDateTime(startDate, _currStartTime) / 60;
                        } else if (f2 == 4) {
                            min = (int) (getDiffDateTime(startDate, endDate) / 60) - restLength;
                        } else if (f2 == 5) {
                            min = (int) (getDiffDateTime(startDate, _currStartTime) / 60) + pmMiCount;
                        }
                    } else if (f1 == 3) {
                        if (f2 == 3) {
                            min = 0;
                        } else if (f2 == 4) {
                            min = (int) getDiffDateTime(_currRestEndTime, endDate) / 60;
                        } else if (f2 == 5) {
                            min = pmMiCount;
                        }
                    } else if (f1 == 4) {
                        if (f2 == 4) {
                            min = (int) getDiffDateTime(startDate, endDate) / 60;
                        } else if (f2 == 5) {
                            min = (int) getDiffDateTime(startDate, _currEndTime) / 60;
                        }
                    } else {
                        min = 0;
                    }
                } else {
                    min = 0;
                }
            } else {//不是同一天
                if (isWeekday(cal1)) {//是工作日
                    if (f1 == 1) {
                        min = workLength;
                    } else if (f1 == 2) {
                        min = (int) (getDiffDateTime(startDate, _currRestStartTime) / 60) + pmMiCount;
                    } else if (f1 == 3) {
                        min = pmMiCount;
                    } else if (f1 == 4) {
                        min = (int) getDiffDateTime(startDate, _currEndTime) / 60;
                    } else if (f1 == 5) {
                        min = 0;
                    }
                } else {
                    min = 0;
                }
                cal1.add(Calendar.DATE, 1);//增加1天
                Date newDate = cal1.getTime();
                String nextDate = yMdHms.format(newDate);//开始一天的零点
                min += betweenTowTime(nextDate, endDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return min;
    }

    /**
     * 在工作日的那个阶段
     */
    private static int workDayFlag(String currTime) {
        int flag = 0;
        String currDay = currTime.substring(0, 10);
        String _currStartTime = currDay + " " + startTime;
        String _currRestStartTime = currDay + " " + restStartTime;
        String _currRestEndTime = currDay + " " + restEndTime;
        String _currEndTime = currDay + " " + endTime;
        //判断是否在当天上班之前
        String compareStartTime = compareDateTime(currTime, _currStartTime);//判断是否在上班之前
        String compareEndTime = compareDateTime(currTime, _currEndTime);//判断是否在下班之后
        String compareRestStartTime = compareDateTime(currTime, _currRestStartTime);
        String compareRestEndTime = compareDateTime(currTime, _currRestEndTime);
        if ("1".equals(compareStartTime)) {//上班前
            flag = 1;
        } else if ("-1".equals(compareStartTime) && "1".equals(compareRestStartTime)) {//上午上班时间
            flag = 2;
        } else if ("-1".equals(compareRestStartTime) && "1".equals(compareRestEndTime)) {//中午休息时间
            flag = 3;
        } else if ("-1".equals(compareRestEndTime) && "1".equals(compareEndTime)) {//下午上班时间
            flag = 4;
        } else if ("-1".equals(compareEndTime)) {//下班后
            flag = 5;
        }
        return flag;
    }

    /**
     * @param format
     * @param time
     * @param addHours
     * @param latn_id
     * @return
     */
    public String addDateHours(String format, String time, float addHours, String latn_id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        this.setDateConfigData(latn_id);
        //判断时间是否是工作日，不是工作日则取最近的工作日
        try {
            Calendar wd = Calendar.getInstance();
            Date nowTime = dateFormat.parse(time);
            wd.setTime(nowTime);
            if (!isWeekday(wd)) {
                while (!isWeekday(wd)) {
                    wd.add(Calendar.DATE, 1);//增加一天
                }
                nowTime = wd.getTime();
                nowTime = yMd.parse(yMdHms.format(nowTime));
                time = yMdHms.format(nowTime);//取是工作日的零点
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        setWorkLength(time);
        float workHours = workLength / 60f;
        int days = (int) (addHours / workHours);
        int overMinute = (int) (addHours % workHours) * 60;
        days = days + overMinute / workLength;
        overMinute = overMinute % workLength;

        String currTime = inputTime;
        //当天的开始时间
        String _currStartTime = inDate + " " + startTime;
        String _currRestStartTime = inDate + " " + restStartTime;
        String _currRestEndTime = inDate + " " + restEndTime;
        String _currEndTime = inDate + " " + endTime;
        //判断是否在当天上班之前
        String compareStartTime = compareDateTime(inputTime, _currStartTime);//判断是否在上班之前
        String compareEndTime = compareDateTime(inputTime, _currEndTime);//判断是否在下班之后
        long chaEndTime = getDiffDateTime(inputTime, _currEndTime) / 60;//距离当天下午下班的时间
        if ("1".equals(compareStartTime)) {//在上班之前
            second = "00";
            currTime = _currStartTime;
            if (amMiCount < overMinute) {
                overMinute += restLength;
            }
        } else {
            if ("1".equals(compareEndTime)) {//在在下班之前
                String compareRestStartTime = compareDateTime(inputTime, _currRestStartTime);//在休息下班
                String compareRestEndTime = compareDateTime(inputTime, _currRestEndTime);//判断是休息上班
                if ("1".equals(compareRestStartTime)) {//在上午上班的时间
                    long chaRestStartTime = getDiffDateTime(inputTime, _currRestStartTime) / 60;//距离上午下班的时间
                    int temp_chaEndTime = (int) (overMinute + restLength);//
                    if (chaRestStartTime < overMinute) {
                        if (chaEndTime >= temp_chaEndTime) {
                            overMinute = overMinute + restLength;
                        } else {
                            days++;
                            second = "00";
                            currTime = _currStartTime;
                            overMinute = (int) (temp_chaEndTime - chaEndTime);
                        }
                    }
                } else {
                    if ("1".equals(compareRestEndTime)) {//在中午休息
                        if (pmMiCount < overMinute) {
                            days++;
                            second = "00";
                            currTime = _currStartTime;
                            overMinute -= pmMiCount;
                            if (overMinute > amMiCount) {
                                overMinute += restLength;
                            }
                        } else {
                            second = "00";
                            currTime = _currRestEndTime;
                        }
                    } else {
                        if (chaEndTime < overMinute) {
                            second = "00";
                            currTime = _currStartTime;
                            days++;
                            overMinute -= chaEndTime;
                            if (overMinute > amMiCount) {
                                overMinute += restLength;
                            }

                        }
                    }
                }
            } else {//在下班之后
                days++;
                second = "00";
                currTime = _currStartTime;
                if (amMiCount < overMinute) {
                    overMinute += restLength;
                }
            }
        }

        Date date = null;
        try {
            date = yMdHms.parse(currTime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, overMinute);// 24小时制
        while (days > 0) {
            days--;
            cal.add(Calendar.DATE, 1);// 1天
            if (!isWeekday(cal)) {
                days++;
            }
        }
        ;
        //将秒计算在内
        if ("00".equals(currTime.substring(currTime.lastIndexOf(":") + 1))) {
            cal.add(Calendar.SECOND, Integer.parseInt(second)); //秒计算
            if (!"00".equals(second)) {
                cal.add(Calendar.MINUTE, -1);
            }
        } else {
            cal.add(Calendar.MINUTE, -1);
        }

        date = cal.getTime();
        cal = null;
        return dateFormat.format(date);
    }

    /**
     * @param time 日期 2015-04-14 12:10:20
     * @title 根据传入日期设置相关时长参数
     */
    public static void setWorkLength(String time) {
        if (null == time || "".equals(time) || time.length() != 19) {
            time = yMdHms.format(new Date());
        }
        inputTime = time;
        second = time.substring(17, 19);
        inTime = time.substring(11, 16);
        inDate = time.substring(0, 10);

        String _startTime = inDate + " " + startTime;
        String _restStartTime = inDate + " " + restStartTime;
        String _restEndTime = inDate + " " + restEndTime;
        String _endTime = inDate + " " + endTime;

        //获取上午上班时长
        amMiCount = (int) (getDiffDateTime(_startTime, _restStartTime) / 60);
        //获取下午上班时长
        pmMiCount = (int) (getDiffDateTime(_restEndTime, _endTime) / 60);
        //中午休息时长
        restLength = (int) (getDiffDateTime(_restStartTime, _restEndTime) / 60);
        //总上班时长
        workLength = amMiCount + pmMiCount;
    }

    /**
     * @param beginDateTime 日期 2015-04-14 12:10:20
     * @param endDateTime   日期 2015-04-14 12:10:20
     * @return
     */
    public static String compareDateTime(String beginDateTime, String endDateTime) {
        String rlt = "0";
        try {
            Date d1 = yMdHms.parse(beginDateTime);
            Date d2 = yMdHms.parse(endDateTime);
            if (d1.getTime() > d2.getTime()) {
                rlt = "-1";
            } else if (d1.getTime() <= d2.getTime()) {
                rlt = "1";
            }
        } catch (Exception exception) {
            rlt = "9";
        }
        return rlt;
    }

    /**
     * @param beginDateTime
     * @param endDateTime
     * @return
     */
    public static long getDiffDateTime(String beginDateTime, String endDateTime) {
        long count = 0;
        if (null != beginDateTime && null != endDateTime && beginDateTime.length() == 19 && endDateTime.length() == 19) {
            try {
                Date begin = yMdHms.parse(beginDateTime);
                Date end = yMdHms.parse(endDateTime);
                count = (end.getTime() - begin.getTime()) / 1000;
            } catch (ParseException e) {
                return count;
            }
        }
        return count;
    }

    /**
     * @param calendar
     * @return
     */
    private static boolean isWeekday(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (calendar.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SATURDAY
                && calendar.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY) {
            //平时
            return !holiDayList.contains(sdf.format(calendar.getTime()));
        } else {
            //周末
            return workDayList.contains(sdf.format(calendar.getTime()));
        }
    }

    public void setDateConfigData(String latn_id) {
        Map reqMap = new HashMap();
        reqMap.put("nowDate", yMd.format(new Date()));
        reqMap.put("latn_id", latn_id);
        Map<String, Object> rltMap = this.getDateConfigData(reqMap);
        if ("0".equals(rltMap.get("code"))) {
            List<Map> tempHoliDayList = (List<Map>) rltMap.get("holiDayList");
            if (null != tempHoliDayList && tempHoliDayList.size() > 0)
                for (Map obj : tempHoliDayList) {
                    holiDayList.add(obj.get("dateStr"));
                }
            List<Map> tempWorkDayList = (List<Map>) rltMap.get("workDayList");
            if (null != tempWorkDayList && tempWorkDayList.size() > 0)
                for (Map obj : tempWorkDayList) {
                    workDayList.add(obj.get("dateStr"));
                }

            List<Map> timeData = (List<Map>)rltMap.get("timeData");
            startTime = String.valueOf(timeData.get(0).get("startTime"));
            restStartTime = String.valueOf(timeData.get(0).get("restStartTime"));
            restEndTime = String.valueOf(timeData.get(0).get("restEndTime"));
            endTime = String.valueOf(timeData.get(0).get("endTime"));
        }
    }

    /**
     * @param param
     * @return
     */
    public Map<String, Object> getDateConfigData(Map<String, Object> param) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            List holiDayListMap = weekdayUtil.searchService.queryHoliDayList(param);
            List workDayListMap = weekdayUtil.searchService.queryWorkDayList(param);
            List timeData = weekdayUtil.searchService.queryTimeData(param);
            resultMap.put("code", Const.SUCCESS);
            resultMap.put("msg", "成功");
            resultMap.put("holiDayList", holiDayListMap);
            resultMap.put("workDayList", workDayListMap);
            resultMap.put("timeData", timeData);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", Const.FAIL_SQL);
            resultMap.put("msg", "系统异常" + e.getMessage());
            log.error("获取日期相关数据异常！", e);
        }
        return resultMap;
    }
}
