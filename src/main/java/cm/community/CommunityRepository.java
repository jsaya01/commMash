package cm.community;

import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    @Async
    @Query("SELECT c FROM Community c where c.name = :name")
    Future<Community> findCommunityByName(@Param("name") String name);
    
    @Query("SELECT c FROM Community c where c.cid = :cid")
    Community findCommunityByCid(@Param("cid") Long cid);
}
