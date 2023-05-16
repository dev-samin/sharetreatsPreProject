package project01;

/*
jdk-18.0.1.1
< 상품 교환 서비스>

 */

//- 고객에게 원하는 입력 형식
//CHECK [상품코드]
//HELP
//CLAIM [상점코드] [상품코드]
//
//- 고객의 입력 예시
//Check 132 421 122
//HELP
//claIm ABcde 1231 2312 123

//상품 코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어져 있습니다.
//상점 코드는 A~Z,a~z 까지의 대,소 영문자만 사용이 가능하며 6문자로 이루어져 있습니다.

//2. 발생할 수 있는 상황들에 대한 테스트 케이스가 필요합니다.
//        각 케이스 별 고객의 입력과 개발자가 예상하는 결과를 이용하여 모든 테스트를 통과해야 합니다.

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin();
        admin.inputProductCodes();
        Set<String> productCodes = admin.getProductCodes();

        ExchangeMachine exchangeMachine = new ExchangeMachine(productCodes);
        exchangeMachine.run();
    }
}
