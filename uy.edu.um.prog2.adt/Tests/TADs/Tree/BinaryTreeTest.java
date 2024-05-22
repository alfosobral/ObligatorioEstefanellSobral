package TADs.Tree;

import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.Tree.Exceptions.EmptyTree;
import TADs.Tree.Exceptions.InvalidKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    BinaryTree<Integer, Object> tree = new BinaryTree<>();

    @Test
    public void testAdd() throws InvalidKey, EmptyTree {
        tree.add(50,"Prueba");
        tree.add(25, "Prueba2");
        tree.add(75, "Prueba3");
        assertEquals("Prueba", tree.serch(50));
        assertEquals("Prueba2", tree.serch(25));
        assertEquals("Prueba3",tree.serch(75));
    }
    //searchNode funciona igual que el search

    @Test
    public void testDelete() throws InvalidKey, EmptyList, EmptyTree, InvalidIndex {
        tree.add(50,"Prueba");
        tree.add(25, "Prueba2");
        tree.add(75, "Prueba3");
        tree.delete(25);
        assertNotEquals("Prueba2", tree.getMin());
    }

}