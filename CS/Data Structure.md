# I. 자료구조란?

자료구조는 개발자가 데이터를 효율적으로 사용할 수 있도록 자료를 구분하여 표현한 것.

각각의 자료구조는 장단점을 지니고 있으므로 어떤 자료구조가 최선일지는 당면한 문제와, 최적화에 따라 달라진다. 

**개발이란 알고리즘을 작성하고, 그에 맞는 자료구조를 선택하는 것**

# II. 자료구조의 분류

추상 데이터 타입(Abstract Data Type)은 자료구조를 설명하는 데이터의 타입을 말하며, 자료구조는 추상 데이터 타입을 실제로 구현한 결과를 말한다. 

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/88afb37d-2c38-4307-bb75-e53bfbd80efd/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/3dccf055-782d-457e-82f1-3b565f932790/image.png)

자료구조는 선형과 비선형 등의 여러 속성을 기반으로 분류할 수 있다.

**선형 자료구조(Linear Data Structure) : 데이터를 일렬로 나열한 자료 구조이다.** 

- 배열 (Array)
- 연결 리스트 (Linked List)
- 스택 (Stack)
- 큐 (Queue)

**비선형 자료구조(Nonlinear Data Structure) : 데이터를 순서와 상관 없이 계층 구조나 그래프 구조로 연결하는 자료 구조다.**

- 트리 (Tree)
- 그래프 (Graph)

# III. 필수 자료구조 8개

## 1) Array (배열)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/2b808a3c-3a80-420d-8e2d-e233e260198a/image.png)

- 동일한 타입의 데이터를 저장.
- 고정된 크기를 갖는다.

| 장점 | 단점 |
| --- | --- |
| 바로 만들어서 활용하기 쉽다. | 데이터를 저장 할 수 있는 메모리 크기가 고정되어 있다. |
| 더 복잡한 자료구조의 기초가 될 수 있다. | 데이터 추가 / 삭제 방법이 비효율적이다.  |
| 원하는 데이터를 효율적으로 탐색 / 가져올 수있다.  | 구조 재구성 시 정렬하는 방식이 비효율적이다.  |
| 정렬에 용이하다.  |  |

## 2) Linked List (연결 리스트)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/bfb2e689-dbda-439f-a901-5de923310f33/image.png)

- 각 데이터 시퀀스가 순서를 가지고 연결된 순차적 구조
- 동적인 데이터 추가 / 삭제에 유리하다.
- 각 요소는 **Node**
- 각 Node에는 **key와** 다음 노드를 가리키는 포인터인 **next**가 포함
- 첫 번째 요소는 **Head**
- 마지막 요소는 **Tail**

| 장점 | 단점 |
| --- | --- |
| 새로운 요소들의 추가 및 삭제가 용이하고 효율적이다.  | 배열보다 메모리를 더 사용한다.   |
| 배열처럼 메모리에 연속적으로 위치하지 않는다.  | 처음부터 끝까지 순회하기 때문에 원하는 값을 비효율적으로 검색 / 가져온다. |
| 배열처럼 구조의 재구성이 필요 없다. | 노드를 반대 방향으로 검색할 때 비효울적이다. 
(이중 연결 리스트의 경우) |
| 동적인 메모리 크기.  |  |
| 메모리를 더 효율적으로 쓸 수 있기 때문에 대용량 데이터 처리에 적합하다.  |  |

## 3) Stack (스택)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/eb82223c-06f9-42b5-a7b8-51401159cbf5/image.png)

- 순서가 보존되는 선형 데이터 구조
- 가장 마지막 요소부터 처리하는 LIFO (Last In First Out)
- ~~프링글스~~

| 장점 | 단점 |
| --- | --- |
| 동적인 메모리 크기 | 가장 최신 요소만 가져온다.  |
| 데이터를 받는 순서대로 정렬된다.  | 한번에 하나의 데이터만 처리 가능하다.  |
| 빠른 런타임 (runtime) |  |

## 4) Queue

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/1ed2fd76-2cdf-468a-853e-809b9d2d5d11/image.png)

- 가장 먼저 입력된 요소를  처리하는 FIFO (First In First Out)

| 장점 | 단점 |
| --- | --- |
| 동적인 메모리 크기 | 가장 마지막 요소만 가져온다.  |
| 데이터를 받는 순서대로 정렬된다.  | 한번에 하나의 데이터만 처리 가능하다.  |
| 빠른 런타임 (runtime) |  |

## 5) Hash Table (해시 테이블)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/4e9b1de5-c7f0-450e-bee7-8fab6ac2d2a3/image.png)

- 해시함수를 사용하여 변환한 값을 색인(Index)로 삼아 키(Key)와 데이터(Value)를 저장하는 자료구조
- 데이터의 크기에 관계없이 삽입 및 검색에 매우 효울적

| 장점 | 단점 |
| --- | --- |
| 새로운 요소들의 추가 / 삭제가 용이하고 효율적이다.  | 충돌이 일어날 수 있다.
(입력된 키의 해시값이 이미 데이터가 저장된 버킷을 가리킬 수 있다.) |
| 원하는 값의 검색 / 가져오기가 빠르고 효율적이다.  | 충돌이 자주 일어날 수 있으며 해시 함수의 정비가 필요한 경우가 많다. |
| 동적인 메모리 크기  |  |

## 6) Graph (그래프)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/cc294e77-9f28-4a33-a47f-e40cf77433f0/image.png)

- nodes / vertices 사이에 edge가 있는 collection
- directed(방향) 그래프는 일방통행
- undirected(무방향) 그래프는 양방향

| 장점 | 단점 |
| --- | --- |
| 새로운 요소들의 추가 / 삭제가 용이하고 효율적이다. | 충돌이 일어날 수 있다.
(입력된 키의 해시값이 이미 데이터가 저장된 버킷을 가리킬 수 있다.) |
| 구조의 응용이 가능하다. |  |

## 7) Tree (트리)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/6f26913b-11b2-43b5-a37a-d9d4002ee960/image.png)

- 그래프가 계층적 구조를 가진 형태.
- 최상위 노트(Root)를 가지고 있음.
- 상위 노드를 부모(Parent)노드, 하위 노드를 자식(Child) 노드라 한다.

## 8) Heap (힙)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/99796903-aeb2-40b2-a4fa-40eee1d111ac/image.png)

- Binary Tree (이진트리)
- 최소 힙 : 부모의 키 값이 자식의 키 값보다 작거나 같다.
    - Root 노드의 키 값이 Tree의 최솟값
- 최대 힙 : 부모의 키 값이 자식의 키 값보다 크거나 같다.
    - Root 노드의 키 값이 트리의 최댓값

---

[[알고리즘 + 자료구조 = 프로그램] 자료구조의 개념과 종류](https://www.hanbit.co.kr/channel/category/category_view.html?cms_code=CMS2832062046)

[자료 구조(Data Structure) 개념 및 종류 정리](https://bnzn2426.tistory.com/115)
