package TADs.Tree;

import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.Tree.Exceptions.EmptyTree;
import TADs.Tree.Exceptions.InvalidKey;
public interface MyTree<K, T> {
    public T serch(K key) throws EmptyTree, InvalidKey;

    Node<K, T> serchNode(K key) throws EmptyTree, InvalidKey;

    public void add(K key, T data) throws InvalidKey;

    void addNode(Node<K, T> add) throws InvalidKey;

    public void delete(K key) throws InvalidKey, EmptyTree, EmptyList, InvalidIndex;
}
