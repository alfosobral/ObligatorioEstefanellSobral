package LinkedList;

public interface MyList<T> {
    void addFirst(T value);
    T get(int position);
    boolean contains(T value);
    void remove(T value);
    int size();
    void addLast(T value);
    void printList();

}
