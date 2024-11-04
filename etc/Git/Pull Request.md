# Pull Request란?

Pull Request(PR) : 협업을 통한 코드 개발이 이루어지는 프로젝트에서 사용되는 기술

Github, Gitlab 등 버전 관리 시스템에서 제공된다. 

수정이 이루어진 사항을 원본이 되는 Repository에 반영을 요청하는 절차이다. 

# 과정

1. **Repository 설정**
    1. 원본(Origin)이 되는 Repository를 fork 한다. 
        - Fork : Origin Repository를 내 Repository로 복사
    2. Fork Repository를 clone하여 내 rocal Repository로 가져온다. 
        - clone : remote Repository의 내용을 rocal Repository로 가져온다.
2. **개발 및 commit**
    1. rocal Repository에서 새로운 기능 개발 or 버그를 수정한다.
3. **Remote Repository로 Push**
    1. rocal Repository의 작업 사항을 Fork Repository로 Push한다. 
4. **Pull Request 생성**
    1. Push 이후 Origin Repository 페이지로 이동하면 `Compare & pull request` 버튼이 활성화 된다. 
    2. 제목과 설명을 작성하여 PR을 생성한다. 
        1. 설명에는 변경 사항의 목적, 수정 내용, 테스트 결과 등이 포함될 수 있다.
5. **코드 리뷰 및 피드백** 
    1. PR이 생성되면 팀원들이 이를 리뷰하고 피드백을 제공할 수 있다. 
    2. 피드백이 있으면 local 환경에서 수정 작업을 하고, 변경 사항을 다시 commit, push 한다.
    3. PR은 자동으로 업데이트 된다.
6. **최종 승인 및 Merge**
    1. 모든 리뷰어가 변경 사항을 승인하면, `Merge pull request` 버튼으로 원본 Repository의 목표 Branch에 변경 사항을 병합한다.

# 정리

Pull Request는 코드 변경 사항을 공유하고 검토받을 수 있는 좋은 방법이다. 

코드 품질을 유지하면서 협업을 원활하게 할 수 있도록 도와주기 때문에, 많은 팀에서 필수적인 절차로 활용하고 있다.
