package bishop_test;

import cm.Application;
import cm.community.Community;
import cm.community.CommunityRepository;
import cm.community_tags.CommunityTags;
import cm.community_tags.CommunityTagsRepository;
import cm.community_user_profile.CommunityUserProfileRepository;
import cm.matches.Matches;
import cm.matches.MatchesRepository;
import cm.user.User;
import cm.user.UserRepository;
import cm.user_interest_tag.UserInterestTag;
import cm.user_interest_tag.UserInterestTagRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class BishopTest2 extends TestCase{

    @Autowired
    private TestEntityManager testEntityManager;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private CommunityUserProfileRepository communityUserProfileRepository;

    @Test
    public void testUserInterestTag() throws ExecutionException, InterruptedException {
    	User user1 = new User(
        		"Tester Nicholas1",
                "Test",
                "nbishop",
                "faleesi",
                "test_desc",
                null
            );
    	
    	User user2 = new User(
        		"Tester Nicholas2",
                "Test",
                "nbishop",
                "faleesi",
                "test_desc",
                null
            );
    	
        testEntityManager.persist(user1);
        testEntityManager.persist(user2);
        testEntityManager.flush();
    	
        List<Matches> foundFuture = matchesRepository.findAll();
        assert(foundFuture.size() == 0);
        
        Matches match = new Matches(1, 2, new Timestamp(0));
        testEntityManager.persist(match);


        foundFuture = matchesRepository.findAll();
        assert(foundFuture.size() == 1);
        
        List<Object[]> ids = matchesRepository.findMatchesFromUid1((long) 1);
        assert(ids.size() == 1);
    }

    /*
    @Test
    public void testCommunityTags() throws ExecutionException, InterruptedException {
        CommunityTags community = new CommunityTags(1, "testComm");

        testEntityManager.persist(community);
        testEntityManager.flush();

        Optional<CommunityTags> foundFuture = communityTagsRepository.findById((long)1);

        if (!foundFuture.isPresent()){
            assert(false);
        }

        CommunityTags found = foundFuture.get();

        assertThat(found.getTag())
                .isEqualTo(community.getTag());
    }
    */

}
