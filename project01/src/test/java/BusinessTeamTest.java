package project01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


class BusinessTeamTest {
    Set<String> productCodes = new HashSet<>();

    @BeforeEach
    void init() {
        productCodes.add("000000000");
        productCodes.add("111111111");
        productCodes.add("222222222");
        productCodes.add("333333333");
        productCodes.add("444444444");
        productCodes.add("555555555");
        productCodes.add("666666666");
        productCodes.add("777777777");
        productCodes.add("888888888");
        productCodes.add("999999999");
    }

    @Test
    void businessTeamTest01() {
//        [ 비지니스 팀 요구사항 ]
//        1. 고객이 상품 교환을 요구하면 가능한지 여부와 교환 결과를 안내해 주세요.
        String command =
                "CLAIM STORE 111111111\n" +
                        "CLAIM STORE 911111111\n" +
                        "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }

    @Test
    void businessTeamTest02() {
//        [ 비지니스 팀 요구사항 ]
//        2. 상품 코드는 10개가 준비되면 고객에게 10개까지만 제공됩니다.
        String command =
                "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }

    @Test
    void businessTeamTest03() {
//        [ 비지니스 팀 요구사항 ]
//        3. 상품 코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어져 있습니다.
        String input =
                "aaaaaaaaa\n" +
                        "1234567890\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Admin admin = new Admin();
        admin.inputProductCodes();
    }

    @Test
    void businessTeamTest04() {
//        [ 비지니스 팀 요구사항 ]
//        4. 상품 코드를 이용하여 상품 교환이 1번 이루어지면, 다시 해당 상품 코드로는 상품 교환을 할 수 없습니다.
        String command =
                "claim aStore 222222222\n" +
                        "claim aStore 222222222\n" +
                        "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }

    @Test
    void businessTeamTest05() {
//        [ 비지니스 팀 요구사항 ]
//        5. 고객은 상품 코드를 사용하기 전에 미리 상품을 교환할 수 있는지 확인이 가능합니다.
        String command =
                "CHECK 333333333\n" +
                        "claim aStore 333333333\n" +
                        "CHECK 333333333\n" +
                        "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }

    @Test
    void businessTeamTest06() {
//        [ 비지니스 팀 요구사항 ]
//        6. SHARETREATS는 고객에게 CHECK, HELP, CLAIM 명령어를 사용할 수 있게 합니다.
//           각 키워드는 CHECK(상품 교환여부 확인), HELP(사용법 안내), CLAIM(상품 교환) 을 의미합니다.
        String command =
                "CHECK 333333333\n" +
                        "claim aStore 333333333\n" +
                        "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }

    @Test
    void businessTeamTest07() {
//        [ 비지니스 팀 요구사항 ]
//        7. SHARETREATS 는 상품 코드 목록을 준비하고 고객은 상품 코드내에서만 상품 교환이 가능합니다.
        String command =
                "CHECK 123123123\n" +
                        "claim aStore 123123123\n" +
                        "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }

    @Test
    void businessTeamTest08() {
//        [ 비지니스 팀 요구사항 ]
//        8. SHARETREATS 는 상품 코드 목록을 준비하고 고객은 상품 코드내에서만 상품 교환이 가능합니다.
        String command =
                "claim 111111111\n" +
                        "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }

    @Test
    void businessTeamTest09() {
//        [ 비지니스 팀 요구사항 ]
//        9. 상점 코드는 A~Z,a~z 까지의 대,소 영문자만 사용이 가능하며 6문자로 이루어져 있습니다.
        String command =
                "claim !STORE 111111111\n" +
                        "claim 1STORE 111111111\n" +
                        "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }
}
