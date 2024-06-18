//package TADs.Hash;
//
//import TADs.Hash.Exceptions.InvalidHashKey;
//
//import java.util.Arrays;
//
//public class Hash<K, T> implements MyHash<K, T>{
//    private Node<K, T>[] array;
//    private Integer size, counter;
//
//    public Hash(Integer size){
//        this.size = size;
//        this.counter = 0;
//        array = new Node[size];
//        Arrays.fill(array,null);
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public int getCounter() {
//        return counter;
//    }
//
//    public void setArray(Node<K,T>[] array) {
//        this.array = array;
//    }
//
//    @Override
//    public int hashFunction(K key) {
//        int index = 0;
//        String keyToStr = key.toString();
//        for (int i = 0; i < keyToStr.length(); i++){
//            index += keyToStr.charAt(i);
//        }
//        index = index%size;
//        return index;
//    }
//
//    @Override
//    public void add(K key, T value) {
//        Node<K, T> add = new Node<>(key, value);
//        if (this.checkCapacity()) {
//            this.reSize();
//        }
//        int index = this.hashFunction(key);
//        if (this.array[index] != null) {
//            do {
//                index = (index+1)%size;
//            }
//            while (this.array[index] != null);
//        }
//        this.array[index] = add;
//        this.counter++;
//
//    }
//
//    @Override
//    public void remove(K key) throws InvalidHashKey {
//        int index = this.hashFunction(key);
//        int oIndex = index;
//        boolean keyFound = false;
//        if (this.array[index] != null && this.array[index].getKey().equals(key)) {
//            keyFound = true;
//        } else {
//            if (this.array[index]==null){
//                while (this.array[index]==null){
//                index = (index+1)%size;
//                }
//            }
//            do {
//                index = (index + 1) % size;
//            }
//            while (this.array[index] != null && !this.array[index].getKey().equals(key));
//            if (this.array[index] == null) {
//                throw new InvalidHashKey();
//            } else {
//                keyFound = true;
//            }
//        }
//        if (keyFound){
//            this.array[index] = null;
//            this.reOrganize(this.size);
//        }
//    }
//
//    @Override
//    public T search(K key) throws InvalidHashKey {
//        T value;
//        int index = this.hashFunction(key);
//        int oIndex = index;
//        if (array[index] != null) {
//            while (!array[index].getKey().equals(key)) {
//                index = (index + 1) % size;
//                if (index == oIndex) {
//                    throw new InvalidHashKey();
//                }
//            }
//        } else {
//            throw new InvalidHashKey();
//        }
//        value = this.array[index].getValue();
//        return value;
//    }
//
//    @Override
//    public boolean checkCapacity() {
//        boolean itsFull = false;
//        if (counter >= (int) (size*0.60)) {
//            itsFull = true;
//        }
//        return itsFull;
//    }
//
//    @Override
//    public void reSize(){
//        int n = size*2;
//        int p = 0;
//        while (p == 0) {
//            n++;
//            int div = 0;
//            for (int i = 1; i < n; i++){
//                if (n%i == 0){
//                    div++;
//                }
//            }
//            if (div == 1) {
//                p = n;
//            }
//        }
//        this.reOrganize(p);
//    }
//
//    @Override
//    public void reOrganize(int newSize){
//        Node<K,T>[] thisArray = this.array;
//        Node<K,T>[] cloneHash = new Node[newSize];
//        this.setArray(cloneHash);
//        this.size = newSize;
//        for (int j = 0; j<thisArray.length; j++){
//            if (thisArray[j] != null) {
//                int index = this.hashFunction(thisArray[j].getKey());
//                if (this.array[index] != null) {
//                    do {
//                        index = (index + 1) % size;
//                    }
//                    while (this.array[index] != null);
//                }
//                this.array[index] = thisArray[j];
//            }
//        }
//    }
//
//    @Override
//    public void printHash() {
//        for (int i = 0; i < size; i++){
//            if (array[i] != null) {
//                System.out.println((String) array[i].getValue() + " " +array[i].getKey());
//            } else {
//                System.out.println("-");
//            }
//        }
//    }
//
//    public boolean contains(K key) {
//        int index = this.hashFunction(key);
//        int oIndex = index;
//        while (array[index] != null) {
//            if (array[index].getKey().equals(key)) {
//                return true;
//            }
//            index = (index + 1) % size;
//            if (index == oIndex) {
//                break;
//            }
//        }
//        return false;
//    }
//
//    public Node<K, T>[] getArray() {
//        return array;
//    }
//}

package TADs.Hash;

import TADs.Hash.Exceptions.InvalidHashKey;

import java.util.Arrays;

public class Hash<K, T> implements MyHash<K, T> {
    private Node<K, T>[] array;
    private int size, counter;

    public Hash(int size) {
        this.size = size;
        this.counter = 0;
        array = new Node[size];
        Arrays.fill(array, null);
    }

    public int getSize() {
        return size;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public int hashFunction(K key) {
        int hash = 7;
        String keyStr = key.toString();
        for (int i = 0; i < keyStr.length(); i++) {
            hash = hash * 31 + keyStr.charAt(i);
        }
        return Math.abs(hash % size);
    }

    @Override
    public void add(K key, T value) {
        if (checkCapacity()) {
            reSize();
        }
        int index = findSlot(key);
        if (array[index] == null) {
            array[index] = new Node<>(key, value);
            counter++;
        }
    }

    @Override
    public void remove(K key) throws InvalidHashKey {
        int index = findSlotForKey(key);
        if (array[index] == null) {
            throw new InvalidHashKey();
        }
        array[index] = null;
        counter--;
        reOrganize();
    }

    @Override
    public T search(K key) throws InvalidHashKey {
        int index = findSlotForKey(key);
        if (array[index] == null) {
            throw new InvalidHashKey();
        }
        return array[index].getValue();
    }

    @Override
    public boolean checkCapacity() {
        return counter >= size * 0.60;
    }

    @Override
    public void reSize() {
        int newSize = getNextPrime(size * 2);
        Node<K, T>[] oldArray = array;
        array = new Node[newSize];
        size = newSize;
        counter = 0;
        for (Node<K, T> node : oldArray) {
            if (node != null) {
                add(node.getKey(), node.getValue());
            }
        }
    }

    @Override
    public void reOrganize() {
        Node<K, T>[] oldArray = array;
        array = new Node[size];
        counter = 0;
        for (Node<K, T> node : oldArray) {
            if (node != null) {
                add(node.getKey(), node.getValue());
            }
        }
    }

    private int findSlot(K key) {
        int index = hashFunction(key);
        while (array[index] != null && !array[index].getKey().equals(key)) {
            index = (index + 1) % size;
        }
        return index;
    }

    private int findSlotForKey(K key) throws InvalidHashKey {
        int index = hashFunction(key);
        int startIndex = index;
        while (array[index] != null && !array[index].getKey().equals(key)) {
            index = (index + 1) % size;
            if (index == startIndex) {
                throw new InvalidHashKey();
            }
        }
        return index;
    }

    private int getNextPrime(int number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number++;
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }

    @Override
    public void printHash() {
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                System.out.println(array[i].getValue() + " " + array[i].getKey());
            } else {
                System.out.println("-");
            }
        }
    }

    public boolean contains(K key) {
        try {
            int index = findSlotForKey(key);
            return array[index] != null;
        } catch (InvalidHashKey e) {
            return false;
        }
    }

    public Node<K, T>[] getArray() {
        return array;
    }

    public void relace(K key, T value) throws InvalidHashKey {
        int index = findSlotForKey(key);
        if (array[index] == null) {
            throw new InvalidHashKey();
        }
        array[index] = new Node<>(key, value);
    }
}
