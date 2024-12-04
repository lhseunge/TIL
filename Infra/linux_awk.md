# [linux :: awk 활용](https://lhseunge.oopy.io/152f35ec-632d-8022-99d1-f7f2d20cb5a3)

docker image 정리를 위해 특정 호스트의 image를 정리하고 싶었다. 

먼저, `docker images` 의 결과를 `grep` 하였다. 

```bash
# command 
docker images | grep localhost

# prompt
localhost:5000/asia.gcr.io/project-177804/emulator         tb        a68cc348014d   4 days ago     380MB
localhost:5000/project                                     tb-emul   0ebce20c0b4e   4 days ago     494MB
localhost:5000/lmm                                         tb        897c355c78f1   6 days ago     424MB
localhost:5000/asia.gcr.io/project-177804/onm              tb        a18694b8548e   2 weeks ago    374MB
localhost:5000/asia.gcr.io/project-177804/batch            tb        7906be2b70ef   2 weeks ago    229MB
localhost:5000/asia.gcr.io/project-177804/nginx-ssl        x86       3b0540070211   2 weeks ago    192MB
localhost:5000/api-gateway                                 x86       541065039027   6 weeks ago    756MB
localhost:5000/egenerator                                  x86       ec4daa685fb3   6 weeks ago    577MB
localhost:5000/asia.gcr.io/project-177804/admin-console    x86       ee98b826d6bf   6 weeks ago    415MB
localhost:5000/asia.gcr.io/project-177804/rsync-server     tb        1de9aa3ead17   6 weeks ago    12MB
localhost:5000/docker.company.com/mdp/project/filebeat     tb        c170d3300bc1   6 weeks ago    328MB
localhost:5000/asia.gcr.io/project-177804/kibana           tb        a0cab407b82e   6 weeks ago    797MB
localhost:5000/asia.gcr.io/project-177804/logstash         tb        a17752fadb38   6 weeks ago    700MB
localhost:5000/asia.gcr.io/project-177804/mariadb          tb        07372e78724f   6 years ago    363MB
```

`docker images`의 결과는 `REPOSITORY`, `TAG`, `IMAGE ID`, `CREATED`, `SIZE` 로 출력된다. 

docker image 삭제 명령어는 `docker rmi <삭제할 이미지:TAG>` 로 `docker images`의 결과 중 `REPOSITORY` 와 `TAG` 정보만 필요하다.

필요한 정보만 필터링 하기 위해 사용할 `awk`는  ***특정 텍스트를 원하는 대로 필터링 하거나 가공하여 출력해주는 프로그램***이다. 

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/913bdc25-cb1f-4a31-a593-465ba2189cc1/image.png)

`awk` 에는 수많은 기능을 제공하지만, 기본적인 기능으로 특정 필드만 필터링 할 수 있다. 

```bash

# command 
docker images | grep localhost | awk '{print $1,$2}'

# prompt
localhost:5000/asia.gcr.io/project-177804/emulator tb
localhost:5000/project tb-emul
localhost:5000/lmm tb
localhost:5000/asia.gcr.io/project-177804/onm tb
localhost:5000/asia.gcr.io/project-177804/batch tb
localhost:5000/asia.gcr.io/project-177804/nginx-ssl x86
localhost:5000/api-gateway x86
localhost:5000/egenerator x86
localhost:5000/asia.gcr.io/project-177804/admin-console x86
localhost:5000/asia.gcr.io/project-177804/rsync-server tb
localhost:5000/docker.company.com/mdp/project/filebeat tb
localhost:5000/asia.gcr.io/project-177804/kibana tb
localhost:5000/asia.gcr.io/project-177804/logstash tb
localhost:5000/asia.gcr.io/project-177804/mariadb tb
```

`REPOSITORY` 와 `TAG`  의 필드를 필터링 하였지만, `docker rmi` 명령어와 조합하여 사용하려면 구분자로 사용된 공백 부분을 :(콜론) 으로 바꿔주어야 한다. 

다음은 구분자를 지정하는 `awk` 기능을 적용한 예제이다. 

```bash
# command
docker images | grep 10.9 | awk 'BEGIN{OFS = ":"}{print ($1, $2)}'

# prompt
localhost:5000/asia.gcr.io/project-177804/emulator:tb
localhost:5000/project:tb-emul
localhost:5000/lmm:tb
localhost:5000/asia.gcr.io/project-177804/onm:tb
localhost:5000/asia.gcr.io/project-177804/batch:tb
localhost:5000/asia.gcr.io/project-177804/nginx-ssl:x86
localhost:5000/api-gateway:x86
localhost:5000/egenerator:x86
localhost:5000/asia.gcr.io/project-177804/admin-console:x86
localhost:5000/asia.gcr.io/project-177804/rsync-server:tb
localhost:5000/docker.company.com/mdp/project/filebeat:tb
localhost:5000/asia.gcr.io/project-177804/kibana:tb
localhost:5000/asia.gcr.io/project-177804/logstash:tb
localhost:5000/asia.gcr.io/project-177804/mariadb:tb
```

`BEGIN{OFS = ":"}`를 사용하여 `docker rmi` 와 조합하기 위해 `<삭제할 이미지:TAG>` 구조로 출력하는데 성공했다. 

이제 해당 내용을 `(백틱)으로 감 싸 `docker rmi` 와 사용하여 문제를 해결하였다. 

```bash
docker rmi `docker images | grep localhost | awk 'BEGIN{OFS = ":"}{print ($1, $2)}'`
```
