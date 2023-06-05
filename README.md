## project01 상품 교환 서비스  

### 테스트 케이스
**[고객의 입력]**

1. 문자열은 0~9, a~z, A~Z, SPACE 까지의 문자를 무작위로 입력할 수 있습니다.  
- 정상  
HELP  
CHECK 000000000  
CLAIM aaaaaa 000000000  
EXIT  

- 에러  
!!!!!!! !!!! !!!!!  *("ERROR : 문자와 숫자 공백만 입력해 주세요" 에러메세지 출력)*
EXIT  
    
2. 문자열의 길이는 최대 30글자까지 입력할 것입니다.  
- 에러  
HELP  
CHECK 000000000 111111111 222222222  *("ERROR : 최대 30글자까지 입력 할수 있습니다." 에러메세지 출력)*  
CLAIM aSTORE 111111111 222222222  *("ERROR : 최대 30글자까지 입력 할수 있습니다." 에러메세지 출력)*  
EXIT  

**[ 비지니스 팀 요구사항 ]**

1. 고객이 상품 교환을 요구하면 가능한지 여부와 교환 결과를 안내해 주세요.  
CLAIM aSTORE 111111111  
EXIT  

2. 상품 코드는 10개가 준비되면 고객에게 10개까지만 제공됩니다.  
상품코드 입력 (테스트를 시작하면 초기값으로 자동입력)   
000000000  
111111111  
222222222  
333333333  
444444444  
555555555  
666666666  
777777777  
888888888  
999999999  

3. 상품 코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어져 있습니다.  
- 에러  
aaaaaaaaa  *("ERROR : 상품 코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어져야 합니다." 에러메세지 출력)*  
1234567890  *("ERROR : 상품 코드는 0~9 자연수 글자로 이루어져 있으며 9문자로 이루어져야 합니다." 에러메세지 출력)*  
EXIT  

4. 상품 코드를 이용하여 상품 교환이 1번 이루어지면, 다시 해당 상품 코드로는 상품 교환을 할 수 없습니다.  
- 에러 (사용한 코드 재사용시 사용불가 메세지) 
claim aStore 222222222  
claim aStore 222222222  *("ERROR : 333333333은 존재하지 않는 상품코드입니다" 메세지 출력)*  
EXIT  

5. 고객은 상품 코드를 사용하기 전에 미리 상품을 교환할 수 있는지 확인이 가능합니다.  
CHECK 333333333  
claim aStore 333333333  
CHECK 333333333  *("ERROR : 333333333은 존재하지 않는 상품코드입니다" 메세지 출력)*  
EXIT  

6. SHARETREATS는 고객에게 CHECK, HELP, CLAIM 명령어를 사용할 수 있게 합니다.  
각 키워드는 CHECK(상품 교환여부 확인), HELP(사용법 안내), CLAIM(상품 교환) 을 의미합니다.  
HELP  
CHECK 333333333  
claim aStore 333333333  
EXIT  

7. SHARETREATS는 상품 코드 목록을 준비하고 고객은 상품 코드내에서만 상품 교환이 가능합니다.  
- 에러 (존재하지 않는 상품코드)  
check aStore 123123123  *("ERROR : 123123123 존재하지 않는 상품코드입니다" 메세지 출력)*  
claim aStore 123123123  *("ERROR : 123123123 존재하지 않는 상품코드입니다" 메세지 출력)*  
EXIT  

8. 상품 교환을 할 때는 어떤 상점에서 교환하였는지 상점 코드를 알아야 합니다.  
- 정상  
claim aSTORE 000000000  
EXIT  

- 에러  
claim 111111111  *("ERROR : 정확한 상점코드를 입력해주세요" 에러메세지 출력.)*  
claim 1STORE 111111111  *("ERROR : 정확한 상점코드를 입력해주세요" 에러메세지 출력.)*  
EXIT  

9. 상점 코드는 A~Z,a~z 까지의 대,소 영문자만 사용이 가능하며 6문자로 이루어져 있습니다.
- 정상  
claim aSTORE 111111111  
EXIT  

