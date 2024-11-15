# java-convenience-store-precourse


# 미션 진행 과정
## 의식해야 하는 목표와 방향 설정
### 미션 진행 마인드
- 시간과 일의 단위를 잘게 쪼개고 현재 목표에 집중하자
    - 잘개 쪼갠 것을 바탕으로 계속해서 피드백을 받고 동기부여를 받아 나아가자
- 추상화를 언제까지 할지 구체화를 언제부터 할지 생각하자
    - 설계를 언제까지 하고 구현을 시작할지 생각하자
    - 구현(구체적)을 하면서 추상적인 부분에서 보이지 않던 곳을 찾고 이를 통해 힌트를 얻어 더욱 좋은 설계로 나아가자

### 협업
- commit 메시지 명확하게 개선 및 협업의 관점에서 commit 작성하자
- 코드 가독성을 늘리자! 구글 스타일 가이드 적용!

### 프로덕션 코드
- 자바 API를 활용하여 생산성 향상 및 가독성 있게 개선하는 것이 목표
    - Stream API, 람다, 메서드 참조
    - Optional
    - Record
    - TextBlock, String Templates(사용불가)
- 클린 코드 지향 함으로써 더욱 좋은 가독성을 만들자
    - 변수명, 메서드명 명확히 하기
    - 메서드명도 추상화와 구체화에 대해 고민하자
    - 값 하드 코딩 하지 않기
    - 구현 순서 지키기(클래스는 상수, 멤버 변수, 생성자, 메서드 순으로 작성)
- 함수 분리 하여 가독성, 재사용성 증가시키자
    - 한 메서드가 한가지 기능만 담당하게 하기(한가지 기능을 하는 기준 세우기)
    - 함수 10라인 넘지 않게 하기 넘는다면 책임을 분리에대한 신호로 받아들이기
- 객체를 객체답게 사용하자
    - getter를 사용하지 말고 메시지를 보내자
    - 책임을 명확하게 분리시키자 ex) 비즈니스 로직과 UI로직 분리하기
- 필드가 많아진다면 객체가 많은 책임을 들고 있는 신호로 받아들여 책임을 분리해보자
  - 필드의 수가 많아지면 객체의 복잡도가 증가하고, 관리가 어려워지며, 버그가 발생할 가능성도 높아진다. 따라서 필드에 중복이 있거나 불필요한 필드가 없는지 확인하고, 이를 최소화한다
- 연관된 상수들이 있다면 Enum을 사용하자
  -  가독성과 타입안정성을 증가시키자
  -  Enum도 클래스다 객체지향적으로 사용하자
- 공통적인 상수가 있다면 묶어서 관리하자
- 패키지 레벨에 대해서 생각해봐라 
  - 관심사의 분리나 응집도에 대한 부분을 클래스 뿐만 아니라 패키지 레벨에서도 고민을 해 보시면 좋을 것 같아요!
- 외부에 전달하는 데이터는 불변하게 만들어 잘못 사용될 수 있는 경우를 없애자
- main에서 비즈니스 로직이 보이는걸 추상화하는걸 해보자
- final 키워드 사용하여 예기치 않게 변경되어 발생할 수 있는 문제 방지하기


### 테스트 코드 
- 테스트 코드도 코드다 좋은 테스트코드를 작성하자(유지보수)
    - 행동 상황 결과로 표현하여 명확하고 구체적이게 작성하자 단 비즈니스 언어를 사용!=> 문서화
    - 반복적으로 수행하는 부분이 있다면 중복을 제거하여 유지보수성을 높이고 가독성을 향상시켜야 한다.
    - 테스트를 위한 코드를 만들지말자 구현코드가 테스트에 종속된다.
- 예외사항 고민하기 정상적인 값이 안들어오는 경우도 잘 체크하기 성공하는 케이스 뿐만 아니라 예외 케이스도 테스트한다
- 테스트하기 어렵다면 책임 분리 또는 외부에서 주입해야하는 신호로 받아 들이자
    - 테스트하기 어려운 의존성을 외부에서 주입하거나 분리하여 테스트 가능한 상태로 만들자
- private 함수를 테스트 하고 싶다면 클래스(객체) 분리를 고려하자
- 올바르지 않은 값이 들어오는 경우도 생각해봐야한다.
    - 7처럼 올바르지 않은 matchCount 가 들어와도 예외가 발생하는 것이 아닌 NONE 이 반환되겠네요! NONE 에서도 BiPredicate 를 잘 활용해 보면, orElse(NONE) 대신 orElseThrow로 처리할 수 있을 것 같은데 고민해 보시면 좋을 것 같습니다!!
- assertThat을 여러개 쓰지말고 AssertAll을 써라
  - usingRecursiveComparison, doesNotContain, AssertAll. isNotEmpty() 사용해보기
- 테스트시 경계값 말고 음수와 0에대해서도 테스트하라



## 게임 진행 흐름
1. 보유하고 있는 상품을 알려준다.(출력)
2. 구매할 상품과 수량을 물어본다(입력,출력)
   1. 만약 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우 해당 상품을 더 받을지에 대해 물어본다(입력,출력)
   2. 만약 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 물어본다.(입력,출력)
