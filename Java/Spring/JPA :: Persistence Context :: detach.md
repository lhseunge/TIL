# 준영속 사태

<aside>

영속 상태의 Entity가 영속성 컨텍스트에서 분리된 상태. (detached)

Entity가 준영속 상태에서는 영속성 컨텍스트의 기능을 사용할 수 없다. 

</aside>

## 준영속 상태의 특징

1. 비영속 상태에 가깝다. 
    - 영속성 컨텍스트의 어느 기능도 동작하지 않음.
2. 식별자 값을 갖고 있다. 
    - 비영속 상태의 식별자는 값이 없을 수도 있지만, 준영속 상태의 Entity는 무조건 영속화가 선행되기 때문에 식별자 값을 갖고 있다.
3. 지연 로딩할 수 없다. 
    - 준영속 상내는 영속성 컨텍스트가 관리하지 않으므로 지연 로딩 시 문제가 발생한다.

## Entity를 준영속 상태로 만드는 3가지 방법

### EntityManager.detach(ENTITY) : 특정 Entity만 준영속화

- Entity 객체를 em.persist() 메소드로 영속화 하고 이어서 em.detach() 메소드로 준영속화 상태가 되면,
- 해당 Entity는 영속성 컨텍스트의 관리 영역에서 벗어나게 된다.
- 영속성 컨텍스트의 기능을 사용할 수 없기 때문에, SQL 저장소의 Insert SQL도 DB에 반영되지 않는다.

### EntityManager.clear() : 영속성 컨텍스트를 초기화

- em.detach()가 특정 Entity를 준영속 상태로 변경한다면,
- em.clear()는 영속성 컨텍스트의 Entity를 준영속 상태로 변경한다.

### EntityManager.close() : 영속성 컨텍스트를 종료

- EntityManager가 종료되면 EntityManager가 관리중인 Entity는 **준영속 상태가 된다.**

> Entity가 준영속 상태가 되는 케이스는 일반적으로 영속성 컨텍스트가 종료되었을 경우이다.
Entity를 임의적으로 준영속 상태로 분리하는 케이스는 드물다.
> 

## 병합: merge()

<aside>

준영속 Entity를 영속 상태로 변경하려면 병합을 사용하면 된다. 

</aside>

- merge()는 준영속 상태의 Entity를 인자로 받아 동일한 정보를 갖는 새로운 영속 Entity를 반환한다.

### workflow

1. EntityManager.merge(ENTITY) 메소드 시작.
2. 영속성 컨텍스트의 1차 캐시 or DB에서 Entity의 식별자를 통해 조회.
3. 조회한 영속 Entity에 merge할 Entity의 데이터를 주입한다. 

- em.contains(ENTITY)는 인자 Entity가 EntityManager에 의해 관리되는지 확인하는 메소드이다.

### 비영속 병합

- 지금까지는 준영속 상태의 Entity의 병합을 하였다.
- 추가로, merge()는 비영속 상태의 Entity를 영속화할 수도 있는데,
- 식별자의 값을 통해 영속성 컨텍스트의 1차 캐시 or DB에서 데이터를 조회하고,
- 조회 결과를 통해 Insert 혹은 Update를 수행한다.
