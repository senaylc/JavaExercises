import java.util.ArrayList;

public class Company {
    String id;
    String name;
    int admission_quota;
    int application_quota;
    float min_accepted_year;
    float min_accepted_gpa;

    public ArrayList<Student> getAppliedStudents() {
        return appliedStudents;
    }

    public void setAppliedStudents(ArrayList<Student> appliedStudents) {
        this.appliedStudents = appliedStudents;
    }


    public ArrayList<Student> getAdmittedStudents() {
        return admittedStudents;
    }

    public void setAdmittedStudents(ArrayList<Student> admittedStudents) {
        this.admittedStudents = admittedStudents;
    }

    ArrayList<Student> admittedStudents = new ArrayList<Student>();
    ArrayList<Student> appliedStudents = new ArrayList<Student>();

    Company(String id, String name, int admission_quota, int application_quota, float min_accepted_year, float min_accepted_gpa) {
        this.id = id;
        this.name = name;
        this.admission_quota = admission_quota;
        this.application_quota = application_quota;
        this.min_accepted_year = min_accepted_year;
        this.min_accepted_gpa = min_accepted_gpa;
    }

    public void applyStudent(ArrayList<Student> stuList, Student stu) {
        stuList.add(stu);
    }

    public void admitStudent(ArrayList<Student> stuList, Student stu) {
        stuList.add(stu);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAdmission_quota() {
        return admission_quota;
    }

    public int getApplication_quota() {
        return application_quota;
    }

    public float getMin_accepted_year() {
        return min_accepted_year;
    }

    public float getMin_accepted_gpa() {
        return min_accepted_gpa;
    }
}
