package gntp.lesson.guestbook.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeService {
	public static final int DATE_ONLY = 0;
	public static final int TIME_ONLY = 1;
	public static final int DATE_TIME = 2;
	
	public static String getDateTime(int type) {
		String now = null;
		// 요청에 따라 날짜, 시간 , 날짜 + 시간 정보를 제공한다.
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		now = sdf.format(new Date());
		if (type==0) //날짜 제공
		{
			String[] temp = now.split(" ");
			now = temp[0];
		} 
		else if(type==1) //시간 제공
		{
			now = now.split(" ")[1];
		}
		
		return now;
	}
	
	public static String getDateTime(int type, Date date) {
		String now = null;
		// 요청에 따라 날짜, 시간 , 날짜 + 시간 정보를 제공한다.
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		now = sdf.format(date);
		if (type==0) //날짜 제공
		{
			String[] temp = now.split(" ");
			now = temp[0];
		} 
		else if(type==1) //시간 제공
		{
			now = now.split(" ")[1];
		}
		
		return now;
	}
	
	public String getDateTimeV2(DateTimeServiceType type) {
		String now = null;
		// 요청에 따라 날짜, 시간 , 날짜 + 시간 정보를 제공한다.
		String pattern = "yyyy년MM월dd일 HH시mm분ss초";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		now = sdf.format(new Date());
		if (type==DateTimeServiceType.DATE_ONLY) //날짜 제공
		{
			String[] temp = now.split(" ");
			now = temp[0];
		} 
		else if(type==DateTimeServiceType.TIME_ONLY) //시간 제공
		{
			now = now.split(" ")[1];
		}
		
		return now;
	}
}
