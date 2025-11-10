package com.Security.SpringWebToken.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Security.SpringWebToken.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

    Optional<UserModel> findByEmail(String email);
}
