import java.util.LinkedList;

public class HashTable<Clave, Valor> {
    private LinkedList<HashNode<Clave, Valor>>[] chainArray;
    private int numCeldas = 100;
    private int size;

    public HashTable() {
        chainArray = new LinkedList[numCeldas];
        for (int i = 0; i < numCeldas; i++) {
            chainArray[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int dividHash(Clave key) {
        int hashCode = key.hashCode();
        return (hashCode & Integer.MAX_VALUE) % numCeldas;
    }

    private int sumaHash(Clave key) {
        int hashCode = key.hashCode() + 31;
        return (hashCode & Integer.MAX_VALUE) % numCeldas;
    }

    public void put(Clave key, Valor value, boolean useHashCode1) {
        int bucketIndex = useHashCode1 ? dividHash(key) : sumaHash(key);

        for (HashNode<Clave, Valor> node : chainArray[bucketIndex]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        chainArray[bucketIndex].add(new HashNode<>(key, value));
        size++;
    }

    public int size() {
        return size;
    }

    public LinkedList<HashNode<Clave, Valor>>[] getChainArray() {
        return chainArray;
    }

    public int getNumCeldas() {
        return numCeldas;
    }
}
