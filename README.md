# Stock Exchange

가상 증권 거래소를 **주문 → 체결 → 정산 → 조회**의 단일 흐름으로 구현한다.
자료구조, 동시성, 트랜잭션, 이벤트 정합성, 조회 최적화를 각각 "문제 재현 → 개선 → 측정"으로 남긴다.

구현 가이드(일정 · 단계별 할 일): [Virtual Stock Exchange — 구현 가이드](https://insoo-hwang.github.io/Study-Note/project/Virtual-Stock-Exchange/Virtual-Stock-Exchange/)

> 이 README는 Week 10에 "문제와 해결" 중심으로 다시 쓴다.

## 구조 — Modular Monolith

배포 단위는 하나, 도메인 경계는 패키지로 나눈다.

```text
src/main/java/com/exchange
├── ExchangeApplication.java
├── trading    주문, OrderBook, Matching, Worker, 체결 내역 조회
├── account    계좌, 보유주식, 자산 예약, Settlement
├── market     현재가 (MarketData Consumer, Redis)
├── outbox     Outbox 저장 / Publisher
└── common     공통 예외, 에러 응답 형식
각 모듈: domain / application / infrastructure / presentation  (outbox는 presentation 없음)
```

## Tech Stack

Java 21, Spring Boot 4, Spring Data JPA, PostgreSQL, Flyway, Lombok, JUnit 5, Testcontainers, Awaitility, Gradle

Kafka(Week 7)와 Redis(Week 9)는 필요해지는 단계에서 추가한다.

## Run

Docker가 필요하다. `bootRun` 시 `compose.yaml`의 PostgreSQL이 자동으로 실행된다.

```bash
./gradlew bootRun
curl localhost:8080/actuator/health
```

## Test

```bash
./gradlew test             # 통합 테스트 포함 (Testcontainers)
./gradlew reproduceTest    # 실패 재현 테스트만 (@Tag("reproduce"), CI에서는 제외)
```

## Tags

`v0.1-trading` → `v0.2-account` → `v0.3-event` → `v1.0`