3. 멤버십 할인을 받을지 물어본다(입력)
4. 영수증을 보여준다(출력)
5. 추가 구매를 진행할지 또는 종료할지를 물어본다

## 핵심 규칙 정리
1. 프로모션 할인 정리
   1. 동일한 상품에 여러 프로모션이 적용되지 않는다.
   2. 날짜가 기간 내에 포함된 경우에 할인을 적용한다.
   3. 같은 이름의 프로모션 상품과 일반상품이 존재한다면 프로모션 상품 먼저 적용하고, 프로모션 재고가 부족할 경우 일반 상품재고를 사용한다.
      1. 단 프로모션 재고가 부족한 경우 프로모션이 적용되지 않은 것을 결제할지 말지를 사용자가 선택할 수 있다.
         1. Y 선택시 일부 수량에 대해 정가로 결제
         2. N 선택시 일부 수량을 제외 하고 결제
   4. 프로모션 상품 혜택 기준에 맞게 상품을 가져 온 경우 프로모션을 적용 받을지 말지를 선택할 수 있다 
      1. ex) 2개를 가져오면 1개를 더주는 경우 1개를 받아 프로모션을 적용 받을지 안받을지 선택
      2. Y 선택시 상품 추가 + 프로모션 적용
      3. N 선택시 상품 추가 X 
2. 멤버십 할인 정리
   1. 프로모션을 적용한 상품을 제외하고 남은 금액의 30%를 할인 받을 수 있다
3. 영수증 정리
   1. 구매 상품, 증정상품, 금액정보를 출력 해야한다.
   2. 금액정보
      1. 총 구매액 => 수량, 전체 금액
      2. 행사 할인 => 프로모션 할인 금액
      3. 멤버십 할인 => 멤버십 할인 금액
      4. 내실돈 => 총구매액 - 할인 금액
   3. 구매 상품 내역 
      1. 구매한 상품명, 수량, 가격(상품금액 * 수량)
   4. 증정 상품 내역
      1. 프로 모션 제공된 상품의 목록
4. 상품 구매하면 다음과 같은 3가지 상태를 가진다
   1. 정상적으로 구매 된 상품
   2. 증정으로 상품을 추가로 받을 수 있는 상품
   3. 증정품이 부족한 상품
## 요구사항 정리
### 핵심 요구 사항
- [x] 상품 정보 구하는 기능
- [x] 상품 구매 기능
- [x] 증정품이 부족한 상품이 있는지 구하는 기능
- [x] 프로모션으로 증정 상품을 받을 수 있는 상품이 있는지 구하는 기능  
- [x] 멤버십 할인 적용 기능
- [x] 계속해서 구매를 진행할지 여부 판단하는 기능

### 입력 요구 사항
- [x] 구매 상품명, 수량 입력
- [x] 증정 받을 수 있는 상품을 추가 여부 입력
- [x] 일부 수량에 대해 정가로 결제할지 여부를 입력
- [x] 멤버십 할인 적용 여부를 입력
- [x] 추가 구매 여부를 입력


### 출력 요구 사항
- [x] 보유 하고 있는 상품 출력(상품 내역)
  - 상품명, 금액, 개수, 프로모션
- [x] 영수증 출력
  - 구매한 상품 => 상품명, 수량, 가격(상품금액 * 수량
  - 증정 상품 => 상품명, 수량
  - 금액 정보 => 이름, 수량(생략 가능), 금액

### 에러 처리 요구 사항
- [x] 숫자를 입력하지 않은 경우
- [x] 조건에 대한 여부를 올바르지 않게 입력 하지 않은 경우
  - 증정 상품 추가 여부, 멤버십 할인 여부, 일부 상품 정가 결제 여부. 구매하고 싶은 다른 상품 여부
- [x] 구매할 상품과 수량 형식이 올바르지 않은 경우
- [x] 존재하지 않는 상품을 입력한 경우
- [x] 구매 수량이 재고 수량을 초과한 경우
- [x] 구매 수량을 음수로 입력한 경우

```agsl
예외 상황 시 에러 문구를 출력해야 한다. 단, 에러 문구는 "[ERROR]"로 시작해야 한다.
```

## 설계
### ConvenienceStore(Product) => 상품 구매에 대한 기능은 누가 제공할 것인가?
편의점에 대한 책임

### Product(name,price,quantity,Promotion(객체)) => 상품 정보는 누가 제공해줄 것인가?
상품에 대한 책임

### Promotion(Enum)
name,buy,get,start_date,end_date
프로모션에 대한 책임

### Order
주문에 대한 책임

### OrderProduct
주문 상품에 대한 책임
1. 정상적으로 구매 된 상품
2. 증정으로 상품을 추가로 받을 수 있는 상품
3. 증정품이 부족한 상품


### Command(Enum)
- [x] Command 찾는 기능
- [x] Y인지 N인지 판단하는 기능
