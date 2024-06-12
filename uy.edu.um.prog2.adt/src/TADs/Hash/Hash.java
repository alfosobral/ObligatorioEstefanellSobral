package TADs.Hash;

import TADs.Hash.Exceptions.InvalidHashKey;

import java.util.Arrays;

public class Hash<K, T> implements MyHash<K, T>{
    private Node<K, T>[] array;
    private Integer size, counter;

    public Hash(Integer size){
        this.size = size;
        this.counter = 0;
        array = new Node[size];
        Arrays.fill(array,null);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getCounter() {
        return counter;
    }

    public void setArray(Node<K,T>[] array) {
        this.array = array;
    }

    @Override
    public int hashFunction(K key) {
        String keyToStr = key.toString();
        int hash = 5381;
        for (int i = 0; i < keyToStr.length(); i++) {
            hash = (hash << 5) + hash + keyToStr.charAt(i);
        }
        return Math.abs(hash % size);
    }

    @Override
    public void add(K key, T value) {
        Node<K, T> add = new Node<>(key, value);
        if (this.checkCapacity()) {
            this.reSize();
//            this.reHash();
        }
        key = (K) key.toString().trim();
        int index = this.hashFunction(key);
        while (this.array[index] != null) {
            index = (index+1) % size;
        }
        this.array[index] = new Node<>(key, value);
        this.counter++;

    }

    @Override
    public void remove(K key) throws InvalidHashKey {
        int index = this.hashFunction(key);
        int startIndex = index;
        while (this.array[index] != null && !this.array[index].getKey().equals(key)) {
            index = (index + 1) % size;
            if (index == startIndex) {
                throw new InvalidHashKey();
            }
        }
        if (this.array[index] != null) {
            this.array[index] = null;
            this.counter--;
            this.reOrganize(this.size);
        } else {
            throw new InvalidHashKey();
        }
    }

    @Override
    public T search(K key) throws InvalidHashKey {
        int index = hashFunction(key);
        int startIndex = index;
        while (this.array[index] != null) {
            if (this.array[index].getKey().equals(key)) {
                return this.array[index].getValue();
            }
            index = (index + 1) % size;
            if (index == startIndex) {
                break;
            }
        }
        throw new InvalidHashKey();
    }

    @Override
    public boolean checkCapacity() {
        return counter >= (int) (size * 0.60);
    }

    @Override
    public void reSize(){
        int n = size*2;
        int p = 0;
        while (p == 0) {
            n++;
            int div = 0;
            for (int i = 1; i < n; i++){
                if (n%i == 0){
                    div++;
                }
            }
            if (div == 1) {
                p = n;
            }
        }
        this.reOrganize(p);
    }

    private int getNewSize(int n) {
        n = (n % 2 == 0) ? n + 1 : n + 2;
        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }

    @Override
    public void reOrganize(int newSize) {
        Node<K, T>[] thisArray = this.array;
        Node<K, T>[] cloneHash = new Node[newSize];
        this.setArray(cloneHash);
        this.size = newSize;
        for (Node<K, T> node : thisArray) {
            if (node != null) {
                int index = this.hashFunction(node.getKey());
                while (this.array[index] != null) {
                    index = (index + 1) % size;
                }
                this.array[index] = node;
            }
        }
    }

    @Override
    public void printHash() {
        for (int i = 0; i < size; i++){
            if (array[i] != null) {
                System.out.println(i + " - " + (String) array[i].getValue() + " " +array[i].getKey());
            } else {
                System.out.println("-");
            }
        }
    }

    public boolean contains(K key) {
        int index = hashFunction(key);
        int startIndex = index;
        while (array[index] != null) {
            if (array[index].getKey().equals(key)) {
                return true;
            }
            index = (index + 1) % size;
            if (index == startIndex) {
                break;
            }
        }
        return false;
    }

//    public void reHash() {
//        int newSize = getNewSize(size * 2);
//        Node<K, T>[] newArray = new Node[newSize];
//        Arrays.fill(newArray, null);
//        Node<K, T>[] oldArray = this.array;
//        this.array = newArray;
//        this.size = newSize;
//        this.counter = 0;
//
//        for (Node<K, T> node : oldArray) {
//            if (node != null) {
//                this.add(node.getKey(), node.getValue());
//            }
//        }
//    }


}
