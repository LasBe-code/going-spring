# GOING - SPRING

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

### 📙  리팩토링

### 📙  Spring Security를 이용한 비밀번호 암호화

### 📙  트랜잭션 사용

# ⚡️ 프로젝트 주요 기능


## 📙  로그인 및 회원가입

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled.png)

- 고객과 사업자 로그인, 회원가입 분리
- 카카오 API를 이용한 소셜 로그인
- 네이버 SENS를 이용한 문자인증
- 비동기적으로 중복체크, 문자인증
- 비밀번호 암호화 - 복호화

## 📙  숙소 검색

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%201.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%202.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%203.png)

- 메인 화면에서 검색창, 숙소 카테고리별, 추천으로 원하는 숙소를 탐색할 수 있습니다.
- 검색 결과창에서는 기존 검색 결과와 함께 가격 범위를 조정하여 재검색이 가능합니다.
- 지도 검색에서는 검색 결과를 지도에 출력해 숙소 위치를 직관적으로 파악할 수 있습니다.

## 📙  사업자 페이지

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%204.png)

- 고객과 분리된 사업자 페이지에서는 숙소와 관련된 모든 작업을 수행할 수 있습니다.

## 📙  숙소 찜하기

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%205.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%206.png)

- 원하는 숙소를 찜하거나 취소할 수 있고, 찜한 목록은 마이페이지에서 확인 가능합니다.

## 📙  결제 및 예약

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%207.png)

- 아임포트 API를 이용해 결제 서비스를 구현했으며 예약된 방은 중복처리되어 예약이 불가능하게 됩니다.

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%208.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%209.png)

- 사업자는 입실할 고객과 퇴실할 고객의 예약 상태를 유연하게 변경할 수 있습니다.
- 만약 체크아웃 날짜보다 일찍 퇴실 처리할 경우 예약 중복 처리된 방은 다시 예약할 수 있도록 처리됩니다.

## 📙  리뷰

### 🔎  고객

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2010.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2011.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2012.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2013.png)

- 퇴실 완료처리된 예약에 한해 고객은 1회 별점 평가과 리뷰 작성이 가능합니다.
- 작성된 평가와 리뷰는 숙소 상세페이지에서 확인 가능합니다.

### 🔎  사업자

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2014.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2015.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2016.png)

- 숙박 업체에 등록된 리뷰를 모두 확인할 수 있습니다.
- 고객이 작성한 리뷰에 답글을 달 수 있으며, 악성 댓글일 경우 관리자에게 신고할 수 있습니다.

## 📙  관리자 모드

### 🔎  **통계**

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2017.png)

- 서비스 관리자는 전반적인 서비스 매출에 대한 통계를 확인할 수 있습니다.

### 🔎  **사업자 가입 승인**

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2018.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2019.png)

- 사업자(숙박 업체)는 관리자의 승인을 한번 거쳐야 서비스를 이용할 수 있도록 했습니다.

### 🔎  리뷰 모니터링

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2020.png)

![Untitled](GOING%20-%20SPRING%2065d55764fa834e57b754e36919143cd1/Untitled%2021.png)

- 사업자가 신고한 리뷰는 관리자가 모니터링합니다.
- 정당한 리뷰라면 신고를 철회하고, 악성 리뷰라면 삭제할 수 있습니다.

# ⚡️ Troubleshooting


## 📙 예약 날짜 저장 / 동일 기간 예약 중복 처리

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
