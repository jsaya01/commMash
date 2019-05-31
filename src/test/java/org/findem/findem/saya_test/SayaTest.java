package org.findem.findem.saya_test;

import cm.Application;
import cm.user.User;
import cm.user.UserRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class SayaTest extends TestCase{

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUser() throws ExecutionException, InterruptedException {
        User user = new User("Jonah",
                "Test",
                "jsaya",
                "username",
                "testDesc",
                null);

        testEntityManager.persist(user);
        testEntityManager.flush();

        // when
        Future<User> foundFuture = userRepository.findUserByUsername(user.getUsername());


        if (!foundFuture.isDone()){
            assert(false);
        }
        User found = foundFuture.get();

        // then
        assertThat(found.getFname())
                .isEqualTo(user.getFname());
    }

}
