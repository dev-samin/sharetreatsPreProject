package project03;

import java.util.ArrayList;

public class Department {
    private String name;
    private int staffCount;
    private ArrayList<Department> subDepartments;

    Department(int staffCount, String name) {
        this.staffCount = staffCount;
        this.name = name;
        subDepartments = new ArrayList<>();
    }

    public int calTotalStaffCount() {
        int totalStaffCount = staffCount;
        if (subDepartments.size() != 0) {
            for (int i = 0; i < subDepartments.size(); i++) {
                totalStaffCount += subDepartments.get(i).calTotalStaffCount();
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
}

