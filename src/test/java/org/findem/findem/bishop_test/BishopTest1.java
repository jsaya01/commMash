package org.findem.findem.bishop_test;

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

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class BishopTest1 extends TestCase{

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository comRepository;

    @Test
    public void testUserLookup() throws ExecutionException, InterruptedException {
        User user = new User(
	        		"Tester Nicholas",
	                "Test",
	                "nbishop",
	                "faleesi",
	                "test_desc",
	                null
                );

        testEntityManager.persist(user);
        testEntityManager.flush();

        // should work
        Future<User> foundFuture = userRepository.findUserByUsername(user.getUsername());

        if (!foundFuture.isDone()){
            assert(false);
        }
        
        User found = foundFuture.get();
        assertThat(found.getFname()).isEqualTo(user.getFname());
    }

    @Test
    public void testCommunityMultipleCommunities() throws ExecutionException, InterruptedException {
        Community community1 = new Community(
                "testCommunity1",
                null,
                "this is a test community"
        );
        
        Community community2 = new Community(
                "testCommunity2",
                null,
                "this is a test community1"
        );
        
        Community community3 = new Community(
                "testCommunity3",
                null,
                "this is a test community1"
        );

        testEntityManager.persist(community1);
        testEntityManager.persist(community2);
        testEntityManager.persist(community3);
        testEntityManager.flush();

        List<Community> foundFuture = comRepository.findAll();

        assertThat(foundFuture.size()).isEqualTo(3);
    }

}
