package org.findem.findem.bishop_test;

import cm.Application;
import cm.community.Community;
import cm.community_user_profile.CommunityUserProfile;
import cm.community_user_profile.CommunityUserProfileRepository;
import cm.matches.Matches;
import cm.matches.MatchesRepository;
import cm.user.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class BishopTest2 extends TestCase{

    @Autowired
    private TestEntityManager testEntityManager;
    

    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private CommunityUserProfileRepository communityUserProfileRepository;

    @Test
    public void testMatch() throws ExecutionException, InterruptedException {
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

        Matches match = null;
        try {
            match = new Matches(1, 2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        testEntityManager.persist(match);


        foundFuture = matchesRepository.findAll();
        assert(foundFuture.size() == 1);
        
        List<Object[]> ids = matchesRepository.findMatchesFromUid1((long) 1);
        assert(ids.size() == 1);
    }
    
    @Test
    public void testCommunityUserProfile() {
    	User user = new User(
        		"Tester Nicholas1",
                "Test",
                "nbishop",
                "faleesi",
                "test_desc",
                null
            );
    	
    	Community community = new Community("ai", null, "Ai");
    	
    	 testEntityManager.persist(user);
    	 testEntityManager.persist(community);
         testEntityManager.flush();
         
         CommunityUserProfile profile = new CommunityUserProfile(user.getUid(), community.getCid(), "new AI description");
         
         communityUserProfileRepository.save(profile);
         
         assert(communityUserProfileRepository.count() == 1);
         assert(communityUserProfileRepository.findAll().get(0).getCid() == community.getCid());
         assert(communityUserProfileRepository.findAll().get(0).getUid() == user.getUid());
    }
}
