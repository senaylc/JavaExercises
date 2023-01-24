{
    static String companiesFilepath;
    static String studentsFilepath;
    static String commandsFilepath;
    static String outputFilepath;
    static ArrayList<Student> studentArrayList = new ArrayList<Student>();
    static ArrayList<Company> companiesArrayList = new ArrayList<Company>();

    public static void main(String[] args) {
        companiesFilepath = args[0]; // companies.txt filepath
        studentsFilepath = args[1]; // students.txt filepath
        commandsFilepath = args[2]; // commands.txt filepath
        outputFilepath = args[3]; // output.txt filepath

        ArrayList<String> commandsList = new ArrayList<String>();


        File file = new File(outputFilepath);
        //Instantiating the PrintStream class
        PrintStream stream = null;
        try {
            stream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(stream);

        companiesArrayList = instantiateCompany(parseFile(readFile(companiesFilepath)));
        commandsList = readFile(commandsFilepath);

        studentArrayList = instantiateStudent(parseFile(readFile(studentsFilepath)));
        readCommand(commandsList);
    }

    public static void readCommand(ArrayList<String> commandList) {
        for (String c : commandList) {
            if (c.contains("APPLY")) {
                ArrayList<String> appList = new ArrayList<String>();
                String[] app = c.split(" ");
                appList.add(app[2]);
                appList.add(app[4]);
                Command.apply(appList, studentArrayList, companiesArrayList);

            }
            if (c.contains("ADMIT")) {
                ArrayList<String> admitList = new ArrayList<String>();
                String[] adm = c.split(" ");
                admitList.add(adm[1]);
                admitList.add(adm[3]);
                Command.admit(admitList, studentArrayList, companiesArrayList);
            }
            if (c.contains("INFO COMPANY")) {
                String tempComp;
                String[] inf = c.split(" ");
                tempComp = inf[2];
                Command.infoCompany(tempComp, companiesArrayList);
            }
            if (c.contains("LIST APPLICATIONS TO")) {
                String tempComp;
                String[] inf = c.split(" ");
                tempComp = inf[3];
                Command.listCompanyApplications(tempComp, companiesArrayList);
            }
            if (c.contains("INFO STUDENT")) {
                String tempStu;
                String[] stu = c.split(" ");
                tempStu = stu[2];
                Command.infoStudent(tempStu, studentArrayList);

            }
            if(c.contains("LIST APPLICATIONS BY")){
                String tempStu;
                String[] inf = c.split(" ");
                tempStu = inf[3];
                Command.listStudentApplications(tempStu, studentArrayList);
            }

        }
    }


    public static ArrayList<Student> instantiateStudent(ArrayList<ArrayList<String>> studentList) {
        ArrayList<Student> stuArr = new ArrayList<Student>();
        for (ArrayList<String> stu : studentList) {
            stuArr.add(new Student(Long.parseLong(stu.get(0)), stu.get(1), stu.get(2), Integer.parseInt(stu.get(3)), Float.parseFloat(stu.get(4))));
        }

        return stuArr;
    }

    public static ArrayList<Company> instantiateCompany(ArrayList<ArrayList<String>> comList) {
        ArrayList<Company> comArr = new ArrayList<Company>();
        for (ArrayList<String> com : comList) {
            comArr.add(new Company(com.get(0), com.get(1), Integer.parseInt(com.get(2)), Integer.parseInt(com.get(3)), Integer.parseInt(com.get(4)), Float.parseFloat(com.get(5))));
        }
        return comArr;

    }

    public static ArrayList<String> readFile(String args) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner scn = new Scanner(new File(args));
            list = new ArrayList<String>();
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                list.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<ArrayList<String>> parseFile(ArrayList<String> tempList) {
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        tempList.remove(0);
        while (tempList.size() != 0) {
            ArrayList<String> list = new ArrayList<>();
            String[] array2 = (tempList.get(0)).split("\t");
            Collections.addAll(list, array2);
            array.add(list);
            tempList.remove(0);
        }
        return array;
    }
}
