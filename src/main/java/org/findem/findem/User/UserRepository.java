package org.findem.findem.User;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Async
    @Query("SELECT u FROM User u where u.email = :email")
    Future<User> findUserByUsername(@Param("email") String email);
}
