# [Docker](https://lhseunge.oopy.io/9f54e03d-701f-46c9-954d-d7bc5e7f8180)

하나의 컴퓨터에 가상으로 컴퓨터를 만들고 그 위에 운영체제를 설치한 후 그 위에 웹서버를 설치한다면 어떨까

like **vmware** or **virtualbox**

하나의 컴퓨터에 각각의 앱을 실행하고, 각각의 앱은 격리된 환경에서 실행됨

운영체제가 설치된 컴퓨터는 Host 

Host에서 실행된 각각의 격리 구역을 Container

각각의 Container에는 운영체제 전체가 설치되는 것이 아닌, 
앱을 실행하는데 필요한 라이브러리, 실행파일만 포함

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/00876e81-3a13-4dca-8cd1-73999c996a07/Untitled.png)

리눅스 운영체제에는 이런 실행방법을 내장하고 있음 → Container 기술

Docker는 Container기술의 표준

---

Docker위에서 돌아가는 Container, Container안에서 동작하는 각각의 앱들은 리눅스 운영체제에서 동작하는 앱들이다.

내 컴퓨터가 리눅스 운영체제가 아니라면 도커를 쓸 수 없나? → no 가상머신으로 리눅스를 설치하고 사용하면 됨

→ 이 과정을 Docker가 해주므로 사용자는 복잡할 것이 없다.

내 컴퓨터가 리눅스가 아니라면 가상머신을 이용하기 때문에 속도 저하가 일어날 수 있음

→ 그럼에도 주류 Os에서 사용하는 이유는 도커가 주는 편의성 때문
