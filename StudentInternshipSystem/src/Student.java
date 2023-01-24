import java.util.ArrayList;

public class Student {
    long id;
    String name;
    String surname;
    int year;
    float gpa;

    public Company getAdmittedCompany() {
        return admittedCompany;
    }

    public void setAdmittedCompany(Company admittedCompany) {
        this.admittedCompany = admittedCompany;
    }

    Company admittedCompany=null;


    public ArrayList<Company> getApplicationsToCompanies() {
        return applicationsToCompanies;
    }

    public void setApplicationsToCompanies(ArrayList<Company> applicationsToCompanies) {
        this.applicationsToCompanies = applicationsToCompanies;
    }

    ArrayList<Company> applicationsToCompanies = new ArrayList<Company>();

    Student(long id, String name, String surname, int year, float gpa) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.gpa = gpa;
    }

    public void addCompany(ArrayList<Company> comList, Company com) {
        comList.add(com);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }

    public float getGpa() {
        return gpa;
    }


}
