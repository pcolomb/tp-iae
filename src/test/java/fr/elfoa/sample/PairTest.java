package fr.elfoa.sample;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Pierre Colomb
 */
public class PairTest {


    @Test
    public void testConstruction(){

        Pair point = new Pair(1, 2);

        assertEquals(1,point.getX());
        assertEquals(2,point.getY());

        Pair other = new Pair(1, 2);

        assertTrue(other.equals(point));
    }
}