# Redis

# Remote Dictionary Server

# 레디스란?

- 레디스는 Key-Value NoSQL 저장소
- 비정형 데이터의 저장, 관리에 용의하다.
- In-memory 구조
- DB, Cache, Message Queue, Shared Memory 용도로 사용

> 인메모리 ( In-Memory )
컴퓨터의 주기억장치인 RAM에 데이터를 올려서 사용하는 방법.
RAM에 데이터를 저장하게 되면 메모리 내부에서 처리가 되므로, 
데이터를 저장/조회할 때 하드디스크를 오고가는 과정을 거치지 않아도 되어 속도가 빠름
⠀
But, 서버의 메모리 용량을 초과하는 데이터를 처리할 경우,
RAM의 특성인 휘발성에 따라 데이터가 유실될 수 있음
> 

## 레디스의 특징

- Key-Value 구조
- 빠른 처리 속도
- Data Type(Collection)을 지원
    
    ![image.png](attachment:3d51675a-7235-4248-97bc-c6bc40f50566:image.png)
    
- AOF, RDB 방식
    - In-Memory의 단점인 데이터 유실을 방지하기 위해 **백업 기능을 제공**
    - AOF : Redis의 모든 write/update 연산 자체를 모두 log 파일에 기록하는 형태
    - RDB : 순간적으로 메모리에 있는 내용 전체를 디스크에 담아 영구 저장하는 방식
- Redis Sentinel 및 Redis Cluster를 통한 **자동 파티셔닝을 제공**
    - Master와 Slaves로 구성하여 여러대의 복제본을 만들 수 있고, 여러대의 서버로 읽기를 확장 가능
- 다양한 프로그래밍 언어 지원
- Single Thread
    - 한번에 하나의 명령만 수행이 가능하므로 동시성 문제 방지 가능
    - 

---

[Redis란 무엇일까? - Redis의 특징과 사용 시 주의점](https://velog.io/@wnguswn7/Redis%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C-Redis%EC%9D%98-%ED%8A%B9%EC%A7%95%EA%B3%BC-%EC%82%AC%EC%9A%A9-%EC%8B%9C-%EC%A3%BC%EC%9D%98%EC%A0%90)

더 작성 필요 

[Getting Started :: Spring Data Redis](https://docs.spring.io/spring-data/redis/reference/redis/getting-started.html)
