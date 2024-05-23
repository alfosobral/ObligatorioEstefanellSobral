package TADs.Hash;

import TADs.Hash.Exceptions.InvalidHashKey;

public class main {
    public static void main(String[] args) throws InvalidHashKey {
        Hash<String, String> h = new Hash<>(5);

        h.add("A", "Prueba1");
        h.add("B", "Prueba2");
        h.add("C", "Prueba3");
        h.add("D", "Prueba4");
        h.add("E", "Prueba5");
        h.add("F", "Prueba6");
        h.add("G", "Prueba7");
        h.add("H", "Prueba8");
        h.add("Z", "Prueba9");

        h.serch("C");
        h.serch("Z");
        System.out.println(" ");
        h.printHash();
        h.remove("E");
        h.printHash();
        h.remove("J");
        h.printHash();
    }

}
