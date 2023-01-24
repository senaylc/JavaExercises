import java.util.TreeSet;

public class Team {
    TreeSet<Employee> members;
    Manager manager;
    String teamName;

    public Team(String teamName, Manager manager, TreeSet<Employee> members) {
        this.manager = manager;
        this.teamName = teamName;
        this.members = members;
    }


    @Override
    public String toString() {
        String s = "Team: " + teamName + " - Manager: " + manager.getName() + " - Members:";
        int c = members.size();
        for (Employee e : members) {
            s += " " + e.getName();
            c--;
            if (c != 0) s += ",";
        }
        return s;
    }
}
