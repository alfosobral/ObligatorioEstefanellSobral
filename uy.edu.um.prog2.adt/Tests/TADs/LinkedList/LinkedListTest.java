package TADs.LinkedList;

import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class LinkedListTest {
    LinkedList<Object> linkedList = new LinkedList<>();
    Node<Object> test1 = new Node<>("Prueba");
    Node<Object> test2 = new Node<>("Prueba2");
    Node<Object> test3 = new Node<>("Prueba3");
    Node<Object> test4 = new Node<>(2);
    @Test
    public void testAdd() throws EmptyList, InvalidIndex {
        linkedList.addFirst(test1.getValue());
        linkedList.addLast(test2.getValue());
        linkedList.addFirst(test3.getValue());
        linkedList.addLast(test4.getValue());
        int tamano = linkedList.size();
        Object value1 = linkedList.get(0);
        assertEquals(4, tamano);
        assertEquals(value1, test3.getValue());

    }
    @Test
    public void testRemoveValue() throws EmptyList {
        linkedList.addFirst(test1.getValue());
        linkedList.addLast(test2.getValue());
        linkedList.addFirst(test3.getValue());
        linkedList.addLast(test4.getValue());
        int tamano = linkedList.size();
        linkedList.removeValue(test2.getValue());
        int tamano_final = linkedList.size();
        assertNotEquals(tamano, tamano_final);
    }

    @Test
    public void testRemovePosition() throws EmptyList, InvalidIndex {
        linkedList.addFirst(test1.getValue());
        linkedList.addLast(test2.getValue());
        linkedList.addFirst(test3.getValue());
        linkedList.addLast(test4.getValue());
        int tamano = linkedList.size();
        linkedList.removePosition(0);
        int tamano_final = linkedList.size();
        assertNotEquals(tamano, tamano_final);
    }

    @Test
    public void testExceptions() throws EmptyList, InvalidIndex {
        assertThrows(EmptyList.class, ()->{linkedList.get(4);});
        linkedList.addFirst("Hola");
        assertThrows(InvalidIndex.class, ()->{linkedList.get(4);});
        linkedList.removeValue("Hola");
        assertThrows(EmptyList.class, ()->{linkedList.removeValue(1);});
    }
}

