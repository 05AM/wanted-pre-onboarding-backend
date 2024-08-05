# wanted-pre-onboarding-backend
원티드 백엔드 프리온보딩 인턴십 사전 미션

<br>

## ➡️ API Doc
접속 방법: 프로그램 실행 후 링크 접속

http://localhost:8080/swagger-ui/index.html

<br>

## ➡️ Database
- 사양: H2 인메모리 DB
- 접속 url: http://localhost:8080/h2-console
- 기본 member ID: 1

Dummy Data 정보는 `src/main/resources/data.sql`을 참고 부탁드립니다.

<br>

## ➡️ ERD
![drawSQL-image-export-2024-08-04](https://github.com/user-attachments/assets/e2dd47e2-7199-4903-ae7e-44cc7a7432cd)

<br>

## ➡️ 요구사항
- [x]  채용공고를 등록한다.

- [x]  채용공고를 수정한다.
  - 회사 id 제외 모든 필드 수정 가능
     
- [x]  채용공고를 삭제한다.

- [x]  채용공고 목록을 조회한다.
  - 채용공고를 검색할 수 있다.

- [x]  채용공고 상세 페이지를 조회한다.
  - 회사가 올린 다른 채용공고 목록이 포함된다.

- [x]  사용자가 채용공고에 지원할 수 있다.
  - 사용자는 한 채용공고에 1회만 지원이 가능하다.
