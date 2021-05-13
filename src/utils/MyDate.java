package utils;
import java.util.Calendar;
import java.util.TimeZone;

//import javax.management.BadBinaryOpValueExpException;

import java.util.Scanner;

public class MyDate {
	private int day;
	private int month;
	private int year;
	
	private final String[] monthList = { 
			"",
			"January", 
			"February", 
			"March", 
			"April",
			"May", 
			"Jun", 
			"July", 
			"August",
			"September", 
			"October", 
			"November", 
			"December" 
	};
	private final String[] monthListAb = { 
			"",
			"Jan", 
			"Feb", 
			"Mar", 
			"Apr", 
			"May", 
			"Jun", 
			"Jul", 
			"Aug", 
			"Sep", 
			"Oct",
			"Nov", 
			"Dec" 
	};
	private static final String[] tensNames = {
			"hundred",
		    "ten",
		    "twenty",
		    "thirty",
		    "forty",
		    "fifty",
		    "sixty",
		    "seventy",
		    "eighty",
		    "ninety",
		    ""
	};
	private static final String[] numNames = {
		    "",
		    "one",
		    "two",
		    "three",
		    "four",
		    "five",
		    "six",
		    "seven",
		    "eight",
		    "nine",
		    "ten",
		    "eleven",
		    "twelve",
		    "thirteen",
		    "fourteen",
		    "fifteen",
		    "sixteen",
		    "seventeen",
		    "eighteen",
		    "nineteen",
	};
	private static final String[] OrdinalNum = {
			"",
			"first",
			"second",
			"third",
			"fourth",
			"fifth",
			"sixth",
			"seventh",
			"eighth",
			"ninth",
			"tenth",
			"eleventh",
			"twelfth",
			"thirteenth",
			"fourteenth",
			"fifteenth",
			"sixteenth",
			"seventeenth",
			"eighteenth",
			"nineteenth",
			"twentieth",
			"twenty-first",
			"twenty-second",
			"twenty-third",
			"twenty-fourth",
			"twenty-fifth",
			"twenty-sixth",
			"twenty-seventh",
			"twenty-eighth",
			"twenty-ninth",
			"thirtieth",
			"thirty-first"
	};
	
	public void processString(String str) {
		String[] parts = str.split(" ");
		int[] result = new int[3];
		result[0] = Integer.parseInt(parts[2]);
		
		switch (parts[0]) {
		case "January":
		case "Jan.":
		case "Jan":
		case "1":
		case "01":
			result[1] = 1;
			break;
		case "February":
		case "Feb.":
		case "Feb":
		case "2":
		case "02":
			result[1] = 2;
			break;
		case "March":
		case "Mar.":
		case "Mar":
		case "3":
		case "03":
			result[1] = 3;
			break;
		case "April":
		case "Apr.":
		case "Apr":
		case "4":
		case "04":
			result[1] = 4;
			break;
		case "May":
		case "5":
		case "05":
			result[1] = 5;
			break;
		case "June":
		case "Jun":
		case "6":
		case "06":
			result[1] = 6;
			break;
		case "July":
		case "Jul.":
		case "Jul":
		case "7":
		case "07":
			result[1] = 7;
			break;
		case "August":
		case "Aug.":
		case "Aug":
		case "8":
		case "08":
			result[1] = 8;
			break;
		case "September":
		case "Sept.":
		case "Sep":
		case "9":
		case "09":
			result[1] = 9;
			break;
		case "October":
		case "Oct.":
		case "Oct":
		case "10":
			result[1] = 10;
			break;
		case "November":
		case "Nov.":
		case "Nov":
		case "11":
			result[1] = 11;
			break;
		case "December":
		case "Dec.":
		case "Dec":
		case "12":
			result[1] = 12;
			break;
		default: {
			result[1] = 0;
		}
		}
		
		int len = parts[1].length();
		if (len == 1 || len == 2) {
			result[2] = Integer.parseInt(parts[1]);
		} else if (len == 3 || len == 4) {
			result[2] = Integer.parseInt(parts[1], 0, len - 2, 10);
		} else {
			result[2] = -1;
		}
		if (!setYear(result[0]) || !setMonth(result[1]) || !setDay(result[2])) {
			setCurrentDate();
		}
	}
	
	private int findNum(String str) {
		int i = 0;
		for (i = 0; i <= 19; i++) {
			if (numNames[i].equals(str)) {
				return i;
			}
		}
		for (i = 0; i <= 10; i++) {
			if (tensNames[i].equals(str)) {
				return i * 10;
			}
		}
		return -1;
	}
	
	private int splitYearSubtract(String str) {
		String[] parts = str.split("-");
		int len = parts.length;
		if (len == 1) {
			return findNum(parts[0]);
		} else if (len == 2) {
			return findNum(parts[0]) + findNum(parts[1]);
		}
		return -1;
	}
	
	private int parseYear(String yearStr) {
		String str = yearStr;
		if (yearStr.contains("and")) {
			str = yearStr.replace(" and", "");
		}
		String[] parts = str.split(" ");
		int len = parts.length;
		if (len == 1) {
			return splitYearSubtract(parts[0]);
		} else if (len == 2) {
			return splitYearSubtract(parts[0]) * 100 + splitYearSubtract(parts[1]);
		} else if (len == 3) {
			int a = splitYearSubtract(parts[0]);
			int b = splitYearSubtract(parts[2]);
			if (parts[1].equals("thousand")) {
				return a * 1000 + b;
			} else if (parts[1].equals("hundred")) {
				return a * 100 + b;
			}
		}
		return -1;
	}
	
