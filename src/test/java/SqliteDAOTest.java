/*
  Christopher Cruzen                         LaunchCode CS50
  "SqliteDAOTest.java"                            05.16.2015

  Problem Set #6

*/
import com.xephorium.metrolink.database.SqliteDAO;
import org.junit.Test;
import java.time.LocalTime;
import static org.junit.Assert.*;

public class SqliteDAOTest
{
    @Test
    public void convertDatabaseTimeShouldConvertStringTimes()
    {
        SqliteDAO accessObject = new SqliteDAO();

        LocalTime time = LocalTime.of(00,00,00);
        assertEquals(time, accessObject.convertDatabaseTime("00:00:00"));

        time = LocalTime.of(23,59,59);
        assertEquals(time, accessObject.convertDatabaseTime("23:59:59"));

        time = LocalTime.of(14,30,00);
        assertEquals(time, accessObject.convertDatabaseTime("14:30:00"));
    }
}
