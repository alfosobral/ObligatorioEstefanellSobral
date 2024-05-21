package TADs.Queue;

import TADs.Queue.Exceptions.EmptyQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    Queue<Object> queue = new Queue<>();
    Node<Object> test1 = new Node<>("Prueba");
    Node<Object> test2 = new Node<>("Prueba2");
    Node<Object> test3 = new Node<>("Prueba3");
    Node<Object> test4 = new Node<>(2);

    @Test
    public void testEnqueue() throws EmptyQueue {
        queue.enqueue(test1.getValue());
        queue.enqueue(test2.getValue());
        queue.enqueue(test3.getValue());
        queue.enqueue(test4.getValue());
        assertTrue(queue.contains(test2.getValue()));
        int size = queue.getSize();
        Object value1 = queue.dequeue().getValue();
        queue.enqueue(value1);
        assertEquals(4,size);
        assertEquals(value1, test4.getValue());
    }

    @Test
    public void testDequeue() throws EmptyQueue {
        queue.enqueue(test1.getValue());
        queue.enqueue(test2.getValue());
        queue.enqueue(test3.getValue());
        queue.enqueue(test4.getValue());
        int size = queue.getSize();
        Object value1 = queue.dequeue().getValue();
        assertEquals(4,size);
        assertFalse(queue.contains(test1.getValue()));
    }
}