package communityPost;

import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import community.Community;

	
@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    @Async
    @Query("SELECT u FROM User u where u.pid = :pid")
    Future<CommunityPost> findCommunityPostByPID(@Param("pid") long pid);
}


