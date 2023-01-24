// TODO : Modify Person class so that Person objects get sorted according to age (decreasingly). To achieve this,
//  you should implement Comparable interface with implements keyword and override the compareTo method

public class Person implements Comparable<Person> {
    int age;
    String name;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", name, age);
    }

    @Override
    public int compareTo(Person o) {
        return o.age-this.age;
    }
}
