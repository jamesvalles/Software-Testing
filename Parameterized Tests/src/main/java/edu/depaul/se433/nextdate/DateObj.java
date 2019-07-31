package edu.depaul.se433.nextdate;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is used to create a new date object and compute next date.
 */

public class DateObj {

  private int year;
  private int month;
  private int day;

  public DateObj(int year, int month, int day) {
    if (year < 1) {
      throw new IllegalArgumentException();
    }

    try {
      LocalDate dayAfter = LocalDate.of(year, month, day).plusDays(1);
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }

    this.year = year;
    this.month = month;
    this.day = day;
  }


  public DateObj nextDate() {
    try {
      LocalDate dayAfter = LocalDate.of(year, month, day).plusDays(1);
      return new DateObj(dayAfter.getYear(), dayAfter.getMonthValue(), dayAfter.getDayOfMonth());
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
  }

  public Date asDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month - 1);
    cal.set(Calendar.DAY_OF_MONTH, day);
    Date date = cal.getTime();
    return date;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (!(other instanceof DateObj)) {
      return false;
    }
    DateObj otherDate = (DateObj) other;

    return (otherDate.year == year) && (otherDate.month == month) && (otherDate.day == day);

  }

  @Override
  public String toString() {
    return String.format("Date[year: %d, month: %d, day: %d]", year, month, day);
  }

}
