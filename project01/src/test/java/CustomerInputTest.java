package project01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;


public class CustomerInputTest {
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
    void customerInputTest01() {
//        [고객의 입력]
//        1. 문자열은 0~9, a~z, A~Z, SPACE 까지의 문자를 무작위로 입력할 수 있습니다.
        String command =
                "!!!!!!! !!!! !!!!!\n" +
                        "EXIT\n";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }

    @Test
    void customerInputTest02() {
//        [고객의 입력]
//        2. 문자열의 길이는 최대 30글자까지 입력할 것입니다.
        String command =
                "                                       HELP\n" +
                        "CHECK 000000000 111111111 222222222\n" +
                        "CLAIM STORE 111111111 222222222\n" +
                        "EXIT";
        ExchangeMachine machine = new ExchangeMachine(productCodes);
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        machine.run();
    }
}
