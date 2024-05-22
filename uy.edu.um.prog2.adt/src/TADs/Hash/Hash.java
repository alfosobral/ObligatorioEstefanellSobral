package TADs.Hash;

import TADs.Hash.Exceptions.InvalidHashKey;

import java.util.Arrays;

public class Hash<T, K> implements MyHash<K, T>{
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
//    public void funcionHash(String value) {
//        if (this.checkCapacity()) {
//            this.reSize();
//            System.out.println(size);
//        }
//        Node<String> add = new Node<>(value);
//        int arrayIndex = Integer.parseInt(value) % this.size;
//        while (array[arrayIndex] != null) {
//            arrayIndex++;
//            System.out.println("Ocurrió una colisión en el índice " + (arrayIndex - 1) + ". " + "El nuevo índice es " + arrayIndex);
//            arrayIndex %= size;
//        }
//        this.array[arrayIndex] = add;
//        counter++;
//    }


    @Override
    public int hashFunction(K key) {
        int index = 0;
        String keyToStr = key.toString();
        for (int i = 0; i < keyToStr.length(); i++){
            index += keyToStr.charAt(i);
        }
        index = index%size;
        return index;
    }

    @Override
    public void add(K key, T value) {
        Node<K, T> add = new Node<>(key, value);
        if (this.checkCapacity()) {
            this.reSize();
        }
        int index = this.hashFunction(key);
        if (this.array[index] != null) {
            do {
                index = (index+1)%size;
            }
            while (this.array[index] != null);
        }
        this.array[index] = add;
        this.counter++;

    }

    @Override
    public void remove(K key) throws InvalidHashKey{
        int index = this.hashFunction(key);
        int oIndex = index;
        if (!this.array[index].getKey().equals(key)) {
            while (!this.array[index].getKey().equals(key)) {
                index = (index + 1) % size;
                if (index == oIndex) {
                    throw new InvalidHashKey();
                }
            }
        }
        this.array[index] = null;
    }

    @Override
    public T serch(K key) throws InvalidHashKey {
        T value = null;
        int index = this.hashFunction(key);
        int oIndex = index;
        if (array[index] != null) {
            while (!array[index].getKey().equals(key)) {
                do {
                    index = (index + 1) % size;
                } while (array[index] != null);
                if (index == oIndex) {
                    throw new InvalidHashKey();
                }
            }
        } else {
            throw new InvalidHashKey();
        }
        value = this.array[index].getValue();
        return value;
    }


    public boolean checkCapacity() {
        boolean itsFull = false;
        if (counter >= (int) (size*0.60)) {
            itsFull = true;
        }
        return itsFull;
    }
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
        Node<K,T>[] newHash = new Node[p];
        for (int i = 0; i < size; i++) {
            newHash[i] = this.array[i];
        }
        this.setSize(p);
        this.setArray(newHash);
    }
    public void printHash() {
        for (int i = 0; i < size; i++){
            if (array[i] != null) {
            System.out.println(array[i].getValue());
            } else {
                System.out.println("-");
            }
        }
    }


}
