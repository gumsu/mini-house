package com.example.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.board.domain.auth.JWTTokenProvider;
import com.example.board.domain.user.User;
import com.example.board.repository.UserRepository;
import com.example.board.request.SignInRequest;
import com.example.board.request.SignUpRequest;
import com.example.board.response.SignInResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private JWTTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("회원가입을 할 수 있다.")
    void register() {
        // given
        SignUpRequest request = signUpRequest();
        User user = createUserEntity(request);

        // mocking
        given(userRepository.save(any())).willReturn(user);
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        // when
        Long newUserId = userService.signUp(request);

        // then
        User findUser = userRepository.findById(newUserId).get();

        assertThat(user.getId()).isEqualTo(findUser.getId());
    }

    @Test
    @DisplayName("로그인을 할 수 있다.")
    void signIn() {
        // given
        SignInRequest request = SignInRequest.builder()
            .email("test@test.com")
            .password("1234")
            .build();

        User user = User.builder()
            .id(1L)
            .name("name")
            .email("test@test.com")
            .password("1234")
            .build();

        // mocking
        given(userRepository.findByEmail(any())).willReturn(Optional.ofNullable(user));
        given(jwtTokenProvider.generateToken(any(), any())).willReturn("123");

        // when
        SignInResponse response = userService.signIn(request);

        // then
        assertThat(response.getToken()).isEqualTo("123");
        assertThat(response.getUserName()).isEqualTo(user.getName());
    }

    @Test
    @DisplayName("가입되지 않은 이메일로 로그인을 할 수 없다.")
    void wrongEmail() {
        // given
        SignInRequest request = SignInRequest.builder()
            .email("test@test.com")
            .password("1234")
            .build();

        given(userRepository.findByEmail(any())).willThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> userService.signIn(request))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private User createUserEntity(SignUpRequest request) {
        return User.builder()
            .id(1L)
            .name(request.getName())
            .email(request.getEmail())
            .password(request.getPassword())
            .build();
    }

    private SignUpRequest signUpRequest() {
        return SignUpRequest.builder()
            .email("test@test.com")
            .name("name")
            .password("1234")
            .build();
    }

}
