# 1회차

### Agenda

- agenda
    1. 좋은 아키텍쳐란? 
    2. Monolithic server
    3. RDS scailing
    4. EC2 scailing
    5. Cache
    6. CDN
    7. serverless

## 좋은 아키텍쳐란?

### 1. 서비스 운영 방식에 적합한 아키텍쳐

1. 개발자가 생각하기에 “이거 무조건 해야돼요”
2. 사용자가 서비스를 사용하는데 bottleneck이 발생하지 않는 아키텍쳐

### 2. 일반적인 케이스

1. 엔지니어링 관점에서 이런 것들을 고려해서 서비스를 안정적으로 운영할 수 있음
2. 케이스에 따라 그렇지 않은 경우도 있음
3. 초기단계에서 굳이 Auto Scailing을 해야하는지

## 서버가 하나일 때 :: Monolithic Server

- api.server.com으로 요청을 보낸다면?
    1. DNS에 주소를 날리면 아이피 주소를 반환한다. 
    (브라우저 → 주소 → DNS → IP주소 → 브라우저)
    2. 브라우저는 IP주소를 가지고 서버에 접속한다.
    3. 서버는 요청에 대한 response를 반환한다. 
        1. 서버는 DB에 Read, Write, Update를 요청하고 DB는 Data를 반환한다. 
        
    
    위 구조를 AWS 용어로 보자면?
    
    1. Route53에 주소를 날리면 아이피 주소를 반환한다. 
    (브라우저 → 주소 → DNS → IP주소 → 브라우저)
    2. 브라우저는 IP주소를 가지고 EC2에 접속한다.
    3. EC2는 요청에 대한 response를 반환한다. 
        1. EC2는 RDS에 Read, Write, Update를 요청하고 RDS는 Data를 반환한다. 
    
    이 때, EC2와 RDS는 같은 VPC(가상 프라이빗 클라우드 :: Virtual Private Cloud)안에 속한다.
    
    RDS는 VPC외부의 접속이 차단되고, 같은 VPC에 속한 EC2를 통해서만 접속 가능하다.
    
    더 안정적인 서비스를 위해서라면? ([🔗베스천 :: bastion)](https://harris91.vercel.app/bastion-host)
    → 브라우저와 통신하는 EC2와 RDS사이에 서버를 하나 더 두고, 새로운 서버만 RDS에 접속할 수 있게 설정한다.
    
     
    

### Route 53

1. **DNS 서비스** 
    1. 도메인 구입 및 관리

### 도메인 Routing Policy

1. **NS - 네임서버**
    1. 특정 도메인의 DNS 서버
    2. 도메인을 IP주소로 redirect 시키는 역할
    3. “일반적으로 도메인 등록 업체”에 가까움
        1. GoDabby에서 구입한 도메인을 AWS에서 관리할 수도 있음
        2. 도메인 설정에서 NS를 AWS에서 지정한 곳으로 옮김
2. **SOA - Start Of Authrity**
    1. 도메인 관련 주요 정보
    2. 이론상으로는 primary name server, 도메인 관리자 이메일, 시리얼 넘버 등등 정보
3. **TXT - Text**
    1. 도메인 권한 확인할 때 많이 사용
4. **A - Address**
    1. IP주소 입력해서 Routing
5. **CNAME - Canonical Name**
    1. 도메인 이름을 활용해서 Routing
6. **MX - Mail Exchange**
    1. 이메일 활용

### 도메인 이전 (NS 변경)

1. 도메인 구입 업체(?)에서 기관 이전이 가능하도록 해야함
    1. 업체는 계속해서 돈을 벌고싶기 때문에 아마 막아놨음

### Scailing을 고려한 Database 선택

1. **RDS**
    1. Relational Database Service, 관계형 데이터베이스 서비스
    2. PostgreSQL, MySQL, MariaDB
2. **NoSQL**
    1. 비정형 테이블
    2. DocumentDB(MongoDB), DynamoDB

1. **서비스를 처음 만들 때**
    1. 관계형 DB로 많이 시작
    2. 레퍼런스가 많다. 
    3. 많은 시니어들이 관계형 DB에 익숙해서(최적화)경험이 더 많다.
    4. **무료 - 비용측면에서 매우 유리**
        1. RDS는 무료가 아니다. MySQL이 무료 라이센스라는 의미.
        2. EC2 + RDS 구조보다 EC2 < Docker : MySQL이 더 저렴하다.
2. **서비스의 규모가 커진다면?**
    1. Join이 많이 발생하면 관계형 DB는 느려진다.
    2. 데이터 양이 많아지면 더 느려짐.
    3. 이런 경우 NoSQL 도입을 고려하게 된다.
        1. AWS는 대부분 DynamoDB
        2. 물론 서비스의 특성에 따라서 NoSQL로 시작할 수도 있다.
        3. Meta의 ‘Threath' 

- 데이터의 유형이나 양에 따라서 관계형 DB와 NoSQL 중 선택하는게 올바르다.

### scale Up vs scale Out

| 유형 | 설명 |
| --- | --- |
| Scale Up | 머신의 성능을 올린다. (사이즈를 키운다.) |
| Scale Out | 같은 머신을 늘린다.  |

AWS의 경우 large와 xlarge의 cpu, memory, 가격 모두 정확히 2배이다. 

그러나, Scale Out은 머신의 개수를 늘리기 때문에 요청을 n개 처리할 수 있다.

관리의 측면에선 Scale Up이 물리적인 성능을 높이기 때문에 편해서 서로의 특성을 고려하여 선택하면 된다.

### RDS의 Scale Up

1. 성능이 더 좋은 장비를 구입
2. AWS 콘솔에서 버튼 클릭

### RDS의 Scale Out - READ Replica

1. READ만 가능한 복제본을 만드는 것
2. 서버로 동시에 여러 클라이언트가 요청을 보내면
    1. 서버는 여러 DB에 READ query를 날릴 수 있음
    2. 요청이 몰려서 DB가 느려지거나, 터지는 것을 방지할 수 있음
3. WRITE는 Replicate 하지 않음
    1. WRITE는 여러 인스턴스에서 발생하면 데이터 복제가 복잡해짐
    2. WRITE는 하나만 할 수 있고, READ는 복제된 데이터를 여러곳에서 가져올 수 있도록
    3. Multi-Write를 바탕으로 구현된 DB도 있다.
        1. 서비스의 특성에 따라 다름
4. WRITE DB가 죽으면?
    1. READ DB중 하나가 WRITE DB로 대체
    2. WRITE DB의 Stand by DB를 가지고 있음

1. Add reader - Replica를 개발자가 추가
2. Add replica auto scaling
    1. AWS가 manage하면서 자동으로 scale Out
    2. 필요 시 인스턴스 늘리고
    3. 필요 없어지면 줄이는 방식
    

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/e775686a-8e42-441b-ad5e-a9a9b584ea62/Untitled.png)

