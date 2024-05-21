package TADs.LinkedList;

import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class LinkedListTest {
    LinkedList<String> linkedList = new LinkedList<>();
    Node<String> test1 = new Node<>("Prueba");
    Node<String> test2 = new Node<>("Prueba2");
    Node<String> test3 = new Node<>("Prueba3");
    @Test
    public void testAdd() throws EmptyList, InvalidIndex {
        linkedList.addFirst("Prueba");
        linkedList.addLast("Prueba2");
        linkedList.addFirst("Prueba3");
        int tamano = linkedList.size();
        String value1 = linkedList.get(0);
        assertEquals(3, tamano);
        assertTrue(value1.equals(test3.getValue()));
    }
//    @Test
//    public void testRemoveValue() throws EmptyList {
//        linkedList.addFirst(test1);
//        linkedList.addLast(test2);
//        linkedList.addFirst(test3);
//        int tamano = linkedList.size();
//        linkedList.removeValue(test2);
//        int tamano_final = linkedList.size();
//        assertNotEquals(tamano, tamano_final);
//    }
//
//    @Test
//    public void testRemovePosition() throws EmptyList, InvalidIndex {
//        linkedList.addFirst(test1);
//        linkedList.addLast(test2);
//        linkedList.addFirst(test3);
//        int tamano = linkedList.size();
//        linkedList.removePosition(0);
//        int tamano_final = linkedList.size();
//        assertNotEquals(tamano, tamano_final);
//    }

}