- 에러  
claim !STORE 111111111  *("ERROR : 정확한 상점코드를 입력해주세요" 에러메세지 출력.)*  
claim 1STORE 111111111  *("ERROR : 정확한 상점코드를 입력해주세요" 에러메세지 출력.)*
EXIT  

## project02 빠칭코 상품 뽑기 서비스  
### 테스트 케이스  

**[비즈니스팀 요구사항]**
1. 돈은 자연수로 정의되며 100 이라는 숫자는 여기서 100원을 의미합니다.  
- 정상  
 INSERT 100  
 EXIT  

- 에러  
INSERT money  ("ERROR : 2147483647 이하의 숫자만 입력해 주세요." 에러메세지 출력.)  
EXIT  

2. 상품 코드는 10개가 준비되면 고객에게 10개까지만 제공됩니다.  
즉, 200원을 고객이 사용하면, 뽑기 서비스는 총 2번의 뽑기 기회를 제공합니다.  
- 정상  
DRAW 2  

- 에러  
DRAW ERROR  *("ERROR : 2147483647 이하의 숫자만 입력해 주세요." 에러메세지 출력)*  
  
3. 고객은 "가상 지갑"에 돈을 충전할 수 있습니다.  
뽑기한 수 만큼 "가상 지갑"에서 돈이 차감됩니다.  
- 정상  
INSERT 10000  
DRAW 2  

- 에러  
INSERT 
INSERT ERROR  *("ERROR : 2147483647 이하의 숫자만 입력해 주세요." 에러메세지 출력.)*  
INSERT !!  *("ERROR : 2147483647 이하의 숫자만 입력해 주세요." 에러메세지 출력.)*  
  
4. 뽑기의 결과는 "상품" 또는 "꽝"이 나올 수 있습니다.   
DRAW 10 (랜덤 확률이므로 꽝또는 당첨이 시행에따라 다르게 출력.)  

5. 상품의 재고 상한선은 없습니다.  
- 자바의 LinkedList 를 활용하여 상품를 저장하기떄문에 사용자 컴퓨터의 LinkedList가 허용하는 범위까지 제한없이 상품재고를 넣을수 있습니다.  

6. 상품은 유통기한이 있습니다. 유통기한이 지난 상품은 고객에게 제공할 수 없습니다.  
DRAW 10 (10번 시행하는동안 유통기한이 지난 상품이 당첨될경우 환불을 해주는 방식으로 처리하였습니다.)  
("유통기한이 지난 상품입니다 " + 유통기한
" 100원을 환불해 드립니다." 메세지 출력)

7. 상품에는 등급이 있습니다. A,B 등급으로 나뉩니다.  
뽑기 정책에 따라 A 또는 B 상품이 선택될 수 있습니다.  
- Admind을 통해 입력 받을시 A, B 등급이 아닌 등급입력시 에러처리(입력불가 메세지 출력) 하였습니다.  
Cider, A, 2024-02-10T02:28:56+09:00  
Sauces, B, 2024-02-10T02:28:56+09:00  
CHICKEN, C, 2023-03-23T02:20:19+09:00  *("ERROR : 잘못된 등급이 입력되었습니다. A 또는 B 등금만 입력해주세요." 에러메세지 출력)*  
  
8. A 상품은 90%의 확률, B 상품은 10%의 확률로 뽑힙니다.  
INSERT 100000  
DRAW 500 (B상품 3번 당첨시 "B등급 상품은 최대 3번까지만 당첨 될수 있습니다." 메세지 출력)  

9. B 상품은 최대 3번까지만 뽑힙니다.
INSERT 100000
DRAW 500 (B상품 3번 당첨시 "B등급 상품은 최대 3번까지만 당첨 될수 있습니다." 메세지 출력)  

