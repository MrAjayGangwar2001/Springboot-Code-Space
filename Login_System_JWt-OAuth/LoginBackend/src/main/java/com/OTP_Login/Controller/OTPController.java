package com.OTP_Login.Controller;

//import com.OTP_Login.JWT.JwtUtil;

import com.OTP_Login.Dto.UserDto;
import com.OTP_Login.JWT.JwtUtil;
import com.OTP_Login.Model.User;
import com.OTP_Login.Service.OTPService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173",
        allowCredentials = "true")
@RequestMapping("api/email")
public class OTPController {

    @Autowired
    private OTPService otpService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/otp")
    public String SendOTP(@RequestBody UserDto dto) {
        otpService.SendOTP(dto.getEmail(), dto);
        return "Otp Sent Successfully ";
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody UserDto dto) {

        boolean verified = otpService.VerifyOTP(dto.getEmail(), dto.getOtp());

        if (!verified) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid OTP");
        }

        String token = jwtUtil.generateToken(dto.getEmail());

        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "email", dto.getEmail(),
                        "loginType", "OTP"
                )
        );
    }

    @GetMapping("/oauth2/success")
//    public ResponseEntity<?> oauthSuccess(
    public void oauthSuccess(
            @AuthenticationPrincipal OAuth2User user, HttpServletResponse res) throws IOException {

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        String token = jwtUtil.generateToken(email);

        // 👉 Yahin JWT generate karoge (next step)
//        return ResponseEntity.ok(Map.of(
//                "token", token,
//                "email", email,
//                "name", name,
//                "loginType", "GOOGLE"
//        ));
        // FRONTEND redirect with token
        String redirectUrl = "http://localhost:5173/oauth-success"
                + "?token=" + token
                + "&email=" + email
                + "&name=" + name;

        res.sendRedirect(redirectUrl);
    }


//    @PostMapping("/verify")
//    public String VerifyOTP(@RequestBody UserDto otp) {
//    return otpService.VerifyOTP(otp.getEmail(), otp.getOtp()) ? "Login Successfully " : "Invalid or Expired OTP ";
//    }


    @GetMapping("/home")
    public String Home() {
        return "You Are on Home Page";
    }

}
