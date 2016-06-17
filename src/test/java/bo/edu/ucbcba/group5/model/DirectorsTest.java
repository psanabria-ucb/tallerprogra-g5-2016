package bo.edu.ucbcba.group5.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by INTEL on 17/06/2016.
 */
public class DirectorsTest {
    private Directors director;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        director = new Directors();
    }

    @Test
    public void testGetAnio() throws Exception {
        assertEquals(0,director.getAnio());
    }

    @Test
    public void testSetAnio() throws Exception {
        director.setAnio(2003);
        assertEquals(2003,director.getAnio());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(0,director.getId());
    }

    @Test
    public void testSetId() throws Exception {
        director.setId(5);
        assertEquals(5,director.getId());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("",director.getName());
    }

    @Test
    public void testSetName() throws Exception {
        director.setName("pedro");
        assertEquals("pedro",director.getName());
    }

    @Test
    public void testGetAwards() throws Exception {
        assertEquals(0,director.getAwards());
    }

    @Test
    public void testSetAwards() throws Exception {
        director.setAwards(3);
        assertEquals(3,director.getAwards());
    }

    @Test
    public void testGetPeliculas() throws Exception {

    }

    @Test
    public void testSetPeliculas() throws Exception {

    }

}