10. A 상품의 확률을 먼저 확인하고 뽑히지 않는다면 B 상품의 뽑기를 시도 합니다. 그래도 뽑히지 못하다면 "꽝"을 반환합니다.  
INSERT 100000  
DRAW 500 (B상품 3번 당첨시 "B등급 상품은 최대 3번까지만 당첨 될수 있습니다." 메세지 출력)  

11. A, B 등급의 상품은 최소 2종류 이상 준비합니다.
"END"  *(2종류이상 상품 입력 하지않을시 "ERROR : A, B 등급의 상품은 최소 2종류 이상 준비해야 합니다." 메세지 출력)*  
"Cider, A, 2024-02-10T02:28:56+09:00"  
"Sauces, B, 2024-02-10T02:28:56+09:00"  
"END"  


## project03 회사 조직(부서) 인원수 파악 서비스
### 테스트 케이스  

**[비즈니스팀 요구사항]**  

1. 회사내의 각 부서는 1개 이상입니다.  
- 정상  
AS, 10  
DEV, 20  
QA, 99  
EXIT  

- 에러  
EXIT  ("ERROR: 1개 이상의 부서를 입력해 주세요" 에러메세지 출력)
AS, 10  
AS, 10  ("ERROR: 이미 존재하는 부서입니다." 에러메세지 출력)  
QA, 99  
EXIT  

2. 각 부서는 포함 관계를 가집니다. 
즉  A, B, C 부서가 있다면 B,C 부서는 A 부서에 포함되며, 산하부서가 될 수 있습니다.  
- 정상
A, 10  
B, 20  
C, 99  
EXIT  
\* > A  
A > B  
A > C  
EXIT  

3. 각 부서는 몇 명의 직원이 있는지 인원수 정보를 가지고 있습니다.  

4. 최 상위 부서(다른 부서에 포함되지 않는 부서)별로 해당 부서와 하위부서 내의 모든 직원수를 합한 수를 제공해야 합니다.  
AS, 10  
DEV, 20  
QA, 99  
EXIT  
EXIT ("ERROR: 최상위 부서가 입력되지 않았습니다." 메세지 출력)
\* > AS  
AS > DEV  
DEV > QA  
EXIT  

**[고객의 입력]**  
1. 고객은 부서명과 인원수, 부서간의 관계를 제공합니다.  
- 정상
A, 10  
B, 20  
C, 99  
EXIT  
\* > A  
A > B  
A > C  
EXIT  

2. 부서명은 A-Z 까지 영문자 대문자로만 제공됩니다.  
- 에러
123, 123 ("ERROR: 형식에 맞지 않습니다." 메세지 출력)
,123  ("ERROR: 형식에 맞지 않습니다." 메세지 출력)  
A, 10  
EXIT  

3. 인원수는 최소 0명에서 1000명의 범위입니다.  
AS, 1001  ("ERROR: 0 ~ 1000명 이하의 인원만 입력해 주세요" 에러메세지 출력)
A, 10  
EXIT  

4. 부서간의 관계는 ">" 로 표시됩니다. 
예를 들어 A > B 는 A 가 상위 부서, B 는 A의 하위 부서입니다.  
즉, B 는 A 에 포함됩니다.  
AS, 10  
DEV, 20  
QA, 99  
EXIT  
AS < *  ("ERROR: [* > 부서명] 또는 [부서명 > 부서명] 형식에 맞지 않습니다." 에러메세지 출력)  
\* > AS  
EXIT  

5. 최상위 부서 표현은 * > A 로 표현합니다.  
즉, A 를 포함하는 상위 부서는 없습니다.  
AS, 10  
DEV, 20  
QA, 99  
EXIT  
\* > AS  
DEV > AS  
\* > DEV  ("최상위 부서는 1개만 입력할수 있습니다." 메세지 출력.)  
EXIT  

6. 1개의 하위 부서는 1개의 상위 부서만을 가질 수 있습니다.  
AS, 10  
DEV, 20  
QA, 99  
EXIT  
\* > AS  
AS > DEV  
QA > DEV  ("ERROR: 이미 상위부서가 존재합니다." 메세지 출력)
EXIT  
