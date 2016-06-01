package bo.edu.ucbcba.group5.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Abel on 5/31/2016.
 */
public class CompanyTest {
    private Company company;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        company = new Company();
    }

    @Test
    public void testGetAnio() throws Exception {
        assertEquals(0,company.getAnio());

    }

    @Test
    public void testSetAnio() throws Exception {
        company.setAnio(2003);
        assertEquals(2003,company.getAnio());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(0,company.getId());
    }

    @Test
    public void testSetId() throws Exception {
        company.setId(5);
        assertEquals(5,company.getId());

    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("",company.getName());
    }

    @Test
    public void testSetName() throws Exception {
        company.setName("pablo");
        assertEquals("pablo",company.getName());

    }

    @Test
    public void testGetAwards() throws Exception {
        assertEquals(0,company.getAwards());

    }

    @Test
    public void testSetAwards() throws Exception {
        company.setAwards(3);
        assertEquals(3,company.getAwards());

    }

    @Test
    public void testGetJuegos() throws Exception {


    }

    @Test
    public void testSetJuegos() throws Exception {

    }
}