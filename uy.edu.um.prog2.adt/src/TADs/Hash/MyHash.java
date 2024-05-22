package TADs.Hash;

import TADs.Hash.Exceptions.InvalidHashKey;

public interface MyHash<K,T> {
    int hashFunction(K key);

    void add(K key, T value);
    void remove(K key) throws InvalidHashKey;

    T serch(K key) throws InvalidHashKey;
}
