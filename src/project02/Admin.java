package project02;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class Admin {
    private LinkedList<Product> products;

    public Admin() {
        products = new LinkedList<>();
    }

    public void inputProductCodes() {
        Scanner scanner = new Scanner(System.in);

        greeting();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("END")) {
                if (products.size() >= 2) {
                    break;
                }
                System.out.println("A, B 등급의 상품은 최소 2종류 이상 준비해야 합니다.");
                continue;
            }

            String[] token = line.split(", ");

            if (token.length != 3) {
                System.out.println("!!형식이 잘못 되었습니다!!");
                help();
                continue;
            }

            if (token[1].equals("A") == false &&
                    token[1].equals("B") == false) {
                System.out.println("잘못된 등급이 입력되었습니다. A 또는 B 등금만 입력해주세요.");
                help();
                continue;
            }

            if (isValidDateFormat(token[2]) == false) {
                System.out.println("유통기한 형식이 잘못되었습니다 입력되었습니다 " + token[2]);
                help();
                continue;
            }
            Product product = new Product(token[0], token[1], token[2]);
            products.add(product);
        }
    }
    private void greeting() {
        System.out.println("[빠칭코 상풍등록 시스템]");
        System.out.println();
        help();
        System.out.println("상품정보를 입력해주세요");
    }

    private void help() {
        System.out.println("[상품정보 형식 도움말]");
        System.out.println("상풍명, 등급(A 또는 B), 유통기한");
        System.out.println("CHICKEN, A, 2024-03-23T02:20:19+09:00");
        System.out.println();
    }

    private boolean isValidDateFormat(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

        try {
            formatter.withZone(ZoneId.of("Asia/Seoul")).parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

}
