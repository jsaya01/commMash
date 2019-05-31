package org.findem.findem.saya_test;

import cm.Application;
import cm.community.Community;
import cm.community.CommunityRepository;
import cm.user.User;
import cm.user.UserRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SayaTest2 extends TestCase{

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

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

    @Test
    public void testCommunity() throws ExecutionException, InterruptedException {
        Community community = new Community(
                "testCommunity",
                null,
                "this is a test community"
        );

        testEntityManager.persist(community);
        testEntityManager.flush();

        Future<Community> foundFuture = communityRepository.findCommunityByName(community.getName());

        if (!foundFuture.isDone()){
            assert(false);
        }

        Community found = foundFuture.get();

        assertThat(found.getName())
                .isEqualTo(community.getName());
    }

}
