package LinkedList;


import LinkedList.Exceptions.EmptyList;
import LinkedList.Exceptions.InvalidIndex;

public interface MyList<T> {
    void addFirst(T value);
    T get(int position) throws InvalidIndex;
    boolean contains(T value);
    void removeValue(T value) throws EmptyList;
    void removePosition(int pos) throws InvalidIndex, EmptyList;
    int size() throws EmptyList;
    void addLast(T value);
    void printList();

}
