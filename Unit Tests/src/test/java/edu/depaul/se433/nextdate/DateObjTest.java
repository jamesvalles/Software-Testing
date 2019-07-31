package edu.depaul.se433.nextdate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

/**
 * This class is used to test whether or not next date method functions correctly by checking whether
 * next date generated is correct and IllegalArgumentException is thrown for invalid inputs.
 */


public class DateObjTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  @DisplayName("Returns next day in simplest case")
  void simplestNextDate() {
    DateObj today = new DateObj(2019, 3, 21);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2019, 3, 22), nextday);
  }

  @Test
  @DisplayName("Returns next day in simplest case in future year")
  void simplestNextDateInFuture() {
    DateObj today = new DateObj(2022, 7, 9);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2022, 7, 10), nextday);
  }

  @Test
  @DisplayName("Returns next day in simplest case in past year")
  void simplestNextDateInPast() {
    DateObj today = new DateObj(2016, 5, 4);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2016, 5, 5), nextday);
  }

  @Test
  @DisplayName("Returns next day in next year from a past year (Y2K Bug)")
  void testGoingBack() {
    DateObj today = new DateObj(1999, 12, 31);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2000, 1, 1), nextday);
  }

  @Test
  @DisplayName("Returns correct next day in next year going into leap year ")
  void leapYearInTest() {
    DateObj today = new DateObj(2019, 12, 31);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2020, 1, 1), nextday);
  }


  @Test
  @DisplayName("Returns correct next day in next year going out of leap year")
  void leapYearOutTest() {
    DateObj today = new DateObj(2020, 12, 31);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2021, 1, 1), nextday);
  }

  @Test
  @DisplayName("Returns correct next day in next month from a leap day in leap year")
  void leapYearNextDate() {
    DateObj today = new DateObj(2020, 2, 29);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2020, 3, 1), nextday);
  }

  @Test
  @DisplayName("Returns correct next day in next month for April with 30 days")
  void april30Days() {
    DateObj today = new DateObj(2020, 4, 30);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2020, 5, 1), nextday);
  }

  @Test
  @DisplayName("Returns correct next day in next month for June with 30 days")
  void june30Days() {
    DateObj today = new DateObj(2019, 6, 30);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2019, 7, 1), nextday);
  }

  @Test
  @DisplayName("Returns correct next day in next month for November with 30 days")
  void november30Days() {
    DateObj today = new DateObj(2020, 11, 30);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2020, 12, 1), nextday);
  }

  @Test
  @DisplayName("Returns correct next day in next month for Sept with 30 days")
  void sept30Days() {
    DateObj today = new DateObj(2020, 9, 30);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2020, 10, 1), nextday);
  }


  @Test
  @DisplayName("Returns correct next day in next month from leap day to non-leap day in past leap year")
  void leapYearNextDatePast() {
    DateObj today = new DateObj(2016, 2, 29);
    DateObj nextday = today.nextDate();
    assertEquals(new DateObj(2016, 3, 1), nextday);
  }


  @Test
  @DisplayName("Returns exception for invalid input of day 29 during non-leap year.")
  void testInvalidDateInput() {
    try {
      DateObj today = new DateObj(2019, 2, 29);
      DateObj nextday = today.nextDate();
      fail("Exception should have occurred.");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), null);
    }
  }

  @Test
  @DisplayName("Returns exception when invalid input year of 0 entered")
  void testInvalidZeroYear() {
    try {
      DateObj today = new DateObj(2019, 2, 0);
      DateObj nextday = today.nextDate();
      fail("Exception should have occurred.");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), null);
    }
  }


  @Test
  @DisplayName("Returns exception when invalid input month of 0 entered")
  void testInvalidZeroMonth() {
    try {
      DateObj today = new DateObj(2019, 0, 20);
      DateObj nextday = today.nextDate();
      fail("Exception should have occurred.");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), null);
    }
  }

  @Test
  @DisplayName("Returns exception when invalid input of negative year entered")
  void testInvalidNegativeYear() {
    try {
      DateObj today = new DateObj(-2019, 10, 20);
      DateObj nextday = today.nextDate();
      fail("Exception should have occurred.");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), null);
    }
  }

  @Test
  @DisplayName("Returns exception when invalid input of negative month entered")
  void testInvalidNegativeMonth() {
    try {
      DateObj today = new DateObj(2019, -10, 20);
      DateObj nextday = today.nextDate();
      fail("Exception should have occurred.");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), null);
    }
  }


  @Test
  @DisplayName("Returns exception when invalid input of negative day entered")
  void testInvalidNegativedDay() {
    DateObj today = new DateObj(2019, 10, -20);
    assertThrows(IllegalArgumentException.class, () -> today.nextDate());
  }

  @Test
  @DisplayName("Returns exception when invalid input of month exceeds max number of 12 months")
  void testInvalidMonthOverMax() {
    try {
      DateObj today = new DateObj(2019, 15, 20);
      DateObj nextday = today.nextDate();
      fail("Exception should have occurred.");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), null);
    }
  }

  @Test
  @DisplayName("Returns exception when invalid input of month exceeds max number of 31 months")
  void testInvalidDayOverMax() {
    DateObj today = new DateObj(2019, 1, 40);
    assertThrows(IllegalArgumentException.class, () -> today.nextDate());
  }

  @Test
  @DisplayName("Returns exception when invalid input of month exceeds max number of 31 months")
  void testAprilInvalid31Day() {
    DateObj today = new DateObj(2019, 4, 31);
    assertThrows(IllegalArgumentException.class, () -> today.nextDate());
  }

  @Test
  @DisplayName("Returns true if both objects are equal when equals method is invoked.")
  void testEqualsMethod() {
    DateObj date1 = new DateObj(2019, 12, 31);
    DateObj date2 = new DateObj(2019, 12, 31);
    Boolean result = date1.equals(date2);
    assertEquals(true, result);
  }


}