	private void processString(String dayStr, String monthStr, String yearStr) {
		int[] result = new int[3];
		result[0] = parseYear(yearStr);
		
		switch (monthStr) {
		case "January":
		case "Jan.":
		case "Jan":
		case "1":
		case "01":
			result[1] = 1;
			break;
		case "February":
		case "Feb.":
		case "Feb":
		case "2":
		case "02":
			result[1] = 2;
			break;
		case "March":
		case "Mar.":
		case "Mar":
		case "3":
		case "03":
			result[1] = 3;
			break;
		case "April":
		case "Apr.":
		case "Apr":
		case "4":
		case "04":
			result[1] = 4;
			break;
		case "May":
		case "5":
		case "05":
			result[1] = 5;
			break;
		case "June":
		case "Jun":
		case "6":
		case "06":
			result[1] = 6;
			break;
		case "July":
		case "Jul.":
		case "Jul":
		case "7":
		case "07":
			result[1] = 7;
			break;
		case "August":
		case "Aug.":
		case "Aug":
		case "8":
		case "08":
			result[1] = 8;
			break;
		case "September":
		case "Sept.":
		case "Sep":
		case "9":
		case "09":
			result[1] = 9;
			break;
		case "October":
		case "Oct.":
		case "Oct":
		case "10":
			result[1] = 10;
			break;
		case "November":
		case "Nov.":
		case "Nov":
		case "11":
			result[1] = 11;
			break;
		case "December":
		case "Dec.":
		case "Dec":
		case "12":
			result[1] = 12;
			break;
		default: {
			result[1] = -1;
		}
		}
		for (int i = 0; i < OrdinalNum.length; i++) {
			if (OrdinalNum[i].equals(dayStr)) {
				result[2] = i;
				break;
			}
		}
		if (!setYear(result[0]) || !setMonth(result[1]) || !setDay(result[2])) {
			setCurrentDate();
		}
	}
	
	public int getDay() {
		return day;
	}
	public boolean setDay(int day) {
		int daysInMonth = 0;
		int month = getMonth();
		int year = getYear();
		if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				daysInMonth = 29;
			else 
				daysInMonth = 28;
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8|| month == 10 ||
				month == 12) {
			daysInMonth = 31;
		} else {
			daysInMonth = 30;
		}
		
		if (day < 1 || day > daysInMonth) {
			System.out.println("Invalid input (day)");
			return false;
		} else { 
			this.day = day;
			return true;
		}
	}
	public int getMonth() {
		return month;
	}
	public boolean setMonth(int month) {
		if (month < 1 || month > 12) {
			System.out.println("Invalid input (month)");
			return false;
		} else {
			this.month = month;
			return true;
		}
			
	}
	public int getYear() {
		return year;
	}
	public boolean setYear(int year) {
		if (year < 0 || year > 9999) {
			System.out.println("Invalid input (year)");
			return false;
		} else {
			this.year = year;
			return true;
		}
	}
	public void print() {
		int len = OrdinalNum[day].length();
		String tmp = OrdinalNum[day].substring(len - 2);
		System.out.println(monthList[month] + " " + day + tmp + " " + this.year);
	}
	public void accept() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the date in format : month day year(Ex: November 23th 2020)");
		String result = scanner.nextLine();
		processString(result);
		scanner.close();
	}
	private void setCurrentDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		day = calendar.get(Calendar.DATE);
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);
	}
	public void printWithDifferentFormats() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose the format:");
		System.out.println("1. yyyy-MM-dd");
		System.out.println("2. d/M/yyyy");
		System.out.println("3. dd-MMM-yyyy");
		System.out.println("4. MMM d yyyy");
		System.out.println("5. mm-dd-yyyy");
		System.out.println("6. Quit");
		System.out.print("Choose your option: ");
		int c = 0;
		do {
			c = scanner.nextInt();
			switch (c) {
			case 1: {
				System.out.printf("%d-%d-%d\n", getYear(), getMonth(), getDay());
				break;
			}
			case 2: {
				System.out.printf("%d/%d/%d\n", getDay(), getMonth(), getYear());
				break;
			}
			case 3: {
				System.out.printf("%d-%d-%d\n", getDay(), getMonth(), getYear());
				break;
			}
			case 4: {
				System.out.printf("%d %d %d\n", getMonth(), getDay(), getYear());
				break;
			}
			case 5: {
				System.out.printf("%d-%d-%d\n", getMonth(), getDay(), getYear());
				break;
			}
			case 6: {
				scanner.close();
				break;
			}
			default:
				System.out.println("Invalid input! Please choose from 1 to 6");
			}
		} while (c < 1 || c > 6);
	}
	public MyDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		this.day = calendar.get(Calendar.DATE);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.year = calendar.get(Calendar.YEAR);
	}
	public MyDate(int day, int month, int year) {
		super();
		if(!setYear(year) || !setMonth(month) || !setDay(day)) {
			System.out.println("Set to current date !");
			setCurrentDate();
		}
	}
	public MyDate(String str) {
		// month day year
		processString(str);
	}
	public MyDate(String day, String month, String year) {
		processString(day, month, year);
	}
	public MyDate(MyDate dateTemp) {
		if (!setYear(dateTemp.getYear()) || !setMonth(dateTemp.getMonth()) || 
				!setDay(dateTemp.getDay()) ) {
			setCurrentDate();
		}
	}
}
