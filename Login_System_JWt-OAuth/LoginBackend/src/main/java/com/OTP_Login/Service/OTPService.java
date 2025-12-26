package com.OTP_Login.Service;

import com.OTP_Login.Dto.UserDto;
import com.OTP_Login.Exception.OtpCooldownException;
import com.OTP_Login.Exception.OtpExpiredException;
import com.OTP_Login.Model.User;
import com.OTP_Login.OTP.OTPUtility;
import com.OTP_Login.Repository.OTPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OTPService {

    private final OTPRepository otpRepository;
    private final EmailService emailService;

    public ResponseEntity<String> SendOTP(String email, UserDto dto){
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body("Email cannot be empty");
        }


        // Wait Time for Resend OTP
        Optional<User> existingOTP = otpRepository.findByEmail(email);
        if (existingOTP.isPresent()) {
            User ExistingOTP = existingOTP.get();
            if(ExistingOTP.getCreatedAt() != null && ExistingOTP.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(30))){
                throw new OtpCooldownException(
                        "Please wait 30 seconds before requesting OTP again"
                );

            }
            // To delete Existing OTP
            otpRepository.delete(ExistingOTP);
        }


        Long otp = OTPUtility.GenerateOTP();
        User user = new User();
        user.setEmail(email);
        user.setOtp(otp);
        user.setExpiryTime(Instant.now().plusSeconds(120));
        user.setCreatedAt(LocalDateTime.now());
        otpRepository.save(user);
        emailService.Sendotp(email,otp);
        return ResponseEntity.ok("OTP Has been sent");
    }

    public boolean VerifyOTP(String email, Long userOTP){
        User savedOTP = otpRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("can not find Email"));

        // ⛔ EXPIRED OTP
        if (savedOTP.getExpiryTime().isBefore(Instant.now())) {
            otpRepository.delete(savedOTP);
            throw new OtpExpiredException("OTP expired");
        }


        if(savedOTP.getOtp().equals(userOTP)){
        otpRepository.delete(savedOTP);
        return true;
        }
        return false;
    }


//    public User processOAuthUser(OAuth2User oAuth2User) {
//        String email = oAuth2User.getAttribute("email");
//
//        return otpRepository.findByEmail(email)
//                .orElseGet(() -> {
//                    User user = new User();
//                    user.setEmail(email);
//                    user.setProvider("GOOGLE");
//                    return otpRepository.save(user);
//                });
//    }

}
