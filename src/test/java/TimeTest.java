/*
  Christopher Cruzen                         LaunchCode CS50
  "TimeTest.java"                                 05.17.2015

  Problem Set #6

    This is the test class for Metrolink's Test.java class.

*/
import com.xephorium.metrolink.database.record.Time;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeTest
{
    //////////////////
    // Test Methods //
    //////////////////

    @Test
    public void constructorShouldOnlyAcceptValidTimeValues()
    {

        /*--- Anticipated Failures ---*/
        assertTrue(constructorGeneratesException(-1, 0, 0));
        assertTrue(constructorGeneratesException(0, -1, 0));
        assertTrue(constructorGeneratesException(0, 0, -1));
        assertTrue(constructorGeneratesException(48, 0, 0));
        assertTrue(constructorGeneratesException(0, 60, 0));
        assertTrue(constructorGeneratesException(0, 0, 60));
        assertTrue(constructorGeneratesException(98, 117, 264));

        /*--- Anticipated Successes ---*/
        assertTrue(constructorGeneratesNoException(0, 0, 0));
        assertTrue(constructorGeneratesNoException(47, 59, 59));
        assertTrue(constructorGeneratesNoException(24, 0, 0));
        assertTrue(constructorGeneratesNoException(18, 30, 0));
        assertTrue(constructorGeneratesNoException(8, 45, 0));

    }

    @Test
    public void toStringShouldReturnValidString()
    {
        /*--- Anticipated Failures ---*/
        assertNotEquals("00:00:00", (new Time(1, 0, 0).toString()));
        assertNotEquals("01:00:00", (new Time(0, 0, 0).toString()));

        /*--- Anticipated Successes ---*/
        assertEquals("00:00:00", (new Time(0, 0, 0).toString()));
        assertEquals("47:59:59", (new Time(47, 59, 59).toString()));
        assertEquals("24:30:05", (new Time(24, 30, 5).toString()));
        assertEquals("24:05:00", (new Time(24, 5, 0).toString()));
        assertEquals("05:00:00", (new Time(5, 0, 0).toString()));
        assertEquals("05:00:00", (new Time(5, 0, 0).toString()));

    }

    @Test
    public void equalsShouldDetermineWhetherTimesAreEqual()
    {
        /*--- Anticipated Failures ---*/
        assertEquals(false, (new Time(0, 0, 0)).equals(new Time(0, 0, 1)));
        assertEquals(false, (new Time(17, 30, 0)).equals(new Time(17, 35, 0)));
        assertEquals(false, (new Time(0, 0, 0)).equals(new Time(25, 0, 0)));
        assertEquals(false, (new Time(2, 45, 0)).equals(new Time(26, 45, 30)));
        assertEquals(false, (new Time(23, 59, 59)).equals(new Time(24, 0, 0)));

        /*--- Anticipated Successes ---*/
        assertEquals(true, (new Time(0, 0, 0)).equals(new Time(0, 0, 0)));
        assertEquals(true, (new Time(17, 30, 0)).equals(new Time(17, 30, 0)));
        assertEquals(true, (new Time(0, 0, 0)).equals(new Time(24, 0, 0)));
        assertEquals(true, (new Time(2, 45, 0)).equals(new Time(26, 45, 0)));
        assertEquals(true, (new Time(23, 59, 59)).equals(new Time(47, 59, 59)));
    }

    @Test
    public void untilShouldReturnAmountOfTimeUntilParameter()
    {
        /*--- Anticipated Failures---*/
        assertFalse(new Time(24, 0, 0).equals(new Time(0, 0, 0).until(new Time(0, 0, 0))));
        assertFalse(new Time(0, 0, 0).equals(new Time(12, 0, 0).until(new Time(14, 0, 0))));
        assertFalse(new Time(12, 0, 0).equals(new Time(23, 59, 59).until(new Time(23, 59, 59))));

        /*--- Anticipated Successes - Times Equal ---*/
        assertTrue(new Time(0, 0, 0).equals(new Time(0, 0, 0).until(new Time(0, 0, 0))));
        assertTrue(new Time(0, 0, 0).equals(new Time(23, 59, 59).until(new Time(23, 59, 59))));
        assertTrue(new Time(0, 0, 0).equals(new Time(0, 0, 0).until(new Time(24, 0, 0))));
        assertTrue(new Time(0, 0, 0).equals(new Time(2, 45, 0).until(new Time(26, 45, 0))));

        /*--- Anticipated Successes - Other ---*/
        assertTrue(new Time(0, 30, 0).equals(new Time(0, 0, 0).until(new Time(0, 30, 0))));
        assertTrue(new Time(23, 30, 0).equals(new Time(0, 30, 0).until(new Time(0, 0, 0))));
        assertTrue(new Time(0, 0, 1).equals(new Time(0, 0, 0).until(new Time(0, 0, 1))));
        assertTrue(new Time(23, 59, 59).equals(new Time(0, 0, 1).until(new Time(0, 0, 0))));
        assertTrue(new Time(10, 0, 0).equals(new Time(5, 0, 0).until(new Time(15, 0, 0))));
        assertTrue(new Time(14, 0, 0).equals(new Time(15, 0, 0).until(new Time(5, 0, 0))));
        assertTrue(new Time(0, 50, 0).equals(new Time(13, 15, 0).until(new Time(14, 5, 0))));
        assertTrue(new Time(23, 10, 0).equals(new Time(14, 5, 0).until(new Time(13, 15, 0))));
        assertTrue(new Time(2, 30, 0).equals(new Time(17, 45, 0).until(new Time(20, 15, 0))));
        assertTrue(new Time(21, 30, 0).equals(new Time(20, 15, 0).until(new Time(17, 45, 0))));
    }

    @Test
    public void laterThanShouldDetermineWhetherPassedTimeEarlier()
    {
        /*--- Anticipated Failures ---*/
        assertEquals(false, (new Time(0, 0, 0)).laterThan(new Time(24, 59, 59)));
        assertEquals(false, (new Time(0, 0, 0)).laterThan(new Time(0, 0, 1)));
        assertEquals(false, (new Time(24, 59, 58)).laterThan(new Time(24, 59, 59)));
        assertEquals(false, (new Time(4, 59, 59)).laterThan(new Time(5, 0, 0)));
        assertEquals(false, (new Time(23, 29, 0)).laterThan(new Time(23, 30, 0)));
        assertEquals(false, (new Time(24, 59, 59)).laterThan(new Time(24, 59, 59)));
        assertEquals(false, (new Time(0, 0, 0)).laterThan(new Time(0, 0, 0)));

        /*--- Anticipated Successes ---*/
        assertEquals(true, (new Time(24, 59, 59)).laterThan(new Time(0, 0, 0)));
        assertEquals(true, (new Time(0, 0, 1)).laterThan(new Time(0, 0, 0)));
        assertEquals(true, (new Time(0, 1, 0)).laterThan(new Time(0, 0, 0)));
        assertEquals(true, (new Time(1, 0, 0)).laterThan(new Time(0, 0, 0)));
        assertEquals(true, (new Time(17, 0, 0)).laterThan(new Time(15, 30, 0)));
        assertEquals(true, (new Time(5, 0, 0)).laterThan(new Time(4, 59, 59)));

    }

    @Test
    public void earlierThanShouldDetermineWhetherPassedTimeLater()
    {
        /*--- Anticipated Failures ---*/
        assertEquals(false, (new Time(24, 59, 59)).earlierThan(new Time(0, 0, 0)));
        assertEquals(false, (new Time(24, 59, 59)).earlierThan(new Time(24, 59, 58)));
        assertEquals(false, (new Time(0, 0, 1)).earlierThan(new Time(0, 0, 0)));
        assertEquals(false, (new Time(0, 1, 0)).earlierThan(new Time(0, 0, 0)));
        assertEquals(false, (new Time(1, 0, 0)).earlierThan(new Time(0, 0, 0)));
        assertEquals(false, (new Time(17, 0, 0)).earlierThan(new Time(15, 30, 0)));
        assertEquals(false, (new Time(5, 0, 0)).earlierThan(new Time(4, 59, 59)));
        assertEquals(false, (new Time(24, 59, 59)).earlierThan(new Time(24, 59, 59)));
        assertEquals(false, (new Time(0, 0, 0)).earlierThan(new Time(0, 0, 0)));

        /*--- Anticipated Successes ---*/
        assertEquals(true, (new Time(0, 0, 0)).earlierThan(new Time(24, 59, 59)));
        assertEquals(true, (new Time(0, 0, 0)).earlierThan(new Time(0, 0, 1)));
        assertEquals(true, (new Time(24, 59, 58)).earlierThan(new Time(24, 59, 59)));
        assertEquals(true, (new Time(4, 59, 59)).earlierThan(new Time(5, 0, 0)));
        assertEquals(true, (new Time(23, 29, 0)).earlierThan(new Time(23, 30, 0)));

    }

    @Test
    public void validShouldDetermineInputTimeValidity()
    {

        /*--- Anticipated Failures ---*/
        assertFalse(Time.valid(-1, 0, 0));
        assertFalse(Time.valid(0, -1, 0));
        assertFalse(Time.valid(0, 0, -1));
        assertFalse(Time.valid(48, 0, 0));
        assertFalse(Time.valid(0, 60, 0));
        assertFalse(Time.valid(0, 0, 60));
        assertFalse(Time.valid(98, 117, 264));

        /*--- Anticipated Successes ---*/
        assertTrue(Time.valid(0, 0, 0));
        assertTrue(Time.valid(47, 59, 59));
        assertTrue(Time.valid(24, 0, 0));
        assertTrue(Time.valid(18, 30, 0));
        assertTrue(Time.valid(8, 45, 0));

    }

    @Test
    public void convertStringShouldProperlyConvertString()
    {

        /*--- Anticipated Failures ---*/
        assertFalse((new Time(0, 0, 0).equals(Time.convertString("00:00:01"))));
        assertFalse((new Time(0, 0, 1).equals(Time.convertString("00:00:00"))));
        assertFalse((new Time(24, 0, 0).equals(Time.convertString("23:00:00"))));

        /*--- Anticipated Successes ---*/
        assertTrue((new Time(0, 0, 0).equals(Time.convertString("00:00:00"))));
        assertTrue((new Time(24, 30, 15).equals(Time.convertString("24:30:15"))));
        assertTrue((new Time(47, 59, 59).equals(Time.convertString("47:59:59"))));

    }

    @Test
    public void reduceHourShouldReduceHourInTwentyFourHourSystem()
    {
        assertEquals(0, Time.reduceHour(24));
        assertEquals(1, Time.reduceHour(25));
        assertEquals(2, Time.reduceHour(26));
        assertEquals(23, Time.reduceHour(47));
        assertEquals(18, Time.reduceHour(42));
    }


    ////////////////////
    // Helper Methods //
    ////////////////////

    private boolean constructorGeneratesException(int h, int m, int s)
    {
        boolean shouldBeTrue = false;
        try
        {
            Time time = new Time(h, m, s);
        }
        catch(RuntimeException e)
        {
            shouldBeTrue = true;
        }
        return shouldBeTrue;
    }

    private boolean constructorGeneratesNoException(int h, int m, int s)
    {
        boolean shouldBeTrue = true;
        try
        {
            Time time = new Time(h, m, s);
        }
        catch(RuntimeException e)
        {
            shouldBeTrue = false;
        }
        return shouldBeTrue;
    }

}
