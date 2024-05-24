package TADs.LinkedList;

import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularListTest {
    CircularList<Object> circularList = new CircularList<>();

    @Test
    public void testAddFirst() throws InvalidIndex, EmptyList {
        assertThrows(EmptyList.class, ()->{circularList.get(3);});
        circularList.addFirst("Nodo1");
        circularList.addFirst("Nodo2");
        assertEquals("Nodo2", circularList.get(1));
        assertEquals(2, circularList.size());
        assertThrows(InvalidIndex.class, ()->{circularList.get(3);});
    }

    @Test
    public void testAddLasrt() throws InvalidIndex, EmptyList {
        assertThrows(EmptyList.class, ()->{circularList.get(3);});
        circularList.addLast("Nodo1");
        circularList.addLast("Nodo2");
        assertEquals("Nodo1", circularList.get(1));
        assertEquals(2, circularList.size());
        assertThrows(InvalidIndex.class, ()->{circularList.get(3);});
    }

    @Test
    public void testContains() {
        circularList.addFirst("Nodo1");
        circularList.addFirst("Nodo2");
        circularList.addLast("Nodo3");
        circularList.addLast("Nodo4");
        assertTrue(circularList.contains("Nodo3"));
        assertFalse(circularList.contains("X"));
    }

    @Test
    public void testRemovePos() throws EmptyList, InvalidIndex {
        assertThrows(EmptyList.class, ()->{circularList.removePosition(1);});
        circularList.addFirst("Nodo1");
        circularList.addFirst("Nodo2");
        circularList.addFirst("Nodo3");
        circularList.addFirst("Nodo4");
        circularList.removePosition(2);
        assertFalse(circularList.contains("Nodo3"));
        assertThrows(InvalidIndex.class, ()->{circularList.removePosition(777);});
    }

    @Test
    public void testRemoveValue() throws EmptyList{
        assertThrows(EmptyList.class, ()->{circularList.removeValue("X");});
        circularList.addFirst("Nodo1");
        circularList.addFirst("Nodo2");
        circularList.addFirst("Nodo3");
        circularList.addFirst("Nodo4");
        circularList.removeValue("Nodo2");
        assertFalse(circularList.contains("Nodo2"));
    }

}