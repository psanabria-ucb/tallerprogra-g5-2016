package bo.edu.ucbcba.group5.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by INTEL on 16/06/2016.
 */
public class PeliculaTest {

    private Pelicula pelicula;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        pelicula = new Pelicula();
    }

    @Test
    public void testGetNomCover() throws Exception {
        assertEquals("", pelicula.getNombre());
    }

    @Test
    public void testSetNomCover() throws Exception {
        pelicula.setNomCover("nuevo");
        assertEquals("nuevo", pelicula.getNomCover());
    }

    @Test
    public void testGetDirname() throws Exception {
        assertEquals("", pelicula.getDirname());
    }

    @Test
    public void testSetDirname() throws Exception {
        pelicula.setDirname("nuevo");
        assertEquals("nuevo", pelicula.getDirname());
    }

    @Test
    public void testGetDirector() throws Exception {
        assertEquals(null, pelicula.getDirector());
    }

    @Test
    public void testGetDirectorName() throws Exception {
        assertEquals("", pelicula.getDirectorName());
    }

    @Test
    public void testSetDirector() throws Exception {

    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("",pelicula.getDescription());
    }

    @Test
    public void testSetDescription() throws Exception {
        pelicula.setDescription("nuevo");
        assertEquals("nuevo", pelicula.getDescription());
    }

    @Test
    public void testGetNombre() throws Exception {
        assertEquals("",pelicula.getNombre());
    }

    @Test
    public void testSetNombre() throws Exception {
        pelicula.setNombre("nuevo");
        assertEquals("nuevo", pelicula.getNombre());
    }

    @Test
    public void testGetGenero() throws Exception {
        assertEquals("",pelicula.getGenero());
    }

    @Test
    public void testSetGenero() throws Exception {
        pelicula.setGenero("nuevo");
        assertEquals("nuevo", pelicula.getGenero());
    }

    @Test
    public void testGetPeso() throws Exception {

    }

    @Test
    public void testSetPeso() throws Exception {
        pelicula.setPeso(4.5);
        if(pelicula.getPeso()==4.5){

        }
    }

    @Test
    public void testGetLanzamiento() throws Exception {
        assertEquals(0,pelicula.getLanzamiento());
    }

    @Test
    public void testSetLanzamiento() throws Exception {
        pelicula.setLanzamiento(2002);
        assertEquals(2002, pelicula.getLanzamiento());
    }

    @Test
    public void testGetDuracMinutos() throws Exception {
        assertEquals(0,pelicula.getDuracMinutos());
    }

    @Test
    public void testSetDuracMinutos() throws Exception {
        pelicula.setDuracMinutos(200);
        assertEquals(200, pelicula.getDuracMinutos());
    }
}