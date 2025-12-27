package com.user.UserCrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.UserCrud.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

}
