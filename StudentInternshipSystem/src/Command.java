import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Command {

    public static void apply(ArrayList<String> appList, ArrayList<Student> studentArrayList, ArrayList<Company> companyArrayList) {
        String company = appList.get(0);
        long student = Long.parseLong(appList.get(1));
        Company company1 = null;
        Student student1 = null;
        for (Company c : companyArrayList) {
            if ((c.getId()).equalsIgnoreCase(company)) {
                company1 = c;
            }
        }
        for (Student s : studentArrayList) {
            if (s.getId() == student) {
                student1 = s;
            }
        }
        assert company1 != null;
        assert student1 != null;

        if ((company1.appliedStudents).size() < company1.getApplication_quota() && (student1.applicationsToCompanies).size() < 5
                && student1.getGpa() >= company1.getMin_accepted_gpa() && student1.getYear() >= company1.getMin_accepted_year()) {
            student1.addCompany(student1.applicationsToCompanies, company1);
            company1.applyStudent(company1.appliedStudents, student1);
            System.out.print("Application from " + student1.getName() + " " + student1.getSurname() + " (" + student1.getId() + ") to " + company1.getName() + " (" + company1.getId() + ") was registered successfully.\n");

        } else {
            System.out.print("Application from " + student1.getName() + " " + student1.getSurname() + " (" + student1.getId() + ") to " + company1.getName() + " (" + company1.getId() + ") was failed.\n");

        }

    }

    public static void admit(ArrayList<String> admList, ArrayList<Student> studentArrayList, ArrayList<Company> companyArrayList) {
        String company = admList.get(1);
        long student = Long.parseLong(admList.get(0));

        Company company1 = null;
        Student student1 = null;
        for (Company c : companyArrayList) {
            if ((c.getId()).equalsIgnoreCase(company)) {
                company1 = c;
            }
        }
        for (Student s : studentArrayList) {
            if (s.getId() == student) {
                student1 = s;
            }
        }
        assert company1 != null;
        assert student1 != null;


        if ((company1.getAppliedStudents()).contains(student1) && (company1.getAdmittedStudents()).size() < company1.getAdmission_quota() && student1.getAdmittedCompany() == null) {
            student1.setAdmittedCompany(company1);
            company1.admitStudent(company1.admittedStudents, student1);
            System.out.print(company1.getName() + " (" + company1.getId() + ") " + "admitted " + student1.getName() + " " + student1.getSurname() + " (" + student1.getId() + ").\n");

        } else {
            System.out.print(company1.getName() + " (" + company1.getId() + ") " + "COULD NOT admit " + student1.getName() + " " + student1.getSurname() + " (" + student1.getId() + ").\n");
        }
    }

    public static void infoCompany(String companyName, ArrayList<Company> companyArrayList) {
        for (Company c : companyArrayList) {
            if ((c.getId()).equalsIgnoreCase(companyName)) {
                System.out.print("\n### START OF COMPANY INFO ###" + "\n" + c.getName() + " (" + c.getId() + "):\n" + "Min. Accepted GPA: " + (String.format(Locale.US, "%.2f", c.getMin_accepted_gpa())) + "\n" +
                        "Min. Accepted Year: " + (int) c.getMin_accepted_year() + "\n" +
                        "Applications: " + c.getAppliedStudents().size() + "/" + c.getApplication_quota() + "\n" +
                        "Admissions: " + c.getAdmittedStudents().size() + "/" + c.getAdmission_quota() + "\n" +
                        "### END OF COMPANY INFO ###\n");
            }
        }
    }

    public static void infoStudent(String stuId, ArrayList<Student> studentArrayList) {
        for (Student s : studentArrayList) {
            if ((s.getId()) == Long.parseLong(stuId)) {
                System.out.print("\n### START OF STUDENT INFO ###" + "\n" + s.getName() + " " + s.getSurname() + " (" + s.getId() + ") - Year: "
                        + s.getYear() + ", GPA: " + (String.format(Locale.US, "%.2f", s.getGpa())) + "\n" + "### END OF STUDENT INFO ###\n");
            }
        }
    }

    public static void listCompanyApplications(String companyName, ArrayList<Company> companyArrayList) {
        for (Company c : companyArrayList) {
            if ((c.getId()).equalsIgnoreCase(companyName)) {
                System.out.print("\n### START OF COMPANY APPLICATIONS ###\n" + "Admitted Students:\n");

                Collections.sort(c.getAdmittedStudents(), new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        if (s1.getGpa() > s2.getGpa())
                            return -1;
                        if (s1.getGpa() < s2.getGpa())
                            return 1;
                        else {
                            if (s1.getYear() > s2.getYear())
                                return -1;
                            if (s1.getYear() < s2.getYear())
                                return 1;
                            else {
                                int index1 = c.getAppliedStudents().indexOf(s1);
                                int index2 = c.getAppliedStudents().indexOf(s2);
                                if (index1 == -1)
                                    return 1;
                                if (index2 == -1)
                                    return -1;
                                else if (index1 < index2)
                                    return -1;
                                else
                                    return 1;
                            }
                        }
                    }

                });
                Collections.sort(c.getAppliedStudents(), new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        if (s1.getGpa() > s2.getGpa())
                            return -1;
                        if (s1.getGpa() < s2.getGpa())
                            return 1;
                        else {
                            if (s1.getYear() > s2.getYear())
                                return -1;
                            if (s1.getYear() < s2.getYear())
                                return 1;
                            else {

                                int index1 = c.getAppliedStudents().indexOf(s1);
                                int index2 = c.getAppliedStudents().indexOf(s2);
                                if (index1 == -1)
                                    return 1;
                                if (index2 == -1)
                                    return -1;
                                else if (index1 < index2)
                                    return -1;
                                else
                                    return 1;
                            }
                        }
                    }
                });


                if (c.getAdmittedStudents().size() == 0) {
                    System.out.print("None\n");
                } else {
                    for (Student s : c.getAdmittedStudents()) {
                        System.out.print(s.getName() + " " + s.getSurname() + " (" + s.getId() + ") - Year: " + s.getYear() + ", GPA: " + String.format(Locale.US, "%.2f", s.getGpa()) + "\n");
                    }
                }
                System.out.print("\nApplied Students:\n");
                if (c.getAppliedStudents().size() == 0) {
                    System.out.print("None\n");
                } else {
                    for (Student s : c.getAppliedStudents()) {
                        System.out.print(s.getName() + " " + s.getSurname() + " (" + s.getId() + ") - Year: " + s.getYear() + ", GPA: " + String.format(Locale.US, "%.2f", s.getGpa()) + "\n");
                    }
                }
                System.out.print("### END OF COMPANY APPLICATIONS ###\n");
            }
        }
    }

    public static void listStudentApplications(String stuId, ArrayList<Student> studentArrayList) {
        for (Student s : studentArrayList) {
            if ((s.getId()) == Integer.parseInt(stuId)) {
                System.out.print("\n### START OF STUDENT APPLICATIONS ###\n" + "Admitted Company:\n");
                if (s.getAdmittedCompany() == null)
                    System.out.print("None\n");
                else {
                    Company admitted = s.getAdmittedCompany();
                    System.out.print(admitted.getName() + " (" + admitted.getId() + ")\n");
                }
                System.out.print("\nApplied Companies:\n");
                if (s.getApplicationsToCompanies().size() == 0) {
                    System.out.print("None\n");
                } else {
                    for (Company c : s.getApplicationsToCompanies()) {
                        System.out.print(c.getName() + " (" + c.getId() + ")\n");
                    }
                }
                System.out.print("### END OF STUDENT APPLICATIONS ###\n");
            }
        }
    }
}

