package utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class RandomUtilsTests {
    @Before
    public void setUp() {
        // Make the tests deterministic
        RandomUtils.setRandom(new Random(1));
    }

    private void testRandomDateBetween(int startYear, int endYear) {
        Date date = RandomUtils.randomDate(startYear, endYear);
        assertTrue(date.after(new GregorianCalendar(startYear, 1, 1).getTime()));
        assertTrue(date.before(new GregorianCalendar(endYear, 1, 1).getTime()));
    }

    @Test
    public void testRandomDateLittleDifference() {
        testRandomDateBetween(2000, 2001);
    }

    @Test
    public void testRandomDateBigNumbers() {
        testRandomDateBetween(400000, 500000);
    }

    @Test
    public void testRandomDateBeforeEpoch() {
        testRandomDateBetween(1300, 1400);
    }

    @Test
    public void testRandomDateMultiple() {
        for (int i = 0; i < 10; i++) {
            testRandomDateBetween(1000 + i * 100, 1200 + i * 100);
        }
    }
}
