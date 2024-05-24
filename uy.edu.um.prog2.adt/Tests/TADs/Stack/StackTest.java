package TADs.Stack;
import TADs.Stack.Exceptions.EmptyStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    STACK<Object> stack = new STACK<>();
    Node<Object> test1 = new Node<>("Prueba");
    Node<Object> test2 = new Node<>("Prueba2");
    Node<Object> test3 = new Node<>("Prueba3");
    Node<Object> test4 = new Node<>(2);

    @Test
    public void testPush() {
        stack.push(test1.getValue());
        stack.push(test2.getValue());
        stack.push(test3.getValue());
        stack.push(test4.getValue());
        int size = stack.size();
        assertEquals(4, size);
    }

    @Test
    public void testPop() throws EmptyStack {
        stack.push(test1.getValue());
        stack.push(test2.getValue());
        stack.push(test3.getValue());
        stack.push(test4.getValue());
        int size = stack.size();
        assertEquals(4, size);
        stack.pop();
        size = stack.size();
        assertEquals(3, size);
        stack.makeEmpty();
        assertThrows(EmptyStack.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void testPeek() throws EmptyStack {
        stack.push(test1.getValue());
        stack.push(test2.getValue());
        stack.push(test3.getValue());
        stack.push(test4.getValue());
        int size = stack.size();
        assertEquals(4, size);
        stack.pop();
        Object peek = stack.peek().getValue();
        assertEquals(peek, test3.getValue());
        stack.makeEmpty();
        assertThrows(EmptyStack.class, () -> {
            stack.peek();
        });
    }

    @Test
    public void testMakeEmpty() throws EmptyStack {
        stack.push(test1.getValue());
        stack.push(test2.getValue());
        stack.push(test3.getValue());
        stack.push(test4.getValue());
        int size = stack.size();
        assertEquals(4, size);
        stack.makeEmpty();
        size = stack.size();
        assertEquals(0, size);
    }

    @Test
    public void testContains() throws EmptyStack {
        stack.push(test1.getValue());
        stack.push(test2.getValue());
        stack.push(test3.getValue());
        stack.push(test4.getValue());
        assertTrue(stack.contains(test3.getValue()));
    }

    @Test
    public void testExceptions() throws EmptyStack {
        stack.makeEmpty();
        assertThrows(EmptyStack.class, ()->{stack.pop();});
        assertThrows(EmptyStack.class, ()->{stack.peek();});
    }
}