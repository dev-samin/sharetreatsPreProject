package project02;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class Admin {
    LinkedList<Product> products;

    public Admin() {
        products = new LinkedList<>();
    }
    public LinkedList<Product> getProducts() {
        return products;
    }

    public void inputProductCodes() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("END")) {
                System.out.println("읽기 종료");
                break;
            }

            String[] token = line.split(", ");

            // 형식 검사
            if (token.length != 3) {
                System.out.println("형식이 잘못 되었습니다");
                continue;
            }
            // 등급 검사
            if (token[1].equals("A") == false &&
                    token[1].equals("B") == false) {
                System.out.println("잘못된 등급이 입력되었습니다. A 또는 B 등금만 입력해주세요.");
                continue;
            }
            // 날짜 형식 검증
            if (isValidDateFormat(token[2]) == false) {
                System.out.println("잘못된 유통기한 포멧이 입력되었습니다 " + token[2]);
                continue;
            }
            Product product = new Product(token[0], token[1], token[2]);
            products.add(product);
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
}
