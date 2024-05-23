package TADs.Hash;

import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.Stack.Exceptions.EmptyStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {

    Hash<String, String> h = new Hash<>(5);

    @Test
    public void testHashFunction() {
        int index = h.hashFunction("A");
        assertEquals(0, index);
    }

    @Test
    public void testAdd() {
        h.add("A", "Prueba1");
        h.add("B", "Prueba2");
        h.add("C", "Prueba3");
        h.add("D", "Prueba4");
        assertEquals(4, h.getCounter());
        assertEquals(11, h.getSize());
        h.add("E", "Prueba5");
        h.add("F", "Prueba6");
        h.add("G", "Prueba7");
        h.add("H", "Prueba8");
        h.add("Z", "Prueba9");
        assertEquals(9, h.getCounter());
        assertEquals(23, h.getSize());
    }

    @Test
    public void testSerch() throws InvalidHashKey {
        h.add("A", "Prueba1");
        h.add("B", "Prueba2");
        h.add("C", "Prueba3");
        h.add("D", "Prueba4");
        h.add("E", "Prueba5");
        h.add("F", "Prueba6");
        h.add("G", "Prueba7");
        h.add("H", "Prueba8");
        h.add("X", "Prueba9");
        assertEquals("Prueba3", h.serch("C"));
        assertEquals("Prueba9", h.serch("X"));
        assertThrows(InvalidHashKey.class, ()->{h.remove("J");});
    }

}