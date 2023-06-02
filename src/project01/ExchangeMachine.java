package project01;

import java.util.Scanner;
import java.util.Set;

public class ExchangeMachine {
    private Set<String> productCodes;

    ExchangeMachine(Set<String> productCodes) {
        this.productCodes = productCodes;
    }

    public void run() {
        greeting();
        command();
    }

    private void greeting() {
        System.out.println("안녕하세요. 쉐어트리츠 상품교환 서비스 입니다");
        help();
    }

    private void command() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("현재 남은 상품코드는 " + productCodes.size() + "개 입니다");
            String input = scanner.nextLine();

            if (input.length() > 30) {
                System.out.println("ERROR : 최대 30글자까지 입력 할수 있습니다.");
                continue;
            }

            if (input.matches("[a-zA-Z0-9\\s]+") == false) {
                System.out.println("ERROR : 문자와 숫자 공백만 입력해 주세요");
                continue;
            }

            String[] token = input.split(" ");
            if (token.length == 0) {
                System.out.println("ERROR : 입력된 문자가 없어요");
                continue;
            }

            String command = token[0].toUpperCase();
            switch (command) {
                case "CHECK" -> check(token);
                case "HELP" -> help();
                case "CLAIM" -> claim(token);
                case "EXIT" -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("ERROR : 존재하지 않는 명령어 입니다");
            }
        }
    }

    private boolean isAvailableCode(String productCode) {
        if (productCodes.contains(productCode) == true) {
            System.out.println(productCode + "은 사용가능한 상품코드입니다");
            return true;
        } else {
            System.out.println("ERROR : " + productCode + "는 존재하지 않는 상품코드입니다");
            return false;
        }
    }

    private void check(String[] token) {
        System.out.println("[------CHECK------]");

        for (int i = 1; i < token.length; i++) {
            String productCode = token[i];
            isAvailableCode(productCode);
        }
    }

    private void help() {
        System.out.println("[------HELP------]");
        System.out.println("쿠폰확인 : CHECK [상품코드]");
        System.out.println("도움말 : HELP [상품코드]");
        System.out.println("쿠폰사용 : CLAIM [상점코드] [상품코드]");
        System.out.println("종료 : EXIT");
        System.out.println();

    }

    private void claim(String[] token) {
        String storeCode = token[1];

        System.out.println("[------CLAIM------]");

        if (storeCode.matches("[a-zA-Z]+") && storeCode.length() == 6) {
            for (int i = 2; i < token.length; i++) {
                String productCode = token[i];
                if (isAvailableCode(productCode) == true) {
                    productCodes.remove(productCode);
                    System.out.println(storeCode + " 상점에서 " + productCode + "상품코드를 사용하였습니다.");
                }
            }
        } else {
            System.out.println("ERROR : 정확한 상점코드를 입력해주세요");
        }
    }

}

