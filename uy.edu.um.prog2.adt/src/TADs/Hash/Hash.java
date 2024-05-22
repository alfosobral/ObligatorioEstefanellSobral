package TADs.Hash;

import java.util.Arrays;

public class Hash {
    Node<String>[] array;
    Integer size, counter;
    public Hash(Integer size, Integer counter){
        this.size = size;
        this.counter = counter;
        array = new Node[size];
        Arrays.fill(array,null);
    }
    public void funcionHash(String value) {
        if (this.checkCapacity()) {
            this.reSize();
            System.out.println(size);
        }
        Node<String> add = new Node<>(value);
        int arrayIndex = Integer.parseInt(value) % this.size;
        while (array[arrayIndex] != null) {
            arrayIndex++;
            System.out.println("Ocurrió una colisión en el índice " + (arrayIndex - 1) + ". " + "El nuevo índice es " + arrayIndex);
            arrayIndex %= size;
        }
        this.array[arrayIndex] = add;
        counter++;
    }






    public boolean checkCapacity() {
        boolean itsFull = false;
        if (counter == (int) (size*0.75))
            itsFull = true;
        {
            return itsFull;
        }
    }
    public void reSize(){
        int a = size;
        int n = a*2;
        int p = 0;
        while (p == 0) {
            n++;
            int div = 0;
            for (int i =1; i<=n; i++){
                if (n%i == 0){
                    div++;
                }
            }
            if (div == 2) {
                p = n;
            }
        }
        Node[] newHash = new Node[p];
        for (int i = 0; i<a; i++) {
            newHash[i] = this.array[i];
        }
        this.array = newHash;
    }
    public void mostrar() {
        for (int i=0; i< array.length; i++){
            System.out.println(array[i].getValue());
        }
    }

//    public T buscar(){

//    }

    public void insertar(Integer k){

    }

//    public void borrar(Integer k){

//    }
}
