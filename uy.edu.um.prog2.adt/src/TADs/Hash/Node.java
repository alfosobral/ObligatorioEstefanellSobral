package TADs.Hash;

public class Node<K, T> {
    private T value;
    private K key;

    public Node(K key, T value) {
        this.value = value;
        this.key = key;
    }
    public T getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }




}

