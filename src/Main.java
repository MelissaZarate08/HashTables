import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String line;
        String splitBy = ",";
        int id = 1;

        // Creación de las tablas hash con diferentes funciones hash
        HashTable<String, String[]> hashTable1 = new HashTable<>();
        HashTable<String, String[]> hashTable2 = new HashTable<>();

        try (BufferedReader br = new BufferedReader(new FileReader("bussines.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] business = line.split(splitBy);
                System.out.println("[" + id + "] Business [ID=" + business[0] + ", Name=" + business[1] + ", Address=" + business[2] + ", City=" + business[3] + ", State= " + business[4] + "]");
                id++;
                String key = business[0];
                // Inserta en las tablas hash
                hashTable1.put(key, business, true);  // Usa división
                hashTable2.put(key, business, false); // Usa suma
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Compara la eficiencia de las dos funciones hash
        compareHashFunctions(hashTable1, hashTable2);
    }

    private static void compareHashFunctions(HashTable<String, String[]> hashTable1, HashTable<String, String[]> hashTable2) {
        int collisionsHash1 = countCollisions(hashTable1);
        int collisionsHash2 = countCollisions(hashTable2);

        System.out.println("Número de colisiones usando dividHash: " + collisionsHash1);
        System.out.println("Número de colisiones usando sumaHash: " + collisionsHash2);

        if (collisionsHash1 < collisionsHash2) {
            System.out.println("dividHash es más eficiente.");
        } else if (collisionsHash1 > collisionsHash2) {
            System.out.println("sumaHash es más eficiente.");
        } else {
            System.out.println("Ambas funciones hash tienen la misma eficiencia.");
        }
    }

    private static int countCollisions(HashTable<String, String[]> hashTable) {
        int collisions = 0;
        for (int i = 0; i < hashTable.getNumCeldas(); i++) {
            if (hashTable.getChainArray()[i].size() > 1) {
                collisions += hashTable.getChainArray()[i].size() - 1;
            }
        }
        return collisions;
    }
}
