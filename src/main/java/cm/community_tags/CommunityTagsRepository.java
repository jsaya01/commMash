package cm.community_tags;

import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;


@Repository
public interface CommunityTagsRepository extends JpaRepository<CommunityTags, Long>  {

	@Async
    @Query("SELECT ct FROM CommunityTags ct where ct.tid = :tid")
    Future<CommunityTags> findCommunityTagsByTID(@Param("tid") Long tid);
}
