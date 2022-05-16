# 🎒숙박플랫폼 GOING

Java와 Spring MVC를 기반으로 제작한 프로젝트입니다.

기존 Model2로 만든 세미 프로젝트를 스프링 형태로 바꾼 뒤 개선작업과 추가 기능을 삽입했습니다.

고객과 사업자(숙소), 관리자 파트로 기능을 나누어 플랫폼 형태의 서비스를 구축했습니다.

프로젝트 제작 기간 : 2022.04.21 ~ 2022.05.13

# ⚡️ 프로젝트 구성 및 설계


### 📙  Site Map

![사이트맵 (2).png](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/sitemap.png)

### 📙  Database ERD

![GOING (3).png](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/GOING_(3).png)

사진을 저장하는 BUSINESS(사업자)와 ROOM(객실) 테이블은 동일하고 중복되지 않는 시퀀스(pic_num)를 공유해 사진의 위치를 저장하도록 했습니다.   

### 📙  사용 기술

**백앤드**

- Java 11
- Spring MVC
- Spring AOP
- Spring Security
- JUnit4
- MyBatis

**DB & WAS**

- Oracle
- Oracle Cloud
- Tomcat 9

**프론트**

- HTML/CSS
- JavaScript
- Bootstrap 5
- JQuery

**Environment & Tool**

- Mac, Windows 10
- Eclipse
- VSCode
- GitHub
- SQL Developer

# ⚡️ 프로젝트 키워드


### 📙  JUnit을 이용한 단위 테스트

### 📙  AOP를 이용한 Logging

### 📙  [리팩토링](https://fate-close-2ba.notion.site/29f439b4840d471e976da90a1c265ffd)

### 📙  Spring Security를 이용한 비밀번호 암호화

### 📙  트랜잭션 사용


# ⚡️ [프로젝트 주요 기능](https://fate-close-2ba.notion.site/39aba2e82f38452da4058024006a3b00)

### 📙  로그인 및 회원가입

### 📙  숙소 검색

### 📙  사업자 페이지

### 📙  숙소 찜하기

### 📙  결제 및 예약

### 📙  리뷰

### 📙  관리자 모드

# ⚡️ Troubleshooting

### 📙 예약 날짜 저장 / 동일 기간 예약 중복 처리

초반기에 예약테이블의 체크인 - 체크아웃 날짜로만 예약 관련 서비스 로직을 처리했습니다.

이는 동일한 기간에 예약된 객실을 중복체크할 때 문제가 되었습니다.

객실이 예약 기간에 대해 중복될 경우의 수는 다음과 같습니다.

1. (예약할 체크인)  [예약된 체크인]  (예약할 체크아웃)  [예약된 체크아웃] 
2. (예약할 체크인)  [예약된 체크인]  [예약된 체크아웃]  (예약할 체크아웃) 
3. [예약된 체크인]  (예약할 체크인)  [예약된 체크아웃]  (예약할 체크아웃) 
4. [예약된 체크인]  (예약할 체크인)  (예약할 체크아웃)  [예약된 체크아웃]

이 4가지 경우의 수를 모두 체크할 수 있어야 완벽하게 중복 여부를 확인할 수 있는데 예약 내역의    

체크인-체크아웃 날짜로만 확인하기에는 무리가 있었습니다.

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2022.png)

이에 **객실 번호 - 예약 날짜**를 저장하는 RESERVED 테이블을 만들었고 객실을 예약할 때는 예약 된 날짜 하루하루를 저장할 수 있도록 하여 간단하게 BETWEEN을 이용해 중복 여부를 확인할 수 있도록 했습니다.

<aside>
💡 1월 1일 ~ 1월 3일 예약할 시 테이블에 1일, 2일, 3일 저장

</aside>
