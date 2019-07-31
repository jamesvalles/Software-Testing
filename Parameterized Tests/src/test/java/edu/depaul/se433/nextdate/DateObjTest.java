package edu.depaul.se433.nextdate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class is used to parameterized test whether or not next date method functions correctly by
 * checking whether next date generated is correct.
 */


@RunWith(Enclosed.class)
public class DateObjTest {

  @RunWith(Parameterized.class)
  public static class testNextDateFunction {

    @Parameters
    public static Collection parameters() {
      return Arrays.asList(new Object[][]{
          {new DateObj(2022, 7, 10), new DateObj(2022, 7, 9)},
          {new DateObj(2016, 5, 5), new DateObj(2016, 5, 4)},
          {new DateObj(2000, 1, 1), new DateObj(1999, 12, 31)},
          {new DateObj(2020, 1, 1), new DateObj(2019, 12, 31)},
          {new DateObj(2020, 3, 1), new DateObj(2020, 2, 29)},
          {new DateObj(2020, 5, 1), new DateObj(2020, 4, 30)},
          {new DateObj(2019, 7, 1), new DateObj(2019, 6, 30)},
          {new DateObj(2020, 12, 1), new DateObj(2020, 11, 30)},
          {new DateObj(2020, 10, 1), new DateObj(2020, 9, 30)},
          {new DateObj(2016, 3, 1), new DateObj(2016, 2, 29)},
      });
    }

    private DateObj expected;
    private DateObj actual;

    public testNextDateFunction(DateObj expected, DateObj actual) {
      this.expected = expected;
      this.actual = actual;
    }

    @Test
    @DisplayName("Returns correct next day")
    public void testNextDate() {
      DateObj nextday = this.actual.nextDate();
      assertEquals(this.expected, nextday);
    }
  }


  /**
   * This class is used to parameterized test whether or not DateObj Constructor functions
   * correctly, if invalid throw IllegalArgumentException.
   */

  @RunWith(Parameterized.class)
  public static class testDateObjConstructor {

    @Parameters
    public static Collection parameters() {
      return Arrays.asList(new Object[][]{
          {IllegalArgumentException.class, 0, 12, 9}, //test to check for year 0.
          {IllegalArgumentException.class, 2009, 00, 9},
          {IllegalArgumentException.class, 2009, 3, 32},
          {IllegalArgumentException.class, 2022, -1, 9},
          {IllegalArgumentException.class, 2019, 2, 29},
          {IllegalArgumentException.class, 2019, 2, 0},
          {IllegalArgumentException.class, 2019, 0, 20},
          {IllegalArgumentException.class, 2019, 0, 20},
          {IllegalArgumentException.class, -2019, 10, 20},
          {IllegalArgumentException.class, 2019, 10, -20},
          {IllegalArgumentException.class, 2019, 15, 20},
          {IllegalArgumentException.class, 2019, 1, 40},
          {IllegalArgumentException.class, 2019, 4, 31},
          //   {IllegalArgumentException.class, "2019", 4, 31},
          //   {IllegalArgumentException.class, 2019, "4", 31},
          //   {IllegalArgumentException.class, 2019, "4", "31"},

      });
    }

    private Class expected;
    private int year;
    private int month;
    private int day;

    public testDateObjConstructor(Class expected, int year, int month, int day) {
      this.expected = expected;
      this.year = year;
      this.month = month;
      this.day = day;
    }

    @Test
    @DisplayName("Returns exception when invalid input entered in DateObj Constructor")
    public void testInvalidDateInputs() {
      assertThrows(expected, () -> new DateObj(year, month, day));
    }
  }
}