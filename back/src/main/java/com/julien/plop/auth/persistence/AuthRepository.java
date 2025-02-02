package com.julien.plop.auth.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity, String> {

}
