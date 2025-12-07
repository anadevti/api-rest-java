package org.aninha.apirestjava.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    @Modifying
    @Query("update User u SET u.name = :name WHERE u.uuid = :uuid") //jpql
   void  updateName(@Param("uuid") UUID uuid, @Param("name") String name);
}
