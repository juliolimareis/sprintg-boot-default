package com.alerta.dc.util;

import java.util.Date;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class SystemDate {
	public String getDatetimeNowPtBr() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formatter.format(date);
	}

	public Timestamp parseTimestamp(String date){
		return java.sql.Timestamp.valueOf(date);
	}

	public boolean isTimestamp(String date){
		try {
			java.sql.Timestamp.valueOf(date);
			return true;
		} catch (Exception e) {
			//handle exception
		}
		return false;
	}

	public String getDateNow(String pattern) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public String parseDatetimePtToUs(String datetime) throws Exception {
		String[] arrDatetime = datetime.split(" ");
		return parseDatePtToUs(arrDatetime[0]).concat(" ").concat(arrDatetime[1]);
	}

	public String parseDatePtToUs(String date) throws Exception {
		if (date != null && !date.isEmpty()) {
			if (date.length() == 10 && date.contains("/")) {
				String[] arrDate = date.split("/");
				if (arrDate.length == 3) {
					return arrDate[2].concat("-".concat(arrDate[1]).concat("-")).concat(arrDate[0]);
				}
			}
			throw new Exception("PT date format is invalid");
		}
		return date;
	}

	public String parseDateUsToPt(String date) throws Exception {
		if (date != null && !date.isEmpty()) {
			if (date.length() == 10 && date.contains("-")) {
				String[] arrDate = date.split("-");
				if (arrDate.length == 3) {
					return arrDate[0].concat("/".concat(arrDate[1]).concat("/")).concat(arrDate[2]);
				}
			}
			throw new Exception("US date format is invalid.");
		}
		return date;
	}

	public boolean isDatePt(String date) {
		if (date != null && !date.isEmpty()) {
			if (date.length() == 10) {
				String[] arrDate = date.split("/");
				if (arrDate.length == 3) {
					if (arrDate[0].length() == 2 && arrDate[1].length() == 2 && arrDate[2].length() == 4) {
						for (int i = 0; i < arrDate.length; i++) {
							if (!isInteger(arrDate[i])) {
								return false;
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isDateUs(String date) {
		if (date != null && !date.isEmpty()) {
			if (date.length() == 10) {
				String[] arrDate = date.split("-");
				if (arrDate.length == 3) {
					if (arrDate[0].length() == 4 && arrDate[1].length() == 2 && arrDate[2].length() == 2) {
						for (int i = 0; i < arrDate.length; i++) {
							if (!isInteger(arrDate[i])) {
								return false;
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isTime(String time) {
		if (time != null && !time.isEmpty()) {
			if (time.length() == 8) {
				String[] arrTime = time.split(":");
				if (arrTime.length == 3) {
					if (arrTime[0].length() == 2 && arrTime[1].length() == 2 && arrTime[2].length() == 2) {
						for (int i = 0; i < arrTime.length; i++) {
							if (!isInteger(arrTime[i])) {
								return false;
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isNumeric(String number) {
		if (number == null || number.equals("")) {
			// System.out.println("String cannot be parsed, it is null or empty.");
			return false;
		}

		try {
			if (number.contains(".")) {
				Float.parseFloat(number + "f");
			} else {
				Integer.parseInt(number);
			}
			return true;
		} catch (NumberFormatException e) {
			// System.out.println("Input String cannot be parsed to Integer.");
		}
		return false;
	}

	public boolean isInteger(String number) {
		if (number == null || number.equals("")) {
			// System.out.println("String cannot be parsed, it is null or empty.");
			return false;
		}

		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			// System.out.println("Input String cannot be parsed to Integer.");
		}
		return false;
	}

	public boolean isFloat(String number) {
		if (number == null || number.equals("")) {
			// System.out.println("String cannot be parsed, it is null or empty.");
			return false;
		}

		try {
			Float.parseFloat(number + "f");
			return true;
		} catch (NumberFormatException e) {
			// System.out.println("Input String cannot be parsed to Integer.");
		}
		return false;
	}

	public Date getDate(String date) throws Exception {
		if (isDatePt(date)) {
			return java.sql.Date.valueOf(parseDatePtToUs(date));
		} else if (isDateUs(date)) {
			return java.sql.Date.valueOf(date);
		}

		return null;
	}
}
