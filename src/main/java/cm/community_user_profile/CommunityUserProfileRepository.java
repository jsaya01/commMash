package cm.community_user_profile;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Future;

import cm.community.Community;
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

	@Query("SELECT cid FROM CommunityUserProfile cup where cup.uid = :uid")
    List<Long> findCommunityFromUser(@Param("uid") Long uid);
	
	@Query("SELECT uid FROM CommunityUserProfile cup where cup.cid = :cid")
    List<Long> findUserFromCommunity(@Param("cid") Long cid);

    @Query(value = "SELECT cid " +
            "FROM (SELECT cid, COUNT(*) as cnt FROM community_user_profile cup GROUP BY cid) as cnts " +
            "ORDER BY cnts.cnt DESC " +
            "LIMIT :num", nativeQuery = true)
    List<BigInteger> findTrendingCommunities(@Param("num") Long cid);
}
