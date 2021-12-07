package covergenius.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilities {

	public Utilities() {

	}

	public static String addDaysToCurrentDate(int days) {

		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		Date currentDate = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DAY_OF_MONTH, days);
		Date currentDatePlusDays = c.getTime();

		return dateFormat.format(currentDatePlusDays).substring(0, 2);
	}
	
	

}