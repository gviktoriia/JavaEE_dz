package kma.topic2.junit.service;

import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    @SpyBean
    private UserValidator userValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser(){
        NewUser newUser = NewUser.builder().login("green").password("12378").fullName("ligth").build();
        userService.createNewUser(newUser);
        Mockito.verify(userValidator).validateNewUser(newUser);
        assertThat(userRepository.isLoginExists("green")).isTrue();
    }

    @Test
    void getUserByLogin(){
        User person = User.builder().login("green").password("cat").fullName("rigth").build();
        NewUser newPerson = NewUser.builder().login("green").password("cat").fullName("right").build();
        userService.createNewUser(newPerson);

        assertEquals(person, userService.getUserByLogin(person.getLogin()));
    }
}