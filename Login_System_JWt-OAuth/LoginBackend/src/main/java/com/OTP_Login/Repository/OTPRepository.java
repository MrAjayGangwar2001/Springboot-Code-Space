package com.OTP_Login.Repository;

import com.OTP_Login.Dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.OTP_Login.Model.User;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
