package org.findem.findem.tschopp_test;

import cm.Application;
import cm.community.Community;
import cm.community.CommunityRepository;
import cm.community_user_profile.CommunityUserProfile;
import cm.community_user_profile.CommunityUserProfileRepository;
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
public class TschoppTest1 extends TestCase{

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommunityUserProfileRepository communityUserProfileRepository;

    @Test
    public void testCommunityUserProfile() throws ExecutionException, InterruptedException {
        User user = new User("Alex",
                "Test",
                "altschop",
                "username",
                "testDesc",
                null);

        testEntityManager.persist(user);
        testEntityManager.flush();

        Community community = new Community("testCommunity", null, "test");
        testEntityManager.persist(community);
        testEntityManager.flush();

        CommunityUserProfile cup = new CommunityUserProfile(1, 1, null);
        testEntityManager.persist(cup);
        testEntityManager.flush();

        // when
        long id = 1;
        Future<CommunityUserProfile> foundFuture = communityUserProfileRepository.findCommunityUserProfile(id, id);


        if (!foundFuture.isDone()){
            assert(false);
        }

        CommunityUserProfile found = foundFuture.get();

        // then
        assertThat(found.getUid())
                .isEqualTo(user.getUid());
    }

    @Test
    public void testCommunityUserProfile2() throws ExecutionException, InterruptedException {
        User user1 = new User("AlexT",
                "Test",
                "altschop",
                "username",
                "testDesc",
                null);

        testEntityManager.persist(user1);
        User user2 = new User("AlexTT",
                "Test",
                "altschopp",
                "username",
                "testDesc",
                null);
        testEntityManager.persist(user2);
        User user3 = new User("AlexTTT",
                "Test",
                "altschoppp",
                "username",
                "testDesc",
                null);
        testEntityManager.persist(user3);
        testEntityManager.flush();

        Community community = new Community("testCommunity", null, "test");
        testEntityManager.persist(community);
        testEntityManager.flush();

        CommunityUserProfile cup = new CommunityUserProfile(3, 1, null);
        testEntityManager.persist(cup);
        testEntityManager.flush();

        // when
        long uid = 1;
        long cid = 3;
        Future<CommunityUserProfile> foundFuture = communityUserProfileRepository.findCommunityUserProfile(uid, cid);


        if (!foundFuture.isDone()){
            assert(false);
        }

        CommunityUserProfile found = foundFuture.get();

        // then
        assertThat(found.getUid())
                .isEqualTo(user3.getUid());
    }

}
