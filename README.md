# Show me your playlist

**유사 취향의 유저(정보)를 추천**해 줌으로써 음악 사냥을 도와주는 서비스 입니다.

취향에 맞는 음악 사냥을 돕기 위해, 탐색 범위를 좁혀주는 작업이 필요하다고 생각했고,

 탐색 범위를 좁히는 방법은 **나와 비슷한 취향의 유저를 추천해주는 것입니다.**

이 서비스는 두마디로!

> **너의 플레이리스트가 궁금해!**
> → (해석) 여기서 너는 취향이 비슷한 유저를 말함
>
> **알려주면, 나머지는 내가 찾아 볼게!**
> → (해석) 유사 취향의 유저 정보를 통해 음악 사냥 떠날게

프로젝트 소개 자세히 ! -> [링크](https://sebiblog.tistory.com/37?category=555906)

## 기술 스택

- Java 11
- Spring boot
- Gradle
- Mysql
- H2
- JPA
- QueryDSL
- JWT + Oauth(firebase) 로그인 
- Python
- Swagger/POSTMAN



## 위키

좌충우돌 개발 스토리를 기록합니다.

- [블로그 바로가기](https://sebiblog.tistory.com/category/Project/show%20me%20your%20playlist)

  - [1)  프로젝트 소개](https://sebiblog.tistory.com/37?category=555906)
  - [2) 기획과 설계](https://sebiblog.tistory.com/38?category=555906)

  - [3) 개발 컨벤션, 이슈 관리](https://sebiblog.tistory.com/39?category=555906)

  - [4) 개발 : 환경 셋팅](https://sebiblog.tistory.com/40?category=555906)
  - [5_ 개발 : 데이터 스크래핑](https://sebiblog.tistory.com/41?category=555906)

  

`WIKI` 라는이름으로 문서화하고 있습니다.

- [노션 위키](https://lean-owner-437.notion.site/show-me-your-playlist-0ab86888200945a6a703003cc4a20fdb)



### 문서화 보기

- [개발 컨벤션](https://lean-owner-437.notion.site/UI-5d0192046c6740c0830a43582b6f2879)
- [UI 기획 + 기능 설명서](https://lean-owner-437.notion.site/UI-5d0192046c6740c0830a43582b6f2879)
- [DB 명세서](https://lean-owner-437.notion.site/DB-20447f092be74a859ee87b78e65075a2)
- [API 명세서](https://lean-owner-437.notion.site/API-24ce38080448489198ee3b7f1c0e6ffd)



## 프로젝트 목표

1. 적용하고자 하는 기술과 방법의 당위성을 따져 의사 결정하려고 노력했습니다.

2. 코드리뷰를 통한 객체지향적 설계 개선합니다.

3. 코드리뷰를 통한 단위 테스트 개선합니다.

4. 추천 알고리즘은 A/B 테스트하여 두 알고리즘의 성능을 비교합니다.

5. CI/CD, 스케일 아웃, 기능 추가 등 요구 사항에 따라 대응할 수 있는 코드 디자인을 목표로 합니다.



## 화면 구성도

윔지컬을 이용해 화면을 그렸습니다.

https://whimsical.com/show-me-your-playlist-DcLkWuXCS7qEPYjjtpmvd

![image](https://user-images.githubusercontent.com/93963499/167527617-93f6906c-001d-4f91-8313-29f05e2bba4d.png)



## ERD

![image](https://user-images.githubusercontent.com/93963499/167527694-e2943dbf-cbbb-412b-bc41-72e41423043d.png)
