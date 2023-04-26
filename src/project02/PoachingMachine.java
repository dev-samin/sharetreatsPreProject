package project02;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PoachingMachine {
    Customer customer;
    LinkedList<Product> productTypeA;
    LinkedList<Product> productTypeB;
    String productCodePath;

    public PoachingMachine(String productCodePath) {
        this.customer = new Customer();
        this.productTypeA = new LinkedList<>();
        this.productTypeB = new LinkedList<>();
        this.productCodePath = productCodePath;
    }

    public void run() {
        readProductCodesFromFile();
        help();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String[] token = scanner.nextLine().trim().split(" ");
            String command = token[0];
            switch (command) {
                case "DRAW" -> {
                    if (token.length == 2) {
                        LocalDateTime currentDateTime = LocalDateTime.now();
                        try {
                            int count = Integer.parseInt(token[1]);
                            draw(count, currentDateTime);
                        } catch (NumberFormatException e) {
                            System.out.println("2147483647 이하의 숫자만 입력해 주세요.");
                        }
                    } else {
                        System.out.println("시행 횟수를 입력해 주세요");
                    }
                }
                case "INSERT" -> {
                    if (token.length == 2) {
                        try {
                            int money = Integer.parseInt(token[1]);
                            chargeWallet(money);
                        } catch (NumberFormatException e) {
                            System.out.println("2147483647 이하의 숫자만 입력해 주세요.");
                        }
                    } else {
                        System.out.println("충전 금액을 입력해 주세요");
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

    private void readProductCodesFromFile() {
        File file = new File(productCodePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] token = line.split(", ");

                // 등급 검사
                if (!(token[1].equals("A") || token[1].equals("B"))) {
                    throw new IllegalArgumentException("잘못된 등급이 입력되었습니다. A 또는 B 등금만 입력해주세요.");
                }
                // 날짜 형식 검증
                if (isValidDateFormat(token[2]) == false) {
                    throw new IllegalArgumentException("잘못된 유통기한 포멧이 입력되었습니다 " + token[2]);
                }
                // 유통기한 검증
                if (isExpired(token[2]) == false) {
                    throw new IllegalArgumentException("유통기한이 지난 상품입니다 " + token[2]);
                }

                Product product = new Product(token[0], token[1], token[2]);

                if (product.getGrade().equals("A")) {
                    productTypeA.add(product);
                } else if (product.getGrade().equals("B")) {
                    productTypeB.add(product);
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidDateFormat(String input) {
        System.out.println(input);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        try {
            formatter.withZone(ZoneId.of("Asia/Seoul")).parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isExpired(String date) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("현재 날짜 및 시간: " + currentDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime compareDateTime = LocalDateTime.parse(date, formatter);
        System.out.println("비교할 날짜 및 시간: " + compareDateTime);

        if (currentDateTime.isAfter(compareDateTime)) {
            System.out.println("비교할 날짜가 더 과거입니다.");
            return (false);
        } else if (currentDateTime.isBefore(compareDateTime)) {
            System.out.println("비교할 날짜가 더 미래입니다.");
            return (true);
        } else {
            System.out.println("현재 날짜와 비교할 날짜가 동일합니다.");
            return (true);
        }
    }

    private void draw(int count, LocalDateTime currentDateTime) {
        Product goods;
        for (int i = 0; i < count; i++) {
            if (productTypeA.size() == 0 || productTypeB.size() == 0) {
                System.out.println("재고가 부족합니다");
                return;
            }

            if (customer.insertCoin() == true) {
                if (pickGoods(90)) {
                    goods = productTypeA.pop();
                    System.out.println(goods.type + " 당첨 되었습니다,.");
                } else {
                    if (pickGoods(10)) {
                        goods = productTypeB.pop();
                        System.out.println(goods.type + " 당첨 되었습니다,.");
                    } else {
                        System.out.println("꽝 입니다");
                    }
                }
            } else {
                System.out.println("잔액이 부족합니다.");
            }
        }
        System.out.println("현재 전자 지갑에 남은 잔액은 " + customer.getWallet() + "원 입니다");
    }
    private boolean pickGoods(double probability) {
        double num = new Random().nextDouble();
        return (num < (probability / 100));
    }

    private void chargeWallet(int money) {
        int currentWallet = customer.getWallet();
        customer.setWallet(currentWallet + money);
        System.out.println("현재 전자 지갑에 남은 잔액은 " + customer.getWallet() + "원 입니다");
    }


    private void help() {
        System.out.println("[------HELP------]");
        System.out.println("응모 : DRAW [시행횟수]");
        System.out.println("충전 : INSERT");
        System.out.println("종료 : EXIT");
    }

}
