# [Git Branch 전략](https://lhseunge.oopy.io/131f35ec-632d-8069-b418-e4148ed69a15)

## Git-Flow

![git-flow.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/1b3263f3-7e4e-489b-a99d-cde1dd487495/git-flow.png)

- feature : 실제 개발이 진행되는 Branch.
- develop : feature에서 작업된 항목을 병합하는 Branch.
- release : develop 내용 중 출시를 준비하기 위한 Branch.
- hotfix : 출시된 버전에서 버그 발생 시 긴급 수정을 위한 Branch.
- master : 출시된 Branch.

## Github-Flow

![github-flow.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/c1eb5d27-4130-4248-aa04-487379b40248/github-flow.png)

- feature : 개발이 진행되는 Branch.
- master : 출시된 Branch.

## Gitlab-Flow

![gitlab-flow.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/3b7f15ab-70ad-4846-9d78-be18878b5470/5b487e58-7baa-484b-bd78-044c809c7858/gitlab-flow.png)

- feature : 개발이 진행되는 Branch.
- master : 개발된 항목이 병합되는 Branch.
- pre-production : 출시 전 테스트를 위한 Branch.
- production : 출시된 Branch.
