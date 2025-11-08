package com.Security.SpringJWT.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Security.SpringJWT.Model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long>{

    Optional<UserModel> findByEmail(String email);

}
