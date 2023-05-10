package project02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PoachingMachine {
    Customer customer;
    LinkedList<Product> products;
    LinkedList<Product> productTypeA;
    LinkedList<Product> productTypeB;

    public PoachingMachine(LinkedList<Product> products) {
        this.customer = new Customer();
        this.products = products;
        this.productTypeA = new LinkedList<>();
        this.productTypeB = new LinkedList<>();
    }

    public void run() {
        sortProductType();
        help();
        command();
    }

    private void command() {
            Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("현재 전자 지갑에 남은 잔액은 " + customer.getWallet() + "원 입니다");
            String[] token = scanner.nextLine().trim().split(" ");
            String command = token[0];
            switch (command) {
                case "DRAW" -> {
                    if (token.length != 2) {
                        System.out.println("시행 횟수를 입력해 주세요");
                        continue;
                    }
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    try {
                        int count = Integer.parseInt(token[1]);
                        draw(count, currentDateTime);
                    } catch (NumberFormatException e) {
                        System.out.println("2147483647 이하의 숫자만 입력해 주세요.");
                    }
                }
                case "INSERT" -> {
                    if (token.length != 2) {
                        System.out.println("충전 금액을 입력해 주세요");
                    }
                    try {
                        int money = Integer.parseInt(token[1]);
                        chargeWallet(money);
                    } catch (NumberFormatException e) {
                        System.out.println("2147483647 이하의 숫자만 입력해 주세요.");
                    }
                }
                case "EXIT" -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("존재하지 않는 명령어 입니다.");
            }
        }
    }

    private void sortProductType() {
        for (Product product : products) {
            if (product.getGrade().equals("A")) {
                System.out.println("A");
                productTypeA.add(product);
            } else if (product.getGrade().equals("B")) {
                System.out.println("A");
                productTypeB.add(product);
            }
        }
    }


    private void draw(int count, LocalDateTime currentDateTime) {
        Product product;

        for (int i = 0; i < count; i++) {
            if (productTypeA.size() == 0 && productTypeB.size() == 0) {
                System.out.println("재고가 부족합니다");
                return;
            }

            if (customer.insertCoin() == false) {
                System.out.println("잔액이 부족합니다.");
                return;
            }

            if (pickGoods(90)) {
                if (productTypeA.size() == 0) {
                    System.out.println("재고가 부족합니다.");
                    continue;
                }
                product = productTypeA.pop();
                if (isExpired(product.getExpirationDate(), currentDateTime) == false) {
                    System.out.println("유통기한이 지난 상품입니다 " + product.getExpirationDate());
                    continue;
                }
                System.out.println(product.type + " 당첨 되었습니다,.");
                continue;
            }

            if (pickGoods(10)) {
                if (productTypeB.size() == 0) {
                    System.out.println("재고가 부족합니다.");
                    continue;
                }
                product = productTypeB.pop();
                if (isExpired(product.getExpirationDate(), currentDateTime) == false) {
                    System.out.println("유통기한이 지난 상품입니다 " + product.getExpirationDate());
                    continue;
                }
                System.out.println(product.type + " 당첨 되었습니다,.");
            } else {
                System.out.println("꽝 입니다");
            }
        }
    }

    private boolean isExpired(String date,LocalDateTime currentDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime compareDateTime = LocalDateTime.parse(date, formatter);

        if (currentDateTime.isAfter(compareDateTime)) {
            System.out.println("비교할 날짜가 더 과거입니다.");
            return (false);
        } else {
            return (true);
        }
    }

    private boolean pickGoods(double probability) {
        double num = new Random().nextDouble();
        return (num < (probability / 100));
    }

    private void chargeWallet(int money) {
        int currentWallet = customer.getWallet();
        customer.setWallet(currentWallet + money);
    }

    private void help() {
        System.out.println("[------HELP------]");
        System.out.println("응모 : DRAW [시행횟수]");
        System.out.println("충전 : INSERT");
        System.out.println("종료 : EXIT");
    }
}
