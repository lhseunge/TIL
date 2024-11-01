# [Git](https://lhseunge.oopy.io/130f35ec-632d-8019-b1de-f5bdb61fa296)

# Git은?

컴퓨터 파일의 변경사항을 추적하고, 여러 사용자간 작업을 조율하기 위해 개발된 버전 관리 시스템(VCS)이다. 

# Git의 구조

## 구성

### 로컬 저장소 (local repo)

- 사용자의 PC라고 할 수 있다.
- 버저닝할 작업 폴더를 기준으로 한다.
- 변경이 일어난 파일은 `git add` 명령어로 스테이징 영역에 올라간다.
- 스테이징 영역에 있는 파일들은 `git commit` 명령어로 로컬 저장소에 반영된다.

### 원격 저장소 (remote repo)

- 외부 서버의 저장소.
- 대개 로컬 저장소의 파일 변경 이력을 다른 사람과 공유하기 위한 용도로 사용된다.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/9dc654b9-f44f-4724-aa4b-fc2e26dae172/image.png)

# 버전 관리 (Version Control)

## 가지 (Branch)

- VCS의 개념 중 하나로, 원본의 **복사본**이다.
- commit이라는 명령어를 통해 가지를 길게 늘려나갈 수 있다.

## 가지 분기 (Branching)

1. 가지의 시작점부터 복사된 가지를 늘릴 수 있다. 
    
    ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/15c63ebe-7804-4601-b708-74bd2df3a0f6/image.png)
    
2. 복사된 가지는 이후로 다르게 변경이 일어날 수있다. 
    1. `branches/1` 에서는 [Present.md](http://Present.md) 파일을 생성하였다. (내용 없음)
        
        ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/1ff6b223-7c53-4469-9746-e0a818795417/image.png)
        
    2. `branches/1_1` 에서는 [Present.md](http://Present.md) 파일의 내용을 추가하였다.
        
        ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/7ce5345e-79aa-493f-900d-289f5df8b2ac/image.png)
        
    3. `branches/1_2` 에서는 [Present.md](http://Present.md) 파일을 내용을 **다르게** 추가하였다. 
        
        ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/15054287-38ee-4432-8d7a-7bf8d3a7a988/image.png)
        

## 병합 (Merge)

- 뻗어져 나간 가지들을 하나로 묶는 작업
    - ex) 여러 개발자들이 수정한 이력을 하나로 합칠 때 사용
1. `branches/1_2` 와 `branches/2` 를 병합 상태 
    
    ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/23c724cb-96bb-4fe4-ad41-a6bd00f12b71/image.png)
    
2. branch별로 각각 생성한 파일 [Present.md](http://Present.md)와 [Other.md](http://Other.md)를 모두 갖고 있는 상태이다.
    
    ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/653d16d7-42d8-4948-bf44-9e77d6b1667b/image.png)


### Git Branch 전략

# Summary

이러한 특성을 가진 Git을 사용하여 하나의 프로젝트에 여러명의 개발자들이 개발 할 수 있다.

# Git Branch 전략

[Git : Branching Strategies](https://lhseunge.oopy.io/130f35ec-632d-8019-b1de-f5bdb61fa296)
