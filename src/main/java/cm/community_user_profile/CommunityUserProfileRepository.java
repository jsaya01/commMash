package cm.community_user_profile;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;


@Repository

public interface CommunityUserProfileRepository extends JpaRepository<CommunityUserProfile, Long> {

	@Async
    @Query("SELECT cup FROM CommunityUserProfile cup where cup.uid = :uid and cup.cid = :cid")
    Future<CommunityUserProfile> findCommunityUserProfile(@Param("uid") Long uid, @Param("cid") Long cid);

	@Query("SELECT cup.cid FROM CommunityUserProfile cup where cup.uid = :uid")
    List<Long> findCommunityFromUser(@Param("uid") Long uid);
	
	@Query("SELECT cup.uid FROM CommunityUserProfile cup where cup.cid = :cid")
    List<Long> findUserFromCommunity(@Param("cid") Long cid);

}