샤딩과의 차이점

| 유형 |  |
| --- | --- |
| Replica | 데이터가 같은 DB를 복사 |
| Sharding | DB가 각각 다른 데이터를 가짐 |

### RDS Scale Out - Connection Pooling

1. connection을 미리 여러개 생성해두고 활용하는 방식 
2. AWS RDS Proxy 사용 가능
    1. Idle Timeout
    2. Max Connection
    3. Subnet
3. connection Pool은 AWS RDS보다 어플리케이션 단에서 처리하는게 일반적
    1. 비용문제
    2. 개발자가 코드로 관리하는게 유리할 것 같다는 의견

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/24ed85ac-86b2-42b0-b439-dc122eae8aa4/Untitled.png)

### DynamoDB Scale Up

1. Capacity 변경 가능

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/e39b7c60-e1d2-4e6a-a658-68b42a22bf5d/Untitled.png)

### DynamoDB Scale Out

1. Partition Key를 활용한 자동 Partitioning
2. 걱정할 것 없음
3. Serverless - 비용 매우 저렴
    1. 개인 프로젝트도 가급적 DynamoDB 추천 의견
    2. 개인 프로젝트에 RDS 사용하면 수십만원의 비용이 발생할 수도 있음

### scailing을 고려한 서버 선택

1. EC2도 RDS와 유사하게 Scale Up/Out 가능
    1. Scale Up은 RDS와 유사하게 더 큰 사이즈의 인스턴스를 선택 
    2. Scale Out은 Load Balancer가 필요
