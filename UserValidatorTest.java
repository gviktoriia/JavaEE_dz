package kma.topic2.junit.validation;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserValidator userValidator;

    @Test
    void test(){

        Mockito.when(userRepository.isLoginExists( ArgumentMatchers.anyString())).thenReturn(true);

        NewUser newUser = NewUser.builder().login("cat").password("1234").fullName("Linux").build();
        assertThatThrownBy(() -> userValidator.validateNewUser(newUser));

    }

    @Test
    void validatePasswordTest(){
        Mockito.when(userRepository.isLoginExists(ArgumentMatchers.anyString())).thenReturn(false);
        NewUser newUser = NewUser.builder().login("log").password("five").fullName("cattle").build();
        assertThatThrownBy(() -> userValidator.validateNewUser(newUser)).isInstanceOf( ConstraintViolationException.class);
    }
}
