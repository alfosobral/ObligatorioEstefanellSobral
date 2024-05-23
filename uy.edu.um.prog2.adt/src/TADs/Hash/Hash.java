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
        System.out.println("The index is " + index + " for the element " + value + " " + key);
        if (this.array[index] != null) {
            do {
                index = (index+1)%size;
                System.out.println("A colision ocurred in index " + (index - 1) + ". " + "New index is " + index);
            }
            while (this.array[index] != null);
        }
        this.array[index] = add;
        this.counter++;

    }

    @Override
    public void remove(K key) throws InvalidHashKey {
        int index = this.hashFunction(key);
        int oIndex = index;
        boolean keyFound = false;
        if (this.array[index] != null && this.array[index].getKey().equals(key)) {
            keyFound = true;
        } else {
            if (this.array[index]==null){
                while (this.array[index]==null){
                index = (index+1)%size;
                }
            }
            do {
                index = (index + 1) % size;
            }
            while (this.array[index] != null && !this.array[index].getKey().equals(key));
            if (this.array[index] == null) {
                throw new InvalidHashKey();
            } else {
                keyFound = true;
            }
        }
        if (keyFound){
            this.array[index] = null;
            this.reOrganize(this.size);
        }
    }

    @Override
    public T serch(K key) throws InvalidHashKey {
        T value;
        int index = this.hashFunction(key);
        System.out.println("Index is " + index + " for " + key);
        int oIndex = index;
        if (array[index] != null) {
            while (!array[index].getKey().equals(key)) {
                index = (index + 1) % size;
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
        this.reOrganize(p);


    }

    public void reOrganize(int newSize){
        Node<K,T>[] thisArray = this.array;
        Node<K,T>[] cloneHash = new Node[newSize];
        this.setArray(cloneHash);
        this.setSize(newSize);
        for (int j = 0; j<thisArray.length; j++){
            if (thisArray[j] != null) {
                int index = this.hashFunction(thisArray[j].getKey());
                if (this.array[index] != null) {
                    do {
                        index = (index + 1) % size;
                    }
                    while (this.array[index] != null);
                }
                this.array[index] = thisArray[j];
            }
        }
        System.out.println("New size is " + size);
    }
    public void printHash() {
        for (int i = 0; i < size; i++){
            if (array[i] != null) {
                System.out.println((String) array[i].getValue() + " " +array[i].getKey());
            } else {
                System.out.println("-");
            }
        }
    }


}
