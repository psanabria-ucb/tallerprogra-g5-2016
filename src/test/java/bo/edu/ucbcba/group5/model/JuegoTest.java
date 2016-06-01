package bo.edu.ucbcba.group5.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Abel on 5/31/2016.
 */
public class JuegoTest {

    private Juego juego;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        juego = new Juego();
    }


    @Test
    public void testGetNomCover() throws Exception {
        assertEquals("", juego.getNombre());

    }

    @Test
    public void testSetNomCover() throws Exception {
        juego.setNomCover("nuevo");
        assertEquals("nuevo", juego.getNomCover());
    }

    @Test
    public void testGetCompName() throws Exception {
        assertEquals("", juego.getCompName());
    }

    @Test
    public void testSetCompName() throws Exception {
        juego.setCompName("nuevo");
        assertEquals("nuevo", juego.getCompName());
    }

    @Test
    public void testGetCompany() throws Exception {

        assertEquals(null, juego.getCompany());
    }

    @Test
    public void testSetCompany() throws Exception {

    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("",juego.getDescription());

    }

    @Test
    public void testSetDescription() throws Exception {
        juego.setDescription("nuevo");
        assertEquals("nuevo", juego.getDescription());
    }

    @Test
    public void testGetNombre() throws Exception {
        assertEquals("",juego.getNombre());
    }

    @Test
    public void testSetNombre() throws Exception {
        juego.setNombre("nuevo");
        assertEquals("nuevo", juego.getNombre());
    }

    @Test
    public void testGetGenero() throws Exception {
        assertEquals("",juego.getGenero());
    }

    @Test
    public void testSetGenero() throws Exception {
        juego.setGenero("nuevo");
        assertEquals("nuevo", juego.getGenero());

    }

    @Test
    public void testGetPeso() throws Exception {

    }

    @Test
    public void testSetPeso() throws Exception {
        juego.setPeso(4.5);
        if(juego.getPeso()==4.5){

        }

    }

    @Test
    public void testGetLanzamiento() throws Exception {
            assertEquals(0,juego.getLanzamiento());
    }

    @Test
    public void testSetLanzamiento() throws Exception {
        juego.setLanzamiento(2002);
        assertEquals(2002, juego.getLanzamiento());

    }
}