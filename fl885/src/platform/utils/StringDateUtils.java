package platform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * <p>
 * String���ͺ�Data���ͻ���ת���Ĺ�����
 * </p>
 * 
 * @author PP
 * @since 2014-06-17
 */
public class StringDateUtils {

	private static final Logger logger = Logger
			.getLogger(StringDateUtils.class);

	private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";

	public static Date getDateByString(String date, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error("ʱ������ת������");
			e.printStackTrace();
			return null;
		}

	}

	public static String getStringByDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date getDateByString(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error("ʱ������ת������");
			e.printStackTrace();
			return null;
		}

	}

	public static String getStringByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
		return sdf.format(date);
	}

	public static int getMonth(Date date) {
		Calendar cal = setCalendar(date);
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	public static int getDayInMonth(Date date) {
		Calendar cal = setCalendar(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static int getDayInWeek(Date date) {
		Calendar cal = setCalendar(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	public static long getTimeInMillis(Date date) {
		Calendar cal = setCalendar(date);
		long time = cal.getTimeInMillis();
		return time;
	}

	/**
	 * <p>
	 * ��ȡ����ʱ��֮���������ڵļ��� ex.
	 * ["2014-01-01","2014-01-02","2014-01-03",...,"2014-04-03","2014-04-04"]
	 * </p>
	 * 
	 * @param startDate
	 * @param endDate
	 * @return List<Date>
	 */
	public static List<Date> getTotalDate(Date startDate, Date endDate) {

		List<Date> resultList = new ArrayList<Date>();
		double between = (getTimeInMillis(endDate) - getTimeInMillis(startDate)) / 1000; // ��ȡ����ʱ����������
		double day = between / (24 * 3600); // �������ʱ���������

		for (int i = 1; i < day; i++) {
			Calendar cal = setCalendar(startDate);
			cal.add(Calendar.DATE, i);// ����һ��
			resultList.add(cal.getTime());
		}

		logger.info("��������֮�����" + resultList.size() + "��");
		return resultList;

	}

	/**
	 * <p>
	 * ��ȡ����ʱ��֮�乤���յ�����
	 * </p>
	 * 
	 * @param startDate
	 * @param endDate
	 * @return Integer
	 */
	public static int getDutyDays(Date startDate, Date endDate) {

		int result = 0;
		Date date = startDate;
		while (date.compareTo(endDate) <= 0) {
			if (getDayInWeek(date) != 6 && getDayInWeek(date) != 0) {
				result++;
				Calendar cal = setCalendar(date);
				cal.add(Calendar.DATE, 1);
				date = cal.getTime();
			}
		}
		return result;

	}

	private static Calendar setCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

}
