package ru.razzh.igor.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.razzh.igor.entity.Limit;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByUserId(long userId);
    @Modifying
    @Query(value = "insert into limits (id,limmit,user_id) values (:userId,:limit,:userId)", nativeQuery = true)
    void saveLimit(BigDecimal limit, long userId);

    @Modifying
    @Query(value = "update limits set limmit = :limit where user_id = :userId", nativeQuery = true)
    void updateLimit(BigDecimal limit, long userId);

    @Transactional
    @Modifying
    @Query(value = "update limits set limmit = :limit" , nativeQuery = true)
    void revertToDefaultLimit(BigDecimal limit);

    @Modifying
    @Query(value = "update limits set limmit = limmit + :sum where user_id = :userId", nativeQuery = true)
    void revertLimit(BigDecimal sum, long userId);
}
