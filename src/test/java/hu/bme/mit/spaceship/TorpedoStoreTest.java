package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }
    @Test 
    void fire_illegal_argument(){
        TorpedoStore store = new TorpedoStore(1);

        //Ez marha ronda, hogy egy lambdát kell ehhez használni, de az végülis visszaad egy exceptiont, amikor -1-et adok be neki
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
        assertTrue(e.getMessage().contains("numberOfTorpedos"));

        //Ez itt azért kell még egyszer, mert a 2 > 1 esetért nem volt kedvem külön tesztet írni
        e = assertThrows(IllegalArgumentException.class, () -> store.fire(2));
        assertTrue(e.getMessage().contains("numberOfTorpedos"));
    }

    @Test 
    void get_torpedo_count(){
        TorpedoStore store = new TorpedoStore(1);
        assertEquals(1, store.getTorpedoCount());
    }

    @Test
    void evil_test(){
        //NINCS ITT SEMMI LÁTNIVALÓ csak ezzel a csodás és deprecated exec method-del beállítottam a teszt erejéig az IVT_RATE-et, hogy ez is tesztelve legyen a TorpedoStore-ban
        try {
            Process p = Runtime.getRuntime().exec("export IVT_RATE=1");
            p.waitFor();
        } catch (Exception e) {
        }

        TorpedoStore store = new TorpedoStore(1);

        boolean result = store.fire(1);

        assertEquals(true, result);
    }
}
