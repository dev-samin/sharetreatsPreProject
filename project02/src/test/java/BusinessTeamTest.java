package project02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;

class BusinessTeamTest {

    LinkedList<Product> products = new LinkedList<>();

    @BeforeEach
    void init() {
        products.add(new Product("Chicken", "A", "2024-03-23T02:20:19+09:00"));
        products.add(new Product("Cider", "A", "2024-03-23T02:20:19+09:00"));
        products.add(new Product("cola", "A", "2024-03-21T01:00:32+09:00"));
        products.add(new Product("Bakery", "A", "2023-03-21T01:00:32+09:00"));
        products.add(new Product("Bread", "A", "2023-03-21T01:00:32+09:00"));
        products.add(new Product("Computer", "B", "2024-03-21T01:00:32+09:00"));
        products.add(new Product("Phone", "B", "2024-03-21T01:00:32+09:00"));
        products.add(new Product("Radio", "B", "2024-03-21T01:00:32+09:00"));
        products.add(new Product("Cup", "B", "2023-03-21T01:00:32+09:00"));
        products.add(new Product("Bag", "B", "2023-03-21T01:00:32+09:00"));
    }

    @Test
    void businessTeamTest01HappyCase() {
//        [비즈니스팀 요구사항]
//        1. 돈은 자연수로 정의되며 100 이라는 숫자는 여기서 100원을 의미합니다.
        String command =
                "INSERT 100\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(products);
        machine.run();
    }

    @Test
    void businessTeamTest01ErrorCase() {
//        [비즈니스팀 요구사항]
//        1. 돈은 자연수로 정의되며 100 이라는 숫자는 여기서 100원을 의미합니다.
        String command =
                "INSERT money\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(products);
        machine.run();
    }

    @Test
    void businessTeamTest02HappyCase() {
//        [비즈니스팀 요구사항]
//        2. 뽑기 1회당 100원의 돈이 차감됩니다.
//           즉, 200원을 고객이 사용하면, 뽑기 서비스는 총 2번의 뽑기 기회를 제공합니다.
        String command =
                "DRAW 2\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(products);
        machine.run();
    }

    @Test
    void businessTeamTest02ErrorCase() {
//        [비즈니스팀 요구사항]
//        2. 뽑기 1회당 100원의 돈이 차감됩니다.
//           즉, 200원을 고객이 사용하면, 뽑기 서비스는 총 2번의 뽑기 기회를 제공합니다.
        String command =
                "DRAW ERROR\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(products);
        machine.run();
    }

    @Test
    void businessTeamTest03HappyCase() {
//        [비즈니스팀 요구사항]
//        3. 고객은 "가상 지갑"에 돈을 충전할 수 있습니다.
//           뽑기한 수 만큼 "가상 지갑"에서 돈이 차감됩니다.
        String command =
                "INSERT 10000\n" +
                        "DRAW 2\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(products);
        machine.run();
    }

    @Test
    void businessTeamTest03ErrorCase() {
//        [비즈니스팀 요구사항]
//        3. 고객은 "가상 지갑"에 돈을 충전할 수 있습니다.
//           뽑기한 수 만큼 "가상 지갑"에서 돈이 차감됩니다.
        String command =
                "INSERT\n" +
                        "INSERT ERROR\n" +
                        "INSERT !!\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(products);
        machine.run();
    }

    @Test
    void businessTeamTest04HappyCase() {
//        [비즈니스팀 요구사항]
//        4. 뽑기의 결과는 "상품" 또는 "꽝"이 나올 수 있습니다.
        String command =
                "DRAW 10\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(products);
        machine.run();
    }

    @Test
    void businessTeamTest05HappyCase() {
//        [비즈니스팀 요구사항]
//        5. 상품의 재고 상한선은 없습니다.

    }

    @Test
    void businessTeamTest06HappyCase() {
//        [비즈니스팀 요구사항]
//        6. 상품은 유통기한이 있습니다. 유통기한이 지난 상품은 고객에게 제공할 수 없습니다.
        String command =
                "DRAW 10\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(products);
        machine.run();
    }

    @Test
    void businessTeamTest07HappyCase() {
//        [비즈니스팀 요구사항]
//        7. 상품에는 등급이 있습니다. A,B 등급으로 나뉩니다.
//           뽑기 정책에 따라 A 또는 B 상품이 선택될 수 있습니다.

        String command =
                "Cider, A, 2024-02-10T02:28:56+09:00\n" +
                        "Sauces, B, 2024-02-10T02:28:56+09:00\n" +
                        "CHICKEN, C, 2023-03-23T02:20:19+09:00\n" +
                        "END";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        Admin admin = new Admin();
        admin.inputProductCodes();
    }

    @Test
    void businessTeamTest08HappyCase() {

    }

    @Test
    void businessTeamTest09HappyCase() {
//        [비즈니스팀 요구사항]
//        9. B 상품은 최대 3번까지만 뽑힙니다.

        LinkedList<Product> test08Products = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            test08Products.add(new Product("mock", "A", "2024-03-23T02:20:19+09:00"));
        }
        test08Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));
        test08Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));
        test08Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));
        test08Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));
        test08Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));

        String command =
                "INSERT 100000\n" +
                        "DRAW 500\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(test08Products);
        machine.run();
    }

    @Test
    void businessTeamTest10HappyCase() {
//        [비즈니스팀 요구사항]
//        10. A 상품의 확률을 먼저 확인하고 뽑히지 않는다면 B 상품의 뽑기를 시도 합니다.
//            그래도 뽑히지 못하다면 "꽝"을 반환합니다.

        LinkedList<Product> test10Products = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            test10Products.add(new Product("mock", "A", "2024-03-23T02:20:19+09:00"));
        }
        test10Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));
        test10Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));
        test10Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));
        test10Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));
        test10Products.add(new Product("Gold", "B", "2024-03-21T01:00:32+09:00"));

        String command =
                "INSERT 100000\n" +
                        "DRAW 500\n" +
                        "EXIT";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        PoachingMachine machine = new PoachingMachine(test10Products);
        machine.run();
    }

    @Test
    void businessTeamTest11HappyCase() {
//        [비즈니스팀 요구사항]
//        11. A, B 등급의 상품은 최소 2종류 이상 준비합니다.
        String command =
                "END\n" +
                        "Cider, A, 2024-02-10T02:28:56+09:00\n" +
                        "Sauces, B, 2024-02-10T02:28:56+09:00\n" +
                        "END";
        InputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);

        Admin admin = new Admin();
        admin.inputProductCodes();
    }
}