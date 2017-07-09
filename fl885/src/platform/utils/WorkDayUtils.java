package platform.utils;

import gov.util.jpa.BaseJpaDao;
import gov.util.jpa.impl.BaseJpaServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkDayUtils extends BaseJpaServiceImpl{
	
	public static String formatStr = "yyyy/M/dd";

	
	public String getDeadlineWorkday(Date date, int day)
	  {
	    String deadline = null;
	    try {
	      SimpleDateFormat format = new SimpleDateFormat(formatStr);
	      
	      String startDay = format.format(date);
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(date);
	      calendar.add(5, day);
	      Date endDate = calendar.getTime();
	      String endDay = format.format(endDate);
	      
	      int holidayCount = getHolidayDays(startDay, endDay);
	      
	      if (!isWorkday(startDay)) {
	        holidayCount -= 1;
	      }
	      if (holidayCount > 0) {
	        deadline = getDeadlineWorkday(endDate, holidayCount);
	      }
	      else
	        deadline = format.format(endDate);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return deadline;
	  }
	
	  public boolean isWorkday(String day)
	  {
	    String sql = "select * from GOV_HOLIDAY  where DAY='" + day +"'";
	    List<Object> result = this.executeSqlQuery(sql, null);
	    if (result != null&&result.size()!=0) {
	      return false;
	    }
	    return true;
	  }
	  
	  public int getHolidayDays(String startDay, String endDay)
	  {
	    String sql = "select count(*) from GOV_HOLIDAY  where DAY>='" + startDay + "' and DAY<='" + endDay + "'";
        List<Object> result = this.executeSqlQuery(sql, null);
        Object num = result.get(0);
	    return Integer.valueOf(num.toString());
	  }

	@Override
	public BaseJpaDao getJpaDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
