package project03;

public class DepartmentTree {
    private Department superDepartment;
    private Department topDepartment;


    public DepartmentTree() {
        superDepartment = new Department("SUPER", 0);
    }

    public void inputTopDepartment(Department topDepartment) {
        superDepartment.getSubDepartments().add(topDepartment);
        topDepartment.setParentsDepartment(superDepartment);
        if (validTree(topDepartment) == false) {
            superDepartment.getSubDepartments().remove(topDepartment);
            topDepartment.setParentsDepartment(null);
        }
        this.topDepartment = topDepartment;
    }

    public void inputDepartment(Department parents, Department child) {
        if (child.getParentsDepartment() != null) {
            System.out.println("ERROR: 이미 상위부서가 존재합니다.");
            return;
        }
        parents.getSubDepartments().add(child);
        child.setParentsDepartment(parents);
        if (validTree(topDepartment) == false) {
            parents.getSubDepartments().remove(topDepartment);
            child.setParentsDepartment(null);
        }
    }

    public boolean validTree(Department department) {
        Department parentsDepartment = department.getParentsDepartment();

        if (superDepartment.getSubDepartments().size() > 1) {
            System.out.println("ERROR: 최상위 부서는 1개만 입력할수 있습니다.");
            return false;
        }

        if (department == superDepartment) {
            return true;
        }

        while (true) {
            if (parentsDepartment == superDepartment) {
                return true;
            }

            if (parentsDepartment == department) {
                System.out.println("ERROR: 상위 부서의 상위부서가 될수 없습니다");
                return false;
            }
            parentsDepartment = parentsDepartment.getParentsDepartment();
        }
    }

    public Department getSuperDepartment() {
        return superDepartment;
    }

    public void setSuperDepartment(Department superDepartment) {
        this.superDepartment = superDepartment;
    }

    public Department getTopDepartment() {
        return topDepartment;
    }

    public void setTopDepartment(Department topDepartment) {
        this.topDepartment = topDepartment;
    }
}
