package org.findem.findem.saya_test;

import cm.Application;
import cm.community_tags.CommunityTags;
import cm.community_tags.CommunityTagsRepository;
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

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class SayaTest2 extends TestCase{

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserInterestTagRepository userInterestTagRepository;

    @Autowired
    private CommunityTagsRepository communityTagsRepository;

    @Test
    public void testUserInterestTag() throws ExecutionException, InterruptedException {
        UserInterestTag userInterestTag = new UserInterestTag(1, "test");

        testEntityManager.persist(userInterestTag);
        testEntityManager.flush();

        // when
        Optional<UserInterestTag> foundFuture = userInterestTagRepository.findById((long) 1);


        if (!foundFuture.isPresent()){
            assert(false);
        }

        UserInterestTag found = foundFuture.get();

        // then
        assertThat(found.getTag())
                .isEqualTo(userInterestTag.getTag());
    }

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

}
