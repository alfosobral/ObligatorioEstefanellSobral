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
        Node<K,T>[] thisArray = this.array;
        Node<K,T>[] cloneHash = new Node[p];
        this.setArray(cloneHash);
        this.setSize(p);
//        Node<K,T>[] newHash = new Node[p];
//        int index;
//        for (int i = 0; i < thisArray.length; i++) {
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







//            if (cloneHash[i] != null){
//
//
//                index = this.hashFunction(cloneHash[i].getKey());
//                if(newHash[index] != null) {
//                do {
//                    index = (index + 1) % size;
//                    System.out.println("A colision ocurred in index " + (index - 1) + ". " + "New index is " + index);
//                } while (newHash[index] != null);
//                newHash[index] = cloneHash[i];
//
//                } else {
//                    newHash[index] = cloneHash[i];
//                }
//            }


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
