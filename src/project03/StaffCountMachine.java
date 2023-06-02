package project03;

public class StaffCountMachine {
    DepartmentTree departmentTree;

    StaffCountMachine(DepartmentTree departmentTree) {
        this.departmentTree = departmentTree;
    }

    public void run() {
        printResult();
    }


    private void printResult() {
        Department topDepartment = departmentTree.getTopDepartment();
        int total = topDepartment.calSubStaffCount();
        System.out.println("최상위 부서 " + topDepartment.getName() + "의 총인원은 " + total + " 명 입니다");
    }
}
