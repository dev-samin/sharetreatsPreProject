package project03;

import java.util.ArrayList;

public class Department {
    private String name;
    private int staffCount;
    private ArrayList<Department> subDepartments;

    private Department parentsDepartment;

    Department(String name, int staffCount) {
        this.name = name;
        this.staffCount = staffCount;
        subDepartments = new ArrayList<>();
        parentsDepartment = null;
    }

    public int calSubStaffCount() {
        int totalStaffCount = staffCount;
        if (subDepartments.size() != 0) {
            for (Department subDepartment : subDepartments) {
                totalStaffCount += subDepartment.calSubStaffCount();
            }
        }
        return totalStaffCount;
    }

    public int getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(int staffCount) {
        this.staffCount = staffCount;
    }

    public ArrayList<Department> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(ArrayList<Department> subDepartments) {
        this.subDepartments = subDepartments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getParentsDepartment() {
        return parentsDepartment;
    }

    public void setParentsDepartment(Department parentsDepartment) {
        this.parentsDepartment = parentsDepartment;
    }
}

