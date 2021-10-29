package com.stsoft.demotaxi.aggregators.yandex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stsoft.demotaxi.aggregators.yandex.entity.AuthToken;


@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    Optional<AuthToken> findById(Long id);
    Optional<AuthToken> findByName(String name);
}
