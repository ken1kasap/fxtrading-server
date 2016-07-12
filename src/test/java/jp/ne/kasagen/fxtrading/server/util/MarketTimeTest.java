package jp.ne.kasagen.fxtrading.server.util;

import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kasagen
 */
public class MarketTimeTest {
    
    /**
     * Test of isOpened method, of class MarketTime.
     */
    @Test
    public void testIsOpened() {
        System.out.println("isOpened");
        LocalDateTime time = LocalDateTime.of(2016, Month.JULY, 7, 10, 0);
        MarketTime instance = new MarketTime();
        boolean expResult = true;
        boolean result = instance.isOpened(time);
        assertEquals(expResult, result);
    }

     /**
     * Test of isOpened method, of class MarketTime.
     */
    @Test
    public void testIsMondayOpened() {
        System.out.println("isOpened");
        LocalDateTime time = LocalDateTime.of(2016, Month.JULY, 11, 9, 0);
        MarketTime instance = new MarketTime();
        boolean expResult = true;
        boolean result = instance.isOpened(time);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOpened method, of class MarketTime.
     */
    @Test
    public void testIsMondayClosed() {
        System.out.println("isOpened");
        LocalDateTime time = LocalDateTime.of(2016, Month.JULY, 11, 6, 59);
        MarketTime instance = new MarketTime();
        boolean expResult = false;
        boolean result = instance.isOpened(time);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOpened method, of class MarketTime.
     */
    @Test
    public void testIsSaturdayOpened() {
        System.out.println("isOpened");
        LocalDateTime time = LocalDateTime.of(2016, Month.JULY, 16, 6, 54);
        MarketTime instance = new MarketTime();
        boolean expResult = true;
        boolean result = instance.isOpened(time);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOpened method, of class MarketTime.
     */
    @Test
    public void testIsSatudayClosed() {
        System.out.println("isOpened");
        LocalDateTime time = LocalDateTime.of(2016, Month.JULY, 16, 6, 55);
        MarketTime instance = new MarketTime();
        boolean expResult = false;
        boolean result = instance.isOpened(time);
        assertEquals(expResult, result);
    }
    
}
