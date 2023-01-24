import java.util.*;

public class Company {

    HashMap<String, Team> teamsMap;

    HashMap<String, StaffMember> staffMemberMap;

    public Company() {
        staffMemberMap = new HashMap<>();
        teamsMap = new HashMap<>();
    }

    public TreeSet<HourlyWorker> getTopWorkedHourlyWorkers(int count) throws InvalidCommandException {
        TreeSet<HourlyWorker> hourlySet = new TreeSet<>(new HourlyComparator());
        TreeSet<HourlyWorker> hourlySet2 = new TreeSet<>(new HourlyComparator());
        if (count < 1 || count > staffMemberMap.size()) {
            throw new InvalidCommandException("Invalid count (" + count + ") argument passed to getTopWorkedHourlyWorkers.");
        }
        for (StaffMember s : staffMemberMap.values()) {
            if (s instanceof HourlyWorker) {
                hourlySet.add((HourlyWorker) s);
            }
        }
        ArrayList<HourlyWorker> temp = new ArrayList<>(hourlySet);
        for (int i = 0; i < count; i++) {
            hourlySet2.add(temp.get(i));
        }
        return hourlySet2;
    }

    public TreeMap<Employee, Double> getTopPaidEmployees(int count) throws InvalidCommandException {
        TreeSet<Employee> temp = new TreeSet<>(new EmployeeComparator());
        TreeMap<Employee, Double> topPaid = new TreeMap<>(new EmployeeComparator());
        if (count < 1 || count > staffMemberMap.size()) {
            throw new InvalidCommandException("Invalid count (" + count + ") argument passed to getTopPaidEmployees.");
        }
        for (StaffMember s : staffMemberMap.values()) {
            if (s instanceof Employee) {
                temp.add((Employee) s);
            }
        }
        ArrayList<Employee> temp2 = new ArrayList<>(temp);
        for (int i = 0; i < count; i++) {
            Employee e = temp2.get(i);
            Double d = e.calculatePayment();

            topPaid.put(e, d);
        }
        return topPaid;
    }

    public void addStaffMember(StaffMember member) throws InvalidCommandException {
        for (String s : staffMemberMap.keySet()) {
            if (s.equals(member.getName())) {
                throw new InvalidCommandException("Staff member " + member.getName() + " already exists in company.");
            }
        }
        staffMemberMap.put(member.getName(), member);
        System.out.print("Staff member " + member.getName() + " added to company.\n");
    }

    public void removeStaffMember(String name) throws InvalidCommandException {
        for (String s : staffMemberMap.keySet()) {
            if (s.equals(name)) {
                staffMemberMap.remove(s);
                System.out.print("Staff member " + name + " removed from company.\n");
                return;
            }
        }
        throw new InvalidCommandException("Staff member " + name + " does not exist in company.");
    }

    public void addNewTeam(String teamName, String managerName, HashSet<String> teamMemberNames) throws InvalidCommandException {
        Manager m = null;
        if (!staffMemberMap.containsKey(managerName)) {
            throw new InvalidCommandException("There is no manager with name " + managerName + ".");
        } else if (!(staffMemberMap.get(managerName) instanceof Manager)) {
            throw new InvalidCommandException("There is no manager with name " + managerName + ".");
        }
        for (String s : staffMemberMap.keySet()) {
            if (s.equals(managerName)) {
                if (staffMemberMap.get(s) instanceof Manager) m = (Manager) staffMemberMap.get(s);
            }
        }
        TreeSet<Employee> employees = new TreeSet<>(new StringComparator());
        for (String name : teamMemberNames) {
            if (!staffMemberMap.containsKey(name)) {
                throw new InvalidCommandException("There is no employee with name " + name + ".");
            } else if (staffMemberMap.get(name) instanceof Volunteer) {
                throw new InvalidCommandException("There is no employee with name " + name + ".");
            }
            for (String ss : staffMemberMap.keySet()) {
                if (ss.equals(name)) {
                    if (staffMemberMap.get(ss) instanceof Employee) employees.add((Employee) staffMemberMap.get(ss));
                }
            }
        }
        Team t = new Team(teamName, m, employees);
        teamsMap.put(teamName, t);
        System.out.print("Team with name " + teamName + " added.\n");
    }

    public Team getTeamByName(String teamName) throws InvalidCommandException {
        for (String s : teamsMap.keySet()) {
            if (s.equals(teamName)) return teamsMap.get(teamName);
        }
        throw new InvalidCommandException("Team with name " + teamName + " not found.");
    }

    public void printStaffMembers() {
        ArrayList<String> sortedKeys = new ArrayList<>(staffMemberMap.keySet());
        Collections.sort(sortedKeys);
        for (String s : sortedKeys) {
            System.out.print(s + " => ");
            if (staffMemberMap.get(s) instanceof Manager) System.out.print("Manager\n");
            else if (staffMemberMap.get(s) instanceof HourlyWorker) System.out.print("HourlyWorker\n");
            else if (staffMemberMap.get(s) instanceof Volunteer) System.out.print("Volunteer\n");
            else System.out.print("Employee\n");
        }

    }

    public HashMap<String, Team> getTeamsMap() {
        return teamsMap;
    }

    public HashMap<String, StaffMember> getStaffMemberMap() {
        return staffMemberMap;
    }
}
