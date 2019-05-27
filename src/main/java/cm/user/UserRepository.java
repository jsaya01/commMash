package cm.user;

import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    @Async
    @Query("SELECT u FROM User u where u.username = :username")
    Future<User> findUserByUsername(@Param("username") String username);
}
