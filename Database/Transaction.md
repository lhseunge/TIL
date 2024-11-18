# Transaction

DB의 상태를 변경시키기 위해 수행하는 **작업 단위**이다. 

## Transaction의 특징

### 원자성 (Atomicity)

- Transaction의 연산은 DB에 모두 반영되거나, 혹은 모두 반영되지 않아야 한다.
- Transaction안의 모든 명령은 반드시 완벽하게 수행되어야한다.
- 하나의 오류라도 발생한다면, Transaction 전부 취소되어야 한다.

### 일관성 (Consistency)

- Transaction이 성공적으로 완료되면, 일관성 있는 DB 상태로 변환한다.
- System이 가지고 있는 고정요소는 Transaction 수행 전과 후의 상태가 같아야 한다.

### 독립성 (Isolation)

- 둘 이상의 Transaction이 동시에 실행되는 경우, 다른 Transaction이 연산에 끼어들 수 없다.
- 수행중인 Transaction은 완전히 완료될 때 까지 다른 Transaction에서 수행 결과를 참조할 수 없다.

### 지속성 (Durability)

- 성공적으로 완료된 Transaction의 결과는 영구적으로 반영되어야 한다.

## Transaction의 연산 - Commit & Rollback

- Commit - 하나의 Transaction이 성공적으로 끝났으며, DB가 일관성있는 상태로 유지될 때, Transaction이 마무리되었다는 것을 Transaction Manager에게 알리기 위한 연산.
- Rollback - Transaction 처리가 비정상적으로 종료된 경우, Transaction을 다시 시작하거나, 연산한 결과를 취소시킨다.
