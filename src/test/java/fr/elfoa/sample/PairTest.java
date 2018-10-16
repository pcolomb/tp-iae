package fr.elfoa.sample;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Pierre Colomb
 */
public class PointTest {


    @Test
    public void testConstruction(){

        Point point = new Point(1,2);

        assertEquals(1,point.getX());
        assertEquals(2,point.getY());

        Point other = new Point(1,2);

        assertTrue(other.equals(point));
    }
}