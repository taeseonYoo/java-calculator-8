## java-calculator-precourse

### 문자열 덧셈 계산기

---

## 기능 요구사항

--- 

### 1. 입출력 처리

- 실행 결과 예시
  ~~~ 
    덧셈할 문자열을 입력해 주세요.
    1,2:3
    결과 : 6
  ~~~
- [x] 구분자와 양수로 구성된 문자열을 입력받는다.
- [x] 덧셈 결과를 출력한다.

### 2. 비즈니스 로직 구현

- [x] 쉼표 또는 콜론 구분자를 기준으로 문자열을 분리한다.
    - 예: "" => 0, "1,2" => 3, "1,2,3" => 6, "1,2:3" => 6
- [x] 분리한 숫자를 합한다.
- [x] 커스텀 구분자를 사용할 수 있다.
    - 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
    - 커스텀 구분자는 한 자리로 제한한다.
    - 예를 들어 "//;\n1;2;3"과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.

### 3. 예외 처리

사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시킨 후 종료되어야 한다.

- [x] 양수가 아닌 숫자가 주어지는 경우 (ex. 0 또는 음수가 주어진다.)
- [x] 커스텀 구분자가 한 자리가 아닌 경우 ("//;;\n1;;2")
- [x] 구분자로 시작하는 문자열이 주어지는 경우 ("//;\n;1", ";1;2")
- [x] 피연산자가 없는 경우 (ex. ",:")

## 프로그래밍 요구사항

---

- JDK 21 버전에서 실행 가능해야 한다.
- 프로그램 실행의 시작점은 Application의 main()이다.
- build.gradle 파일은 변경할 수 없으며, 제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.
- 프로그램 종료 시 System.exit()를 호출하지 않는다.
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 기본적으로 Java Style Guide를 원칙으로 한다.

### 라이브러리

- camp.nextstep.edu.missionutils에서 제공하는 Console API를 사용하여 구현해야 한다.
    - 사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.

### Commit Convention

예시

~~~
feat(Input): 사용자의 입력 구현
~~~

- feat (feature)
- fix (bug fix)
- docs (documentation)
- style (formatting, missing semi colons, …)
- refactor
- test (when adding missing tests)
- chore (maintain)

