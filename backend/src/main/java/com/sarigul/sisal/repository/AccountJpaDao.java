package com.sarigul.sisal.repository;

import com.sarigul.sisal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountJpaDao extends JpaRepository<Account,Long> {
    @Query("select e from Account e where e.auth.email=?1")
    Optional<Account> findByEmail(String email);
    long count();
}
