package project03;

public class Main {
    public static void main(String[] args) {
        StaffCountMachine machine = new StaffCountMachine();
        machine.run("src/project03/departmentInfo.txt", "src/project03/departmentStruct.txt");
    }
}
