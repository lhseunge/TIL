## Dump sql File Export (내보내기)

### **1. mysqldump Command**

```css
mysqldump -u[user_id] -p[user_pw] [option] [db_name] [tb_name] > [backup_file_name.sql]
```

- u[user_id] : MariaDB 접속 계정
- p[user_pw] : MairaDB 접속 비밀번호
- [option] : dump 옵션, 여러개 중첩이 가능
- [db_name] : 옵션 별 필요에 의한 Database 명칭
- [tb_name] : 옵션 별 필요에 의한 Table 명칭
- [backup_file_name.sql] : 백업받을 파일 명, 경로가 지정되지 않을 경우 스크립트를 실행하는 디렉토리에 파일 생성.

### **2. 전체 데이터베이스 백업하기**

```less
Option : --all-databases

mysqldump -u[user_id] -p[user_pw] --all-databases > [backup_file_name.sql]
```

- -all-databases
    - 전체 데이터베이스에 대해 백업
    - mysql 등 메타정보가 담긴 데이터베이스도 함께 백업

### **3. 특정 데이터베이스 백업하기**

```less
Option : --databases [db_name_1] [db_name_2] ....

mysqldump -u[user_id] -p[user_pw] --databases [db_name_1] [db_name_2] .... > [backup_file_name.sql]
```

- --databases [db_name_1] [db_name_2] ....
- 데이터베이스를 명시적으로 1개 이상 백업

### **4. 특정 테이블 백업하기**

```less
Option : --databases [db_name] --tables [tb_name_1] [tb_name_2] ....

mysqldump -u[user_id] -p[user_pw] --databases [db_name] --tables [tb_name_1] [tb_name_2] .... > [backup_file_name.sql]
```

- -databases [db_name] --tables [tb_name_1] [tb_name_2] ....
    - 하나의 데이터베이스에 테이블을을 명시적으로 1개이상 백업
    - 동시에 여러 데이터베이스의 테이블은 백업 할 수 없음

### **5. 스키마만 백업하기 (데이터 제외)**

```less
Option : --no-data

mysqldump -u[user_id] -p[user_pw] --all-databases --no-data > [backup_file_name.sql]
```

- -no-data
    - 명시된 데이터베이스 혹은 테이블의 개체 정보만 백업
    - sql의 insert 문을 제외하고 백업

### **6. 데이터만 백업하기 (스키마 제외)**

```less
Option : --no-create-info

mysqldump -u[user_id] -p[user_pw] --all-databases --no-create-info > [backup_file_name.sql]
```

- -no-create-info
    - 명시된 데이터베이스 혹은 테이블의 데이터만 백업
    - sql의 create 문을 제외하고 insert 문만 백업

### **7. 특정 테이블 제외하고 백업**

```less
Option : --ignore-table=[db_name_1].[tb_name_1] --ignore-table=[db_name_2].[tb_name_2] ....

mysqldump -u[user_id] -p[user_pw] --all-databases --ignore-table=[db_name].[tb_name] > [backup_file_name.sql]
```

- -ignore-table=[db_name_1].[tb_name_1] --ignore-table=[db_name_2].[tb_name_2] ....
    - 해당 테이블만 제외하고 백업
    - 여러 테이블을 제외하고 싶을 경우 여러 번 명시

### **8. No Lock으로 백업 (InnoDB의 경우)**

```less
Option : --single-transaction

mysqldump -u[user_id] -p[user_pw] --all-databases --single-transaction > [backup_file_name.sql]
```

- -single-transaction
    - 하나의 트랜잭션을 이용하여 No Lock 으로 백업
    - InnoDB의 경우만 No Lock 으로 가능

### **9. 원격지 백업**

```less
Option : -h[host_ip]

mysqldump -u[user_id] -p[user_pw] -h[host_ip] --all-databases > [backup_file_name.sql]
```

- h[host_ip]
    - 원격지 [host_ip]의 데이터베이스를 옵션에 맞게 백업.

---

## Dump sql File Import (가져오기)

### **1. 전체 DB 복구**

```css
mysql -u[user_id] -p[user_pw] < [backup_file_name.sql]
```

- 전체 데이터베이스 백업 파일을 전체 복구 할 경우 mysql DB와 같은 메타정보가 덮어 써질 수 있으니 주의.
- 백업 파일 sql에 있는 전체를 전부 실행하여 복구한다.
    - Create Database 문이 포함되어있기 때문에 따로 지정한 데이터베이스 명이 없을 경우 생성한다.

### **2. 하나의 DB 복구**

```less
Option : --one-database

mysql -u[user_id] -p[user_pw] --one-database [db_name] < [backup_file_name.sql]
```

- 백업 파일 sql 내용 중 하나의 DB만 복구 할 경우 사용.
- [db_name]에 해당하는 DB는 미리 생성되어 있어야한다.
    - 다른 이름의 DB로도 복구가 가능.

### **3. 원격지 DB에 복구**

```rust
Option : -h[host_ip]

mysql -u[user_id] -p[user_pw] -h[host_ip] < [backup_file_name.sql]
```

- [host_ip]로 원격지 정보를 입력 후 해당 데이터베이스에 복구.

---

[[MariaDB] mysqldump를 활용한 백업의 모든 것과 복원](https://da-new.tistory.com/67)
