import java.util.ArrayList;
import java.util.Collections;

public class MySortedSet<T extends Comparable<T>> {
    ArrayList<T> sortedSet = new ArrayList<>();

    public MySortedSet() {
    }

    public boolean contains(T item) {
        for (T i : sortedSet) {
            if (i.equals(item))
                return true;
        }
        return false;
    }

    public void add(T item) {
        if (!sortedSet.contains(item)) {
            sortedSet.add(item);
            Collections.sort(sortedSet);
        } else System.out.print(item + " already exists in the set.\n");
    }

    public void print() {
        for (T i : sortedSet) {
            System.out.print(i.toString() + "\n");
        }
    }


    public void remove(T item) {
        if (sortedSet.contains(item)) {
            sortedSet.remove(item);
            Collections.sort(sortedSet);
        } else System.out.print(item + " does not exist in the set.\n");
    }

}
