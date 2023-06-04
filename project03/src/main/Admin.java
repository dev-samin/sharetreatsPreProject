package project03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {
    private final Map<String, Department> departments;
    private final DepartmentTree departmentTree;

    public Admin() {
        departments = new HashMap<>();
        departmentTree = new DepartmentTree();
    }

    public void inputDepartmentInfo() {
        System.out.println("부서 정보 입력");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine().toUpperCase();

            if (line.equals("END")) {
                if (departments.size() == 0) {
                    System.out.println("1개 이상의 부서를 입력해 주세요");
                    continue;
                }
                System.out.println("입력 완료");
                return;
            }

            if (checkFormat(line, "^[a-zA-Z]+, [0-9]+$") == false) {
                System.out.println("형식에 맞지 않습니다.");
                continue;
            }

            String[] token = line.split(", ");
            String departmentName = token[0];
            if (departments.containsKey(departmentName) == true) {
                System.out.println("이미 존재하는 부서입니다.");
                continue;
            }
            try {
                int staffCount = Integer.parseInt(token[1]);
                if (staffCount > 1000) {
                    System.out.println("0 ~ 1000명 이하의 인원만 입력해 주세요");
                    continue;
                }
                departments.put(departmentName, new Department(departmentName, staffCount));
            } catch (NumberFormatException e) {
                System.out.println("정수범위의 숫자만 입력해 주세요");
            }
        }
    }

    public void inputDepartmentStruct() {
        System.out.println("부서 구조 입력");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine().toUpperCase();

            if (line.equals("END")) {
                if (departmentTree.getTopDepartment() == null) {
                    System.out.println("최상위 부서가 입력되지 않습니다.");
                    continue;
                }
                System.out.println("입력 완료");
                return;
            }

            if (checkFormat(line, "^[*a-zA-Z]+ > [a-zA-Z]+$") == false) {
                System.out.println("[* > 부서명] 또는 [부서명 > 부서명] 형식에 맞지 않습니다.");
                continue;
            }

            String[] token = line.split(" ");
            if (token[0].equals("*")) {
                Department childDepartment = departments.get(token[2]);
                if (childDepartment == null) {
                    System.out.println("존재하지 않는 부서입니다.");
                    continue;
                }
                departmentTree.inputTopDepartment(childDepartment);
            } else {
                if (departmentTree.getTopDepartment() == null) {
                    System.out.println("최상위 부서를 입력하세요");
                    continue;
                }

                Department parentDepartment = departments.get(token[0]);
                Department childDepartment = departments.get(token[2]);
                if (parentDepartment == null) {
                    System.out.println(token[0] + "는 존재하지 않는 부서입니다.");
                    continue;
                }
                if (childDepartment == null) {
                    System.out.println(token[2] + "는 존재하지 않는 부서입니다.");
                    continue;
                }
                departmentTree.inputDepartment(parentDepartment, childDepartment);
            }
        }
    }


    private boolean checkFormat(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public DepartmentTree getDepartmentTree() {
        return departmentTree;
    }
}


