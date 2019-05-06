package community_post;

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
    @Query("SELECT cp FROM CommunityPost cp where cp.pid = :pid")
    Future<CommunityPost> findCommunityPostByPID(@Param("pid") Long pid);
}