2. Load Balancer
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/58475008-6eb7-40bc-b1b5-17c19780fe19/Untitled.png)
    
    1. 단어 그대로 Load를 Balance하는 역할
    2. 클라이언트는 로드밸런서로 요청을 보내고, 로드밸런서가 요청을 서버로 분산
    3. EC2 헬스 체크
    4. SSL 인증서 적용
    5. High availability (왠만한 문제가 발생하더라도 서비스에 문제가 발생하지 않도록 AWS가 처리한다.
    6. ALB, NLB
        1. CLB도 있는데, 이제 사용하지 않음
        2. ALB (Application Layer Load Balancer) - HTTP/HTTPS, WebSocket 
        3. NLB - TCP, TLS, UDP
        4. EC2는 주로 ALB와 사용한다.
    7. ALB 설정 
        1. VPC 설정 후 Target Group을 지정한다. 
        2. EC2 Security Group 
            1. ALB에서 EC2에 접근할 수 있도록 설정 필요
                1. 클라이언트에서 오는 요청을 직접 받을 필요가 없음
                2. Ingress 요청을 ALB가 처리한다. 

### Cache

1. DB Cache
    1. Read가 빈번한 경우 Cache에 저장
    2. DB에 가지 않고 데이터를 가져올 수 있음
        1. Cache에 데이터가 있으면 바로 Read
        2. Cache에 데이터가 없으면 DB 접근
        3. **Server ↔ Cache ↔ DB**
2. 하지만, Cache를 사용하는 것도 비용
    1. Cache Instance 비용
    2. 개발 유지보수 비용
    3. latency를 줄이는것이 서비스 운영에 얼마나 중요한지를 고려해야함 :: “캐시를 꼭 써야하나?”
3. 업데이트 주기도 고려해야 한다.
    1. DB는 업데이트 됐는데, Cache는 예전 데이터를 가지고 있다면?
    2. Expiration 정책을 활용하거나, sync를 자주 해줘야한다.
    3. 서비스 운영 방식에 따라 다르게 고려해야한다. 

### AWS Cache

1. Elastic Cache
2. AWS DynamoDB Accelerator
    1. DynamoDB에 붙여서 사용 - READ 10배 빠름
3. CloudFront - CDN
    1. Content Delivery Network
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/b09a4726-400d-4c2a-8a82-d1b38adcca92/Untitled.png)
        
        1. static한 것들을 빠르게 가져올 수 있도록
        2. 주로 이미지나 영상 

### AWS CloudFront

1. 다른 서비스들과 다르게 Region이 Global
    1. Route 53도 Global이다.
2. Edge Location 활용
    1. AWS 자체적으로 데이터를 여러군데 흩뿌려줌
    2. 여러 Data Center에 caching해서 latency를 줄임
3. S3 연동 시 bucket policy 변경
    1. CloudFront에서 접근할 수 있도록 설정해주어야 CDN 활용 가능
    2. CloudFront 생성 시 S3 연결하면, 복사할 수 있는 Policy 제공

### 안정적인 서비스의 인프라 구성

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/3617d0f1-8d77-4ef2-90ba-303cd379eb56/Untitled.png)

1. 사용자는 CloudFront(CDN)을 통해서 이미지나 영상(static한 데이터) 접근
2. 서버와 DB가 통해서 데이터를 받을 때는 Write와 Read DB를 분리해놓는다. (데이터 정합성을 위하여 Write는 하나로 구성하는게 유리하다. → 인스타그램처럼 사용자가 너~무 많으면 Write도 분리해야 할 수도 있다.)
3. Elastic Cache도 사용하면서 조회가 빈번한 데이터는 DB를 거치지 않게끔 설계 

## 보안

### **1) Stateful**

1. 상태를 기억하는 것
2. 과거에 어떤 클라이언트가 요청을 보냈는지 

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/90bdc84c-7237-41ae-b6ad-1f7eba245149/Untitled.png)

**Problem :** 

- Load Balancer가 세션을 관리하지 않는 서버로 요청을 전달하면?

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/f28042c4-dc9f-4902-aa06-866e2eff9d05/Untitled.png)

### Sticky Session

1. **Load Balancer가 클라이언트가 요청을 보낸 서버를 기억하는 것**
2. 만약 이전에 요청을 처리한 서버가 죽었다면?
    1. 사용자는 인증을 다시 한다.
    2. 죽은 서버가 살아날 때까지 기다린다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/45fbcb63-e5fe-4e15-bd75-f67ce45e4c53/Untitled.png)

### Sticky Session 설정 방법

1. Cloud Formation에서 yml설정으로 배포 
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/2fc5fdbe-0a72-47b6-9823-4dfa5ac48b3f/Untitled.png)
    
2. ELB 생성할 때 configuration

### 2) Stateless

1. 각각의 요청이 분리됨
2. 모든 서버가 클라이언트의 요청을 처리할 수 있음
3. 로드밸런서의 오버헤드 감소 
4. latency 감소
    
    

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/8db47510-1fc3-4c2b-af80-e5f447187e15/Untitled.png)

## Serverless

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/a13f5dc2-74a9-442e-bff5-e189d5ff1d34/Untitled.png)

| Security Group  | 외부의 접근을 제어 |
| --- | --- |
| Subnet | 내부의 접근을 제어 |

---

[Bastion Host(배스천 호스트) 란?](https://harris91.vercel.app/bastion-host)
