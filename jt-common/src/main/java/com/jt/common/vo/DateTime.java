package com.jt.common.vo;

import java.util.Date;

/**
 * 时间处理工具
 * 未完成，只是简单返回
 * 
 * @author zain
 * 16/10/15
 */
public class DateTime {

    // TODO all need to modify
    
    private Date nowDate;
    
    public DateTime(Date nowDate) {
        this.nowDate = nowDate;
    }
    
    @Override
    public String toString() {
        return nowDate.getTime() + "";
    }

    public String toString(String string) {
        if("yyyy".equals(string)) {
            return nowDate.getYear() + "";
        }
        if("MM".equals(string)) {
            return nowDate.getMonth() + "";
        }
        if("dd".equals(string)) {
            return nowDate.getDay() + "";
        }

        return "0";
    }

}
