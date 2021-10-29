package com.stsoft.demotaxi.aggregators.yandex.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stsoft.demotaxi.aggregators.yandex.entity.AuthToken;
import com.stsoft.demotaxi.aggregators.yandex.repository.AuthTokenRepository;


@Service
public class AuthTokenService {
        @Autowired
        private AuthTokenRepository authTokenRepository;

        public List<AuthToken> getAll() {
            return authTokenRepository.findAll();
        }
        public Optional<AuthToken> getClid() {
            return authTokenRepository.findByName("clid");
        }
        public Optional<AuthToken> getApiKey() {
            return authTokenRepository.findByName("apikey");
        }
}
