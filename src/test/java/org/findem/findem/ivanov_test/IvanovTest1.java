package org.findem.findem.ivanov_test;

import cm.Application;
import cm.community_post.CommunityPost;
import cm.community_post.CommunityPostRepository;
import cm.message_instance.MessageInstance;
import cm.message_instance.MessageRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class IvanovTest1 extends TestCase {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CommunityPostRepository communityPostRepository;

    @Test
    public void testMessageInstance() {
        MessageInstance messageInstance = new MessageInstance(1, 2, "test");

        testEntityManager.persist(messageInstance);
        testEntityManager.flush();

        // when
        Optional<MessageInstance> foundFuture = messageRepository.findById((long) 1);

        if (!foundFuture.isPresent()){
            assert(false);
        }

        MessageInstance found = foundFuture.get();

        // then
        assertThat(found.getUidfrom())
                .isEqualTo(messageInstance.getUidfrom());
    }

    @Test
    public void testCommunityPost() {
        CommunityPost communityPost = new CommunityPost(1, 1, null, "test post");

        testEntityManager.persist(communityPost);
        testEntityManager.flush();

        Optional<CommunityPost> foundFuture = communityPostRepository.findById((long)1);

        if (!foundFuture.isPresent()){
            assert(false);
        }

        CommunityPost found = foundFuture.get();

        assertThat(found.getUid())
                .isEqualTo(communityPost.getUid());
    }
}
