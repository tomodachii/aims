package utils;

public class DateUtil {
	//return > 0 if d1 > d2
	//return 0 if d1 == d2
	//return < 0 if d1 < d2
	public static int compare(MyDate d1, MyDate d2) {
		if (d1.getYear() == d2.getYear()) {
			if (d1.getMonth() == d2.getMonth()) {
				return d1.getDay() - d2.getDay();
			} else {
				return d1.getMonth() - d2.getMonth();
			}
		} else {
			return d1.getYear() - d2.getYear();
		}
	}
	
	public static void sort(MyDate ...dateList) {
		int tmpDay, tmpMonth, tmpYear;
		 int n = dateList.length;
	        for (int i = 0; i < n-1; i++)
	            for (int j = 0; j < n-i-1; j++)
	                if (compare(dateList[j], dateList[j + 1]) > 0) {
	                	tmpYear = dateList[j].getYear();
                        tmpMonth = dateList[j].getMonth();
                        tmpDay = dateList[j].getDay();

                        dateList[j].setYear(dateList[j + 1].getYear());
                        dateList[j].setMonth(dateList[j + 1].getMonth());
                        dateList[j].setDay(dateList[j + 1].getDay());

                        dateList[j + 1].setYear(tmpYear);
                        dateList[j + 1].setMonth(tmpMonth);
                        dateList[j + 1].setDay(tmpDay);
	                }
	}
}
