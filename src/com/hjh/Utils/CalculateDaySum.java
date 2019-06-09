package com.hjh.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculateDaySum
{
	public int calculate(String str1,String str2)
	{
		String format="yyyy-MM-dd";
		try {
			Date date1=new SimpleDateFormat(format).parse(str1);
			Date date2=new SimpleDateFormat(format).parse(str2);
			return sum(date1,date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int sum(Date date1,Date date2)
	{
		Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1)+1 ;
        }
        else    //不同年
        {
            return day2-day1+1;
        }

	}

}
