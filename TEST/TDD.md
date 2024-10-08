# TDD

테스트 주도 개발 방법론 

## 목적

- 신뢰성을 높일 수 있다.
- 빠른 생산성을 가질 수 있다.

## 단계

1. 테스트 코드를 먼저 작성함으로써, 테스트 코드가 개발을 주도한다. (테스트 작성)
2. **반드시 실패를 포함하는 테스트 코드의 작성이 앞서야한다.**
3. 앞서 장성된 테스트 코드를 통과할 수 있는 **최소한의 구현 코드**를 작성한다. (최소한의 구현 코드 작성)
4. 최소한으로 구현한 코드는 많은 개선점을 가진다. 단지 테스트만 통과하면 된다. 
5. 최소한으로 구현한 코드를 개선한다 (리팩토링 단계)
6. 위 항목의 반복

**테스트 작성 → 최소한의 구현 코드 작성 → 리팩토링 → 테스트 작성** 

## 효과

### 1) 목표

- 지금 구현해야 할 **목표**를 뚜렷하게 보여준다.
- 잘못된 방향으로 가고있다면, **바른 길로 가도록** 안내해준다.

### 2) 리듬

- 반복되는 짧은 패턴을 통해 리듬이 생기고, 집중력을 높일 수 있다.

### 3) 소통

- 작성된 테스트 코드는 설명서 or API 문서로서 **의사소통의 도구**로 활용할 수 있다.

### 4) 개선

- 개발중인 코드의 문제점을 빠르게 잡아낼 수 있다.
- 테스트 코드를 작성하기 어렵다는건, **잘못된 설계의 조짐**이 될 수 있다.

### 5) 성취

- 반복되는 개발 패턴에서 테스트를 통과하는 구현 코드 작성을 통해, 성취감을 느끼고, 생산성으로 이어질 수 있다.

## Summary.

### TDD는?

1. 테스트 코드를 먼저 작성하여, 명확한 가이드 라인을 갖을 수 있다. 
2. 빠른 피드백이 가능하여, 개발 중 원점으로 돌아가는 것을 방지할 수 있다.
3. 즉각적인 테스트를 통해 개발 진행에 확신을 갖을 수 있다 .
