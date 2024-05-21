package TADs.LinkedList;

import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularListTest {
    LinkedList linkedList = new LinkedList();
    Node test1 = new Node("Prueba");
    Node test2 = new Node("Prueba2");
    Node test3 = new Node("Prueba3");
    @Test
    public void testAdd() throws EmptyList, InvalidIndex {
        linkedList.addFirst(test1);
        linkedList.addLast(test2);
        linkedList.addFirst(test3);
        int tamano = linkedList.size();       //Es por la excepción pero no la tiene realmente, hay que esperar a commitear
        Object value1 = linkedList.get(0);
        assertEquals(2, tamano);
        assertTrue(value1 == test3.getValue());
    }
    @Test
    public void testRemoveValue() throws EmptyList {
        int tamano = linkedList.size();
        linkedList.removeValue(test2);
        int tamano_final = linkedList.size();
        assertNotEquals(tamano, tamano_final);
    }
    @Test
    public void testRemovePosition() throws EmptyList, InvalidIndex {
        int tamano = linkedList.size();
        linkedList.removePosition(0);
        int tamano_final = linkedList.size();
        assertNotEquals(tamano, tamano_final);
    }

}