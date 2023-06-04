package project03;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CustomerInputTest {

    @Test
    void customerInputTest01() {
//        [ 고객의 입력 ]
//        1. 고객은 부서명과 인원수, 부서간의 관계를 제공합니다.
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
    void customerInputTest02() {
//        [ 고객의 입력 ]
//        2. 부서명은 A-Z 까지 영문자 대문자로만 제공됩니다.
        String command =
                "123, 123\n" +
                ",123\n" +
                "A, 10\n" +
                "END";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);

        Admin admin = new Admin();
        admin.inputDepartmentInfo();
    }

    @Test
    void customerInputTest03() {
//        [ 고객의 입력 ]
//        3. 인원수는 최소 0명에서 1000명의 범위입니다.
        String command =
                "AS, 1001\n" +
                "A, 10\n" +
                "END";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);

        Admin admin = new Admin();
        admin.inputDepartmentInfo();
    }

    @Test
    void customerInputTest04() {
//        [ 고객의 입력 ]
//        3. 인원수는 최소 0명에서 1000명의 범위입니다.
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
                "AS < *\n" +
                "* > AS\n" +
                "END";
        in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        admin.inputDepartmentStruct();

    }

    @Test
    void customerInputTest05() {
//        [ 고객의 입력 ]
//        5. 최상위 부서 표현은 * > A 로 표현합니다.
//          즉, A 를 포함하는 상위 부서는 없습니다.
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
                "DEV > AS\n" +
                "* > DEV\n" +
                "END";
        in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        admin.inputDepartmentStruct();
    }

    @Test
    void customerInputTest06() {
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
                "QA > DEV\n" +
                "END\n";
        in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        admin.inputDepartmentStruct();
    }

}
