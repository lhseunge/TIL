# I. Logstash란?

Logstash는 오픈 소스 서버의 데이터 처리 파이프라인이다. 

다양한 소스에서 데이터를 수집하여 변환한 후 자주 사용하는 저장소로 데이터를 전달한다. 

Logstash는 **형식이나 복잡성과 관계 없이** 데이터를 동적으로 **수집, 전환, 전송**한다. 

# II. Logstash의 동작 방식

Logstash의 이벤트 처리 파이프라인은 **inputs → filters → outputs**의 세 단계로 구성된다. 

## Logstash Pipeline

| Inputs | 이벤트 생성 |
| --- | --- |
| Filters | 이벤트 수정 |
| Outputs | 이벤트를 다른곳으로 전달 |

Logstash의 입출력은 데이터가 파이프라인에 들어오거나 나갈 때 데이터를 인코딩 / 디코딩 할 수 있는 코덱을 지원한다. 

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/8a27ddf8-59a9-4809-8407-6e374b04b2d2/image.png)

## 1) Inputs (입력)

Logstash에서 데이터를 입력하려면 `Inputs` 를 사용한다. 

LogStash는 다양한 소스에서 이벤트를 동시에 가져오는 input 플러그인을 광범위하게 지원한다.

로그, 메트릭, 웹, DB저장소, AWS 등에서 데이터를 수집할 수 있다.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/5698aadb-c934-4e0c-954d-f265e3f2319f/image.png)

- Input Plugins

| Input Plugins | Descruption |
| --- | --- |
| **file** | UNIX 명령어 `tail -0F` 처럼 파일 시스템에서 파일을 읽는다. |
| **syslog** | 514 포트를 통해 syslog 메세지를 읽고, RFC3164 포맷에 따라 구분한다. |
| **redis** | redis 채널과 redis 목록을 모두 사용하여 redis 서버로부터 읽어온다. |
| **beats** | `Beats` 가 보낸 이벤트를 입력.  |
- [Input Plugins](https://www.elastic.co/guide/en/logstash/8.17/input-plugins.html) 더보기

## 2) Filters (필터)

`Filters` 는 Logstash의 중간 처리 장치이다. 

필터와 조건을 결합하여 특정 기준을 중족하는 경우, 이벤트에 대한 작업을 수행할 수 있다. 

- Filter Plugins

| Filter Plugins | Description |
| --- | --- |
| **grok** | 임의의 텍스트를 파싱하고 구조화한다. 
Grok은 현재 Logstash에서 구조화되지 않은 로그 데이터를 구조화되고 쿼리 가능한 것으로 파싱하는 가장 좋은 방법입니다. |
| **mutate** | 이벤트 필드에 대한 일반 변환을 수행합니다. 이벤트에서 필드의 이름을 바꾸고, 제거하고, 바꾸고, 수정할 수 있습니다. |
| **drop** | 이벤트를 완전히 삭제한다. |
| **clone** | 이벤트의 사본을 만들고 필드를 추가하거나 제거한다. |
| **geoip** | IP 주소의 지리적 위치에 대한 정보를 추가한다. |
- [Filter Plugins](https://www.elastic.co/guide/en/logstash/8.17/filter-plugins.html) 더보기

## 3) Outputs (출력)

`Outputs` 는 Logstash pipeline의 마지막 단계이다. 

이벤트는 여러 출력을 통과할 수 있지만, 모든 출력 처리가 완료되면, 이벤트는 실행을 마친다. 

- Output Plugins

| Output Plugins | Description |
| --- | --- |
| **elasticsearch** | `elasticsearch`로 데이터를 전송한다.
효율적이고 쉽게 쿼리 형태의 데이터를 전송할 수 있는 방법이다. |
| **file** | 디스크의 파일에 이벤트 데이터를 작성한다. |
| **graphite** | 데이터를 저장하고, 그래프로 표시하는 오픈소스 `graphite` 로 데이터를 전송한다. |
| **statsd** | `statsd`에 데이터를 전송한다. 
이는 카운터와 타이머같은 통계를 수신하고 UDP를 통해 전송하고 집계를 하나 이상의 플러그인 백엔드로 전송하는 서비스  |
- [Output Plugins](https://www.elastic.co/guide/en/logstash/8.17/output-plugins.html) 더보기

## 4) Codecs (코덱)

`Codecs` 는 입력과 출력의 일부로 작동할 수 있는 스트림 필터이다. 

코덱을 사용하면 메세지 전송을 직렬화 프로세스에서 쉽게 분리할 수 있다.

- Codec Plugins

| Codec Plugins | Description |
| --- | --- |
| **json** | JSON 형식으로 데이터를 인코딩 / 디코딩한다. |
| **multiline** | Java 예외 및 스택 추적 메시지와 같은 여러 줄의 텍스트 이벤트를 단일 이벤트로 병합한다. |
- [Codec Plugins](https://www.elastic.co/guide/en/logstash/8.17/codec-plugins.html) 더보기

