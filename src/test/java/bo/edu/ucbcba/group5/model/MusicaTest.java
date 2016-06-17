package bo.edu.ucbcba.group5.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Christian on 16/06/2016.
 */
public class MusicaTest {

    private Musica musica;
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Before
    public void setUp(){ musica = new Musica();}
    @Test
    public void testGetSong2() throws Exception {
        assertEquals("", musica.getSong2());

    }

    @Test
    public void testSetSong2() throws Exception {
        musica.setSong2("nuevo");
        assertEquals("nuevo", musica.getSong2());

    }

    @Test
    public void testGetSong3() throws Exception {
        assertEquals("", musica.getSong3());
    }

    @Test
    public void testSetSong3() throws Exception {
        musica.setSong3("nuevo");
        assertEquals("nuevo", musica.getSong3());

    }

    @Test
    public void testGetSong4() throws Exception {
        assertEquals("", musica.getSong4());
    }

    @Test
    public void testSetSong4() throws Exception {
        musica.setSong4("nuevo");
        assertEquals("nuevo", musica.getSong4());

    }

    @Test
    public void testGetSong5() throws Exception {
        assertEquals("", musica.getSong5());
    }

    @Test
    public void testSetSong5() throws Exception {
        musica.setSong5("nuevo");
        assertEquals("nuevo", musica.getSong5());

    }

    @Test
    public void testGetSong6() throws Exception {
        assertEquals("", musica.getSong6());
    }

    @Test
    public void testSetSong6() throws Exception {
        musica.setSong6("nuevo");
        assertEquals("nuevo", musica.getSong6());

    }

    @Test
    public void testGetSong7() throws Exception {
        assertEquals("", musica.getSong7());
    }

    @Test
    public void testSetSong7() throws Exception {
        musica.setSong7("nuevo");
        assertEquals("nuevo", musica.getSong7());
    }

    @Test
    public void testGetSong8() throws Exception {
        assertEquals("", musica.getSong8());
    }

    @Test
    public void testSetSong8() throws Exception {
        musica.setSong8("nuevo");
        assertEquals("nuevo", musica.getSong8());

    }

    @Test
    public void testGetSong9() throws Exception {
        assertEquals("", musica.getSong9());
    }

    @Test
    public void testSetSong9() throws Exception {
        musica.setSong9("nuevo");
        assertEquals("nuevo", musica.getSong9());

    }

    @Test
    public void testGetSong10() throws Exception {
        assertEquals("", musica.getSong10());
    }

    @Test
    public void testSetSong10() throws Exception {
        musica.setSong10("nuevo");
        assertEquals("nuevo", musica.getSong10());

    }

    @Test
    public void testGetSong11() throws Exception {
        assertEquals("", musica.getSong11());
    }

    @Test
    public void testSetSong11() throws Exception {
        musica.setSong11("nuevo");
        assertEquals("nuevo", musica.getSong11());
    }

    @Test
    public void testGetSong12() throws Exception {
        assertEquals("", musica.getSong12());
    }

    @Test
    public void testSetSong12() throws Exception {
        musica.setSong12("nuevo");
        assertEquals("nuevo", musica.getSong12());
    }

    @Test
    public void testGetSong1() throws Exception {
        assertEquals("", musica.getSong1());
    }

    @Test
    public void testSetSong1() throws Exception {
        musica.setSong1("nuevo");
        assertEquals("nuevo", musica.getSong1());

    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("", musica.getDescription());
    }

    @Test
    public void testSetDescription() throws Exception {
        musica.setDescription("nuevo");
        assertEquals("nuevo", musica.getDescription());

    }

    @Test
    public void testGetNombre() throws Exception {
        assertEquals("", musica.getNombre());
    }

    @Test
    public void testSetNombre() throws Exception {
        musica.setNombre("nuevo");
        assertEquals("nuevo", musica.getNombre());
    }

    @Test
    public void testGetGenero() throws Exception {
        assertEquals("", musica.getGenero());
    }

    @Test
    public void testSetGenero() throws Exception {
        musica.setGenero("nuevo");
        assertEquals("nuevo", musica.getGenero());
    }

    @Test
    public void testGetLanzamiento() throws Exception {
        assertEquals(0,musica.getLanzamiento());
    }

    @Test
    public void testSetLanzamiento() throws Exception {
        musica.setLanzamiento(2002);
        assertEquals(2002, musica.getLanzamiento());

    }

    @Test
    public void testGetDuracMinutos() throws Exception {
        assertEquals(0,musica.getDuracMinutos());
    }

    @Test
    public void testSetDuracMinutos() throws Exception {
        musica.setDuracMinutos(10);
        assertEquals(10, musica.getDuracMinutos());
    }
    @Test
    public void testGetPeso() throws Exception {

    }

    @Test
    public void testSetPeso() throws Exception {
        musica.setPeso(70.5);
        if(musica.getPeso()==70.5){

        }
    }
}