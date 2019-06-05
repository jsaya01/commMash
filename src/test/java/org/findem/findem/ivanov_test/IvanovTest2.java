package org.findem.findem.ivanov_test;

import cm.Application;
import cm.community.Community;
import cm.community.CommunityRepository;
import cm.matches.Matches;
import cm.matches.MatchesRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class IvanovTest2 extends TestCase {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Test
    public void testMatches() throws ParseException {
        Matches matches= new Matches(1, 2);

        testEntityManager.persist(matches);
        testEntityManager.flush();

        // when
        List<Object[]> foundFuture = matchesRepository.findMatchesFromUid1((long) 1);

        if (foundFuture.isEmpty()){
            assert(false);
        }

        Long found = (Long)foundFuture.get(0)[0];

        // then
        assertThat(found)
                .isEqualTo(matches.getUid2());
    }

    @Test
    public void testCommunity() {
        Community community = new Community("help", null, "test post");

        testEntityManager.persist(community);
        testEntityManager.flush();

        List<Community> foundFuture = communityRepository.findAll();

        if (foundFuture.isEmpty()){
            assert(false);
        }

        Community found = foundFuture.get(0);

        assertThat(found.getName())
                .isEqualTo(community.getName());
    }
}
