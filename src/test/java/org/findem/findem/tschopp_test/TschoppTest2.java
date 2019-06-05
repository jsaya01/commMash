package org.findem.findem.tschopp_test;

import cm.Application;
import cm.community_post.CommunityPost;
import cm.community_post.CommunityPostRepository;
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

import java.sql.Date;
import java.text.ParseException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class TschoppTest2 extends TestCase {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private CommunityPostRepository communityPostRepository;

    @Test
    public void testMatches() throws ParseException {
        User user1 = new User("AlexT",
                "Test",
                "alttschop",
                "username",
                "testDesc",
                null);

        testEntityManager.persist(user1);
        testEntityManager.flush();

        User user2 = new User("AlexTT",
                "Test",
                "alttschopp",
                "username",
                "testDesc",
                null);
        testEntityManager.persist(user2);
        testEntityManager.flush();

        long id1 = 1;
        long id2 = 1;
        Matches match = new Matches(id1, id2);
        testEntityManager.persist(match);
        testEntityManager.flush();

        // when
        Optional<Matches> foundFuture = matchesRepository.findById((long)3);

        if (!foundFuture.isPresent()){
            assert(false);
        }

        Matches found = foundFuture.get();

        // then
        assertThat(found)
                .isNotNull();
    }

    @Test
    public void testCommunityPost() throws ParseException {
        CommunityPost communityPost = new CommunityPost(1, 1, Date.valueOf("2018-09-01"), "content");

        testEntityManager.persist(communityPost);
        testEntityManager.flush();

        Optional<CommunityPost> foundFuture = communityPostRepository.findById((long)2);

        if (!foundFuture.isPresent()){
            assert(false);
        }

        CommunityPost found = foundFuture.get();

        assertThat(found)
                .isNotNull();
    }

}
