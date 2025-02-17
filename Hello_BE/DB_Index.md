# Question

- Database 에서 인덱스를 설정하는 방법과 사용시 주의점이 있을까?

---

# Answer

- (mariaDB 기준) Index는 `create index` 명령어를 통해 생성합니다.

```sql
# 단일 인덱스 생성 
create index index_name on tbl_name (index_col_name);

# 복합 인덱스 생성 
create index index_name on tbl_name (index_col_name1, index_col_name2);
```

- 사전에 이해해야 할 RDBMS의 동작 Flow
    1. DB는 Index를 사용하지 않을 경우 전체 데이터를 선형 탐색을 하여 데이터를 찾습니다. (full scan)

- Indexing은 특정 컬럼 - 데이터의 주소를 다른 곳에 저장하는 것입니다.
- 데이터를 조회할 때, Indexing된 컬럼을 지정한다면?
    - DB는 Index 유무를 판단하고, 해당 주소값을 바탕으로 데이터를 빠르게 필터링합니다.
    
    **→ 쉽게 표현하자면 Index는 데이터를 이원화하여 보관하고 있습니다.**
    

- 문제점
    - Indexing된 컬럼에 CUD 조작 시?
        - → Indexing된 데이터에도 반영이 필요합니다.
        - → select 를 제외한 INSERT / UPDATE / DELETE 의 성능에 영향을 줍니다.
    - 복합 인덱스를 사용할 경우?
        - 인덱스에 지정한 컬럼대로 사용해야 Index를 사용할 수 있음
        - Where 절에서 조건을 사용할 때 순서 고려가 필요함
    - 데이터의 고유값에 따라서 Index전략 필요
        - Index는 높은 카디널리티(고유값)에서 높은 효율을 나타냅니다.
        - 복합 인덱스를 설정할 때도, 카디널리티를 고려하여 설정이 필요합니다.

## Reference

> 인덱스를 제어하는 다양한 방법은 공식 문서를 참조해주세요.
> 

[CREATE INDEX](https://mariadb.com/kb/en/create-index/)
