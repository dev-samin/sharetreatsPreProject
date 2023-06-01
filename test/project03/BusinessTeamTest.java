package project03;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BusinessTeamTest {
    @Test
    void businessTeamTest01HappyCase() {
//        [ 비지니스 팀 요구사항 ]
//      1. 회사내의 각 부서는 1개 이상입니다.

        String command =
                        "AS, 10\n" +
                        "DEV, 20\n" +
                        "QA, 99\n" +
                        "END";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);

        Admin admin = new Admin();
        admin.inputDepartmentInfo();
//        admin.inputDepartmentStruct();
//
//        StaffCountMachine machine = new StaffCountMachine(admin.getDepartmentTree());
//        machine.run();
    }

    @Test
    void businessTeamTest01ErrorCase() {
//        [ 비지니스 팀 요구사항 ]
//      1. 회사내의 각 부서는 1개 이상입니다.
        String command =
                "END\n" +
                "AS, 10\n" +
                "AS, 10\n" +
                "QA, 99\n" +
                "END";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);

        Admin admin = new Admin();
        admin.inputDepartmentInfo();
    }

    @Test
    void businessTeamTest02HappyCase() {
//        [ 비지니스 팀 요구사항 ]
//        2. 각 부서는 포함 관계를 가집니다.
//           즉  A, B, C 부서가 있다면 B,C 부서는 A 부서에 포함되며, 산하부서가 될 수 있습니다.
        String command =
                "A, 10\n" +
                "B, 20\n" +
                "C, 99\n" +
                "END";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);

        Admin admin = new Admin();
        admin.inputDepartmentInfo();

        command =
                "* > A\n" +
                "A > B\n" +
                "A > C\n" +
                "END";
        in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        admin.inputDepartmentStruct();

        StaffCountMachine machine = new StaffCountMachine(admin.getDepartmentTree());
        machine.run();
    }

    @Test
    void businessTeamTest03HappyCase() {
//        [ 비지니스 팀 요구사항 ]
//      3. 각 부서는 몇 명의 직원이 있는지 인원수 정보를 가지고 있습니다.

    }

    @Test
    void businessTeamTest04HappyCase() {
//        [ 비지니스 팀 요구사항 ]
//      4. 최 상위 부서(다른 부서에 포함되지 않는 부서)별로 해당 부서와 하위부서 내의 모든 직원수를 합한 수를 제공해야 합니다.
        String command =
                "AS, 10\n" +
                "DEV, 20\n" +
                "QA, 99\n" +
                "END";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);

        Admin admin = new Admin();
        admin.inputDepartmentInfo();

        command =
                "* > AS\n" +
                "AS > DEV\n" +
                "DEV > QA\n" +
                "END";
        in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        admin.inputDepartmentStruct();

        StaffCountMachine machine = new StaffCountMachine(admin.getDepartmentTree());
        machine.run();
    }
}
