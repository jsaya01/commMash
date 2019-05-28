package cm.matches;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchesRepository extends JpaRepository<Matches, Long>{
	
	@Query("SELECT uid2, tstamp FROM Matches m where m.uid1 = :uid")
    List<Object[]> findMatchesFromUid1(@Param("uid") Long uid);
	
	@Query("SELECT uid1, tstamp FROM Matches m where m.uid2 = :uid")
	List<Object[]> findMatchesFromUid2(@Param("uid") Long uid);
}
