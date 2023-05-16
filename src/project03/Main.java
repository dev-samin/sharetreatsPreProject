package project03;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.inputDepartmentInfo();
        admin.inputDepartmentStruct();

        StaffCountMachine machine = new StaffCountMachine(admin.getDepartmentTree());
        machine.run();
    }
}
