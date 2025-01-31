package com.mars.app.aop.auth;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.mars.app.component.auth.AuthenticationHolder;
import com.mars.app.domain.user.repository.UserRepository;
import com.mars.app.service.TestService;
import com.mars.app.support.IntegrationTestSupport;
import com.mars.common.exception.NPGException;
import com.mars.common.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

class AuthenticationAspectTest extends IntegrationTestSupport {

    @Autowired
    private UserRepository userRepository;

    @MockitoSpyBean
    private AuthenticationHolder authenticationHolder;

    @Autowired
    private TestService testService;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("인증된 사용자는 @AuthenticatedUser 어노테이션이 붙은 메서드를 실행할 수 있다.")
    @Test
    void authenticatedUserCanAccessMethod() {
        // given
        User authenticatedUser = User.builder()
            .email("authenticated@example.com")
            .role("ROLE_USER")
            .build();
        userRepository.save(authenticatedUser);
        setAuthenticationAsUserWithToken(authenticatedUser);

        // when
        String result = testService.testMethodReturnSuccess();

        // then
        assertThat(result).isEqualTo("success");
    }

    @DisplayName("익명 사용자는 @AuthenticatedUser 어노테이션이 붙은 메서드 실행 시 예외가 발생한다.")
    @Test
    void anonymousUserThrowsException() {
        // given
        SecurityContextHolder.clearContext();

        // when & then
        assertThatThrownBy(() -> testService.testMethodReturnSuccess())
            .isInstanceOf(NPGException.class)
            .hasMessage("인증 정보가 존재하지 않습니다.");
    }

    @DisplayName("존재하지 않는 사용자는 @AuthenticatedUser 어노테이션이 붙은 메서드 실행 시 예외가 발생한다.")
    @Test
    void nonExistentUserThrowsException() {
        // given
        User nonExistentUser = User.builder()
            .id(9999L)
            .email("nonexistent@example.com")
            .role("ROLE_USER")
            .build();
        setAuthenticationAsUserWithToken(nonExistentUser);

        // when & then
        assertThatThrownBy(() -> testService.testMethodReturnSuccess())
            .isInstanceOf(NPGException.class)
            .hasMessage("사용자를 찾을 수 없습니다.");
    }
}
