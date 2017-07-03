package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.Map;
import model.Room;

public class GameTest {
	
	@Test
	public void checkBloodLocations() {
		
	}
	
	@Test
	public void testToString() {
		Map map = new Map ();
		map.toString();
	}

    @Test
    public void testMoveFourDirections() {
    assertTrue(3 == 4);
    }

}