package project02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PoachingMachine {
    LinkedList<Product> products;
    LinkedList<Product> productTypeA;
    LinkedList<Product> productTypeB;
    Customer customer;

    public PoachingMachine(LinkedList<Product> products) {
        this.products = products;
        this.productTypeA = new LinkedList<>();
        this.productTypeB = new LinkedList<>();
        this.customer = new Customer();
    }

    public void run() {
        greeting();
        sortProductType();
        command();
    }

    private void greeting() {
        System.out.println("[빠칭코 상품 뽑기 서비스]");
        System.out.println("명령어를 입력해주세요");
        System.out.println();
    }

    private void help() {
        System.out.println("[------HELP------]");
        System.out.println("응모 : DRAW [시행횟수]");
        System.out.println("충전 : INSERT");
        System.out.println("종료 : EXIT");
    }

    private void sortProductType() {
        for (Product product : products) {
            if (product.getGrade().equals("A")) {
                productTypeA.add(product);
            }
            if (product.getGrade().equals("B")) {
                productTypeB.add(product);
            }
        }
    }

    private void command() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("현재 남은 잔액은 " + customer.getWallet() + "원 입니다");
            help();
            LocalDateTime currentDateTime = LocalDateTime.now();
            String[] token = scanner.nextLine().trim().toUpperCase(Locale.ROOT).split(" ");
            String command = token[0];
            switch (command) {
                case "DRAW" -> {
                    if (token.length != 2) {
                        System.out.println("ERROR : 시행 횟수를 입력해 주세요");
                        continue;
                    }
                    try {
                        int count = Integer.parseInt(token[1]);
                        draw(count, currentDateTime);
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR : 2147483647 이하의 숫자만 입력해 주세요.");
                    }
                }
                case "INSERT" -> {
                    if (token.length != 2) {
                        System.out.println("ERROR : 충전 금액을 입력해 주세요");
                        continue;
                    }
                    try {
                        int money = Integer.parseInt(token[1]);
                        chargeWallet(money);
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR : 2147483647 이하의 숫자만 입력해 주세요.");
                    }
                }
                case "EXIT" -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("ERROR : 존재하지 않는 명령어 입니다.");
            }
        }
    }


    private void draw(int count, LocalDateTime currentDateTime) {
        for (int i = 0; i < count; i++) {
            if (customer.insertCoin() == false) {
                System.out.println("ERROR : 잔액이 부족합니다.");
                return;
            }

            if (productTypeA.size() == 0 && productTypeB.size() == 0) {
                System.out.println("ERROR : 재고가 부족합니다");
                refund();
                return;
            }

            if (pickGoods(productTypeA, 90, currentDateTime) == true) {
                continue;
            }

            if (customer.getProductTypeB().size() >= 3) {
                System.out.println("B등급 상품은 최대 3번까지만 당첨 될수 있습니다.");
                refund();
                continue;
            }

            if (pickGoods(productTypeB, 10, currentDateTime)) {
                continue;
            }
            System.out.println("꽝 입니다");
        }
    }


    private boolean pickGoods(LinkedList<Product> products, double probability, LocalDateTime currentDateTime) {
        double num = new Random().nextDouble();

        if (num < (probability / 100) == false) {
            return false;
        }

        if (products.size() == 0) {
            System.out.println("재고가 부족합니다. 100원을 환불해 드립니다.");
            refund();
            return true;
        }

        Product product = products.pop();
        if (isExpired(product.getExpirationDate(), currentDateTime) == true) {
            System.out.println("유통기한이 지난 상품입니다 " + product.getExpirationDate());
            refund();
            return true;
        }

        if (product.getGrade().equals("A"))
            customer.getProductTypeA().add(product);
        if (product.getGrade().equals("B"))
            customer.getProductTypeB().add(product);
        System.out.println(product.getProductName() + " 당첨 되었습니다.");
        return true;
    }

    private boolean isExpired(String date, LocalDateTime currentDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime compareDateTime = LocalDateTime.parse(date, formatter);

        if (currentDateTime.isAfter(compareDateTime) == false) {
            return (false);
        }
        return (true);
    }

    private void chargeWallet(int money) {
        int currentWallet = customer.getWallet();
        customer.setWallet(currentWallet + money);
        System.out.println(money + " 원이 충전 되었습니다.");
    }

    private void refund() {
        System.out.println(" 100원을 환불해 드립니다.");
        chargeWallet(100);
    }
}
