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

public class Main {
    public static void main(String[] args) {
        /*
            테스트 케이스
            정상
            "HELP"
            "Help"
            "help"
            "      HELP"
            "       HELP        "

            "CHECK"
            "CHECK 000000000"
            "CHECK 000000000 111111111"

            "CLAIM aaaaaa 000000000"


            에러
            공백만 입력
            "    "

            존재하지 명령어 입력
            "hello"

            30글자 이상
            "                                       HELP"
            "CHECK 000000000 111111111 222222222"
            "CLAIM aaaaaa 111111111 222222222"

            숫자 a-Z 공백 이외의 문자 입력시
            "!!!!!!! !!!! !!!!!"

            상품 코드 숫자 아닐시
            "CHECK aaaaaaaaa"
            상품 코드 길이가 9이상 일때
            "CHECK 0123456789"

            상점코드로 0-9 a-Z 공백 이외 문자 입력
            "claim aa!! 111111111"

            사용한 상품코드 다시사용시
            "claim aaaaaa 111111111"
            "claim aaaaaa 000000000"
        */

        /*
            [고객의 입력]
            1. 문자열은 0~9, a~z, A~Z, SPACE 까지의 문자를 무작위로 입력할 수 있습니다.
            "!!!!!!! !!!! !!!!!"

            2. 문자열의 길이는 최대 30글자까지 입력할 것입니다.
            "                                       HELP"
            "CHECK 000000000 111111111 222222222"
            "CLAIM aaaaaa 111111111 222222222"
        */

        /*
            [ 비지니스 팀 요구사항 ]
            1. 고객이 상품 교환을 요구하면 가능한지 여부와 교환 결과를 안내해 주세요.
                 "CLAIM aaaaaa 000000000"
            2. 상품 코드는 10개가 준비되면 고객에게 10개까지만 제공됩니다.
                ???

            3. 상품 코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어져 있습니다.
                ???

            4. 상품 코드를 이용하여 상품 교환이 1번 이루어지면, 다시 해당 상품 코드로는 상품 교환을 할 수 없습니다.
                "claim aaaaaa 000000000
                claim aaaaaa 000000000"

            5. 고객은 상품 코드를 사용하기 전에 미리 상품을 교환할 수 있는지 확인이 가능합니다.
                CHECK 000000000
                claim aaaaaa 000000000

            6. SHARETREATS는 고객에게 CHECK, HELP, CLAIM 명령어를 사용할 수 있게 합니다.
                각 키워드는 CHECK(상품 교환여부 확인), HELP(사용법 안내), CLAIM(상품 교환) 을 의미합니다.
                HELP
                CHECK 000000000
                claim aaaaaa 000000000

            7. SHARETREATS 는 상품 코드 목록을 준비하고 고객은 상품 코드내에서만 상품 교환이 가능합니다.

            8. 상품 교환을 할 때는 어떤 상점에서 교환하였는지 상점 코드를 알아야 합니다.
                claim 000000000  <- 에러 발생
                claim aaaaaa 000000000
            9. 상점 코드는 A~Z,a~z 까지의 대,소 영문자만 사용이 가능하며 6문자로 이루어져 있습니다.
                claim !aaaaa 111111111
                claim aaaaa 111111111
        */


        /*
            [ 개발팀 요구사항 ]
            1. 상품 코드는 9개의 숫자 문자열로 구성된 총 20개를 개발자가 임의로 제공합니다.
               상품 코드는 문자열 Array 또는 파일이든 어떠한 형태로 제공이 되어도 관계 없습니다.

            2. 발생할 수 있는 상황들에 대한 테스트 케이스가 필요합니다.
               각 케이스 별 고객의 입력과 개발자가 예상하는 결과를 이용하여 모든 테스트를 통과해야 합니다.
        */

        ExchangeMachine exchangeMachine = new ExchangeMachine("src/project01/productCode.txt");
//        ExchangeMachine exchangeMachine = new ExchangeMachine("src/project01/ErrorProductCode.txt");
        exchangeMachine.run();
    }
}
