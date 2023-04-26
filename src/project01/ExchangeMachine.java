package project01;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExchangeMachine {
    Set<String> productCodes;
    String productCodePath;

    ExchangeMachine(String productCodePath) {
        this.productCodes = new HashSet<>();
        this.productCodePath = productCodePath;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        readProductCodesFromFile();
        greeting();
        while (true) {
            System.out.println("현재 남은 상품코드는 " + productCodes.size() + "개 입니다");
            String input = scanner.nextLine();
//          [ 고객의 입력 ] 2. 문자열의 길이는 최대 30글자까지 입력할 것입니다.

                System.out.println(input);
                System.out.println(input.length());
            if (input.length() > 30) {
                System.out.println("최대 30글자까지 입력 할수 있습니다.");
                continue;
            }

//          [ 고객의 입력 ] 1. 문자열은 0~9, a~z, A~Z, SPACE 까지의 문자를 무작위로 입력할 수 있습니다.
            if (input.matches("[a-zA-Z0-9\\s]+") == false) {
                System.out.println("문자와 숫자 공백만 입력해 주세요");
                continue;
            }

            input.trim();
            String[] token = input.split(" ");
            if (token.length == 0) {
                System.out.println("입력된 문자가 없어요");
                continue;
            }

            String command = token[0].toUpperCase();

//          [ 비지니스 팀 요구사항 ] 6. SHARETREATS는 고객에게 CHECK, HELP, CLAIM 명령어를 사용할 수 있게 합니다.
//                각 키워드는 CHECK(상품 교환여부 확인), HELP(사용법 안내), CLAIM(상품 교환) 을 의미합니다.
            switch (command) {
                case "CHECK" -> check(token);
                case "HELP" -> help();
                case "CLAIM" -> claim(token);
                case "EXIT" -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("존재하지 않는 명령어 입니다");
            }
        }
    }

    private void greeting() {
        System.out.println("안녕하세요 쉐어트리츠 상품교환 서비스 입니다");
        help();
    }
//    [ 비지니스 팀 요구사항 ] 2. 상품 코드는 10개가 준비되면 고객에게 10개까지만 제공됩니다.
//    [ 개발팀 요구사항 ] 2. 상품 코드는 9개의 숫자 문자열로 구성된 총 20개를 개발자가 임의로 제공합니다.
//                        상품 코드는 문자열 Array 또는 파일이든 어떠한 형태로 제공이 되어도 관계 없습니다.
    private void readProductCodesFromFile() {
        File file = new File(productCodePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
//                [ 비지니스 팀 요구사항 ] 3. 상품 코드는 9개의 숫자로 이루어져야 합니다.
                if (line.matches("\\d+") == false || line.length() != 9){
                    throw new IllegalArgumentException("상품 코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어져야 합니다.");
                }
                productCodes.add(line);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    [ 비지니스 팀 요구사항 ] 1. 고객이 상품 교환을 요구하면 가능한지 여부와 교환 결과를 안내해 주세요.
//    [ 비지니스 팀 요구사항 ] 7. SHARETREATS 는 상품 코드 목록을 준비하고 고객은 상품 코드내에서만 상품 교환이 가능합니다.
    private boolean isAvailableCode(String productCode) {
        if (productCodes.contains(productCode) == true) {
            System.out.println(productCode + "은 사용가능한 상품코드입니다");
            return true;
        } else {
            System.out.println(productCode + "는 존재하지 않는 상품코드입니다");
            return false;
        }
    }


//    5. 고객은 상품 코드를 사용하기 전에 미리 상품을 교환할 수 있는지 확인이 가능합니다.
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
    }

    private void claim(String[] token) {
//        [ 비지니스 팀 요구사항 ] 8. 상품 교환을 할 때는 어떤 상점에서 교환하였는지 상점 코드를 알아야 합니다.
        String storeCode = token[1];

        System.out.println("[------CLAIM------]");
//        [ 비지니스 팀 요구사항 ] 9. 상점 코드는 A~Z,a~z 까지의 대,소 영문자만 사용이 가능하며 6문자로 이루어져 있습니다.
        if (storeCode.matches("[a-zA-Z]+") && storeCode.length() == 6) {
            for (int i = 2; i < token.length; i++) {
                String productCode = token[i];

                if (isAvailableCode(productCode) == true) {
//                    [ 비지니스 팀 요구사항 ] 4. 상품 코드를 이용하여 상품 교환이 1번 이루어지면, 다시 해당 상품 코드로는 상품 교환을 할 수 없습니다.

                    productCodes.remove(productCode);
                    System.out.println(storeCode + " 상점에서 " + productCode + "상품코드를 사용하였습니다.");
                }
            }
        } else {
            System.out.println("정확한 상점코드를 입력해주세요");
        }
    }
}
