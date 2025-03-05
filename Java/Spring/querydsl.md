# QueryDsl

## 사전 지식

### JPQL

JPA 환경에서 많은 동적 값을 편하게 처리하기 위한 라이브러리 

# QueryDsl 이란

JPQL이 갖는 단점을 보완해주는 라이브러리 

JPQL의 단점

1. Query를 문자열로 작성한다.
    - 오타가 생기거나 잘못된 Query를 작성해도 컴파일 단계에서 알 수 없다.
2. 동적 Query를 작성할 때 조건에 맞게 조합하며 사용한다. 
    - Query가 복잡해지기 때문에 에러 발생을 유발한다.

QueryDsl은 Java로 작성할 수 있기 때문에 Compile 단계에서 에러를 확인할 수 있다.

복잡한 동적 Query는 QClass, Method를 활용하여 쉽게 제어할 수 있다.
