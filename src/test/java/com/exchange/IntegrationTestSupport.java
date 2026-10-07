package com.exchange;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * 통합 테스트 공통 베이스. 실제 PostgreSQL(Testcontainers) 위에서 돈다.
 * <p>
 * 모든 통합 테스트가 같은 설정을 쓰게 해서 Spring 컨텍스트 캐시를 재사용한다.
 * 테스트마다 {@code @MockitoBean} 등을 따로 붙이면 컨텍스트가 새로 떠 느려진다.
 */
@SpringBootTest
@Import(TestcontainersConfiguration.class)
public abstract class IntegrationTestSupport {
}
