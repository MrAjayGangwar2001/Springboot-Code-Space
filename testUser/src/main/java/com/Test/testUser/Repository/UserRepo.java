package com.Test.testUser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Test.testUser.Model.UserModel;

@Repository
public interface UserRepo extends JpaRepository <UserModel, Integer> {

}
