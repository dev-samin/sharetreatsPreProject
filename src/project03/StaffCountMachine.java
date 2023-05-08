package project03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffCountMachine {
    Map<String, Department> departments;
    Department topDepartment;

    StaffCountMachine() {
        this.departments = new HashMap<>();
    }

    public void run(String departmentInfoPath, String departmentStructPath) {
        inputDepartmentInfo(departmentInfoPath);
        inputDepartmentStruct(departmentStructPath);
        printResult();
    }

    private void inputDepartmentInfo(String departmentInfoPath) {
        File file = new File(departmentInfoPath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toUpperCase();
                if (checkFormat(line, "^[a-zA-Z]+, [0-9]+$") == false) {
                    throw new IllegalArgumentException("형식에 맞지 않습니다.");
                }
                String[] token = line.split(", ");
                String departmentName = token[0];
                try {
                    int staffCount = Integer.parseInt(token[1]);
                    departments.put(departmentName, new Department(staffCount, departmentName));
                } catch (NumberFormatException e) {
                    System.out.println("정수범위의 숫자만 입력해 주세요");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void inputDepartmentStruct(String departmentStructPath) {
        File file = new File(departmentStructPath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toUpperCase();
                if (checkFormat(line, "^[*a-zA-Z]+ > [a-zA-Z]+$") == false) {
                    throw new IllegalArgumentException("[부서명 > 부서명] 또는 [* > 부서명] 형식에 맞지 않습니다.");
                }
                String[] token = line.split(" ");

                if (token[0].equals("*")) {
                    Department childDepartment = departments.get(token[2]);
                    if (childDepartment == null) {
                        System.out.println("존재하지 않는 부서입니다.");
                    }
                    topDepartment = childDepartment;
                } else {
                    Department parentDepartment = departments.get(token[0]);
                    Department childDepartment = departments.get(token[2]);
                    if (parentDepartment == null) {
                        System.out.println("존재하지 않는 부서입니다.");
                    }
                    if (childDepartment == null) {
                        System.out.println("존재하지 않는 부서입니다.");
                    }
                    parentDepartment.getSubDepartments().add(childDepartment);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkFormat(String str, String pattern) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    private void printResult() {
        int total = topDepartment.calTotalStaffCount();
        System.out.println(topDepartment.getName() + ", " + total);
    }
}
