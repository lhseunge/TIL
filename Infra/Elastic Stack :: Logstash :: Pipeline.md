# I. Pipeline (파이프라인)

데이터를 처리하기 위해 입력, 필터, 출력 (때로는 코덱) 플러그인을 함께 사용하여 파이프라인을 만들 수 있다.

매우 기본적인 파이프라인은 입력과 출력만 포함될 수 있다. 

하지만, 대부분의 파이프라인은 적어도 하나의 필터 플러그인이 포함되는데, 이는 **ETL(extract, transform, load)**의 **“transform”** 이 발생하기 때문이다. 

파이프라인에서 이벤트 필드를 참조하고 특정 기준을 충족할 때 조건부를 사용하여 이벤트를 처리할 수 있다. 

# II. 간단한 파이프라인 구성하기

로컬 PC에서 간단한 파이프라인 구성을 만든 다음 이를 사용하여, Logstash를 실행해볼 수 있다. 

`logstash-simple.conf`라는 파일을 생성하여 Logstash와 동일한 디렉토리에 저장한다. 

## 1) 파이프라인 작성

```bash
input { 
	stdin { } 
}

output {
  elasticsearch { cloud_id => "<cloud id>" api_key => "<api key>" }
  stdout { codec => rubydebug }
}
```

## 2) 구성된 파이프라인으로 Logstash 실행

```bash
bin/logstash -f logstash-simple.conf
```

[Logstash 설정 에제](https://www.elastic.co/guide/en/logstash/current/config-examples.html) 더보기

# III. Plugin 적용

## 1) Inputs

### Jdbc input plugin

- 사용법

```bash
input {
  jdbc {
    jdbc_driver_library => "/Users/test/logstash/logstash-8.17.0/postgresql-42.7.3.jar"
    jdbc_driver_class => "org.postgresql.Driver"
    jdbc_connection_string => "jdbc:postgresql://localhost:5432/lunch"
    jdbc_user => "seungpro"
    jdbc_password => "seungpro"
    parameters => { "key" => "k2systems" }
    schedule => "* * * * *"
    statement => "SELECT * from store.store where personal_key = :key"
  }
}
```

- [Jdbc Input Configuration Options](https://www.elastic.co/guide/en/logstash/8.17/plugins-inputs-jdbc.html#plugins-inputs-jdbc-options) 더보기

## 2) Filters

## 3) Outputs

### Elasticsearch

```bash

output {
  elasticsearch {

    hosts => ["localhost:9200"]

    ssl => true
    ssl_certificate_authorities => "/Users/test/http_ca.crt"
    user => "elastic"
    password => "wy7fhxK1-QiNZvnfVHr1"

    index => "my_index"
    document_type => "review"

  }

  stdout {
    codec => rubydebug
  }
}
```

- [Elasticsearch Output Configuration Options](https://www.elastic.co/guide/en/logstash/8.17/plugins-outputs-elasticsearch.html#plugins-outputs-elasticsearch-options) 더보기

---

[Creating a Logstash pipeline | Logstash Reference [8.17] | Elastic](https://www.elastic.co/guide/en/logstash/current/configuration.html)

[Jdbc input plugin | Logstash Reference [8.17] | Elastic](https://www.elastic.co/guide/en/logstash/8.17/plugins-inputs-jdbc.html)
