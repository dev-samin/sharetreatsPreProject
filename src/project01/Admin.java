package project01;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Admin {
    Set<String> productCodes;

    public Admin() {
        productCodes = new HashSet<>();
    }



    public void inputProductCodes() {
        Scanner scanner = new Scanner(System.in);
        String line;

        System.out.println("관리자용 상품코드 입력 프로그램입니다");
        System.out.println("9개의 숫자로 이루어진 상품코드를 입력해 주세요");
        System.out.println("모든 입력이 완료되면 END를 입력해 주세요");

        while (true) {
            line = scanner.nextLine();
            if (line.equals("END")) {
                System.out.println("입력종료");
                break;
            }
            //                [ 비지니스 팀 요구사항 ] 3. 상품 코드는 9개의 숫자로 이루어져야 합니다.
            if (line.matches("[0-9]+") == false || line.length() != 9) {
                System.out.println("상품 코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어져야 합니다.");
                continue;
            }
            productCodes.add(line);
        }
    }

    public Set<String> getProductCodes() {

        return productCodes;
    }
}
