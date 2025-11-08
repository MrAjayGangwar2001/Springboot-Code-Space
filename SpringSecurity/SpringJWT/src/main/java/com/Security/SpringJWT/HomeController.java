package com.Security.SpringJWT;

import org.springframework.web.bind.annotation.RestController;

import com.Security.SpringJWT.Dto.LoginDto;
import com.Security.SpringJWT.Model.UserModel;
import com.Security.SpringJWT.Service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



// @RestController
@Controller
@RequiredArgsConstructor
public class HomeController {


    private final UserService userv;

    @GetMapping("/")
    public String HomePage() {
        // return "🏠 Welcome to the Public Home Page — No Login Needed!";
        return """
                <html>
                            <head>
                            <title>TradeFlow App</title>
                            <style>
                                body {
                                background: linear-gradient(120deg, #1a2a6c, #b21f1f, #fdbb2d);
                                background-size: 600% 600%;
                                animation: gradientBG 8s ease infinite;
                                color: #fff;
                                font-family: "Poppins", sans-serif;
                                display: flex;
                                flex-direction: column;
                                align-items: center;
                                justify-content: center;
                                height: 100vh;
                                margin: 0;
                                text-align: center;
                                }

                                @keyframes gradientBG {
                                0% {
                                background-position: 0% 50%;
                                }

                                50% {
                                background-position: 100% 50%;
                                }

                                100% {
                                background-position: 0% 50%;
                                }
                            }

                                h1 {
                                font-size: 3em;
                                margin: 0;
                                }

                                p {
                                font-size: 1.2em;
                                margin-top: 10px;
                                opacity: 0.5;
                                }

                                .status {
                                margin-top: 30px;
                                background: rgba(255, 255, 255, 0.2);
                                display: inline-block;
                                padding: 10px 25px;
                                border-radius: 10px;
                                }
                                .nav{
                                display:flex;
                                }
                                .useradmin{
                                margin: 5px;
                                padding: 10px;
                                cursor:pointer;
                                border-radius: 10px;
                                }
                            </style>
                        </head>

                        <body>
                            <div class="nav">
                                <form action="/user" method="post">
                                    <button class="useradmin" type="submit"><b>User</b></button>
                                </form>
                                <form action="/admin" method="post">
                                    <button class="useradmin" type="submit"><b>Admin</b></button>
                                </form>
                            </div>
                            <h1>🚀 TradeFlow Project is Live!</h1>
                            <p>Server is running smoothly on port <b>8080</b></p>
                            <p>You are on Home Page, No Login Needed.</p>
                            <p>This project is Only Backend Part</p>
                            <div class="status">✅ Status: <b>UP</b></div>

                        </body>
                    </html>
                """;
    }
    
    @GetMapping("/user")
    @ResponseBody
    public String user() {
        System.out.println("You are on User Dashboard");
        return "You are on User Dashboard";
                // <html>
                //             <head>
                //             <title>TradeFlow App</title>
                //             <style>
                //                 body {
                //                 background: linear-gradient(120deg, #1a2a6c, #b21f1f, #fdbb2d);
                //                 background-size: 600% 600%;
                //                 animation: gradientBG 8s ease infinite;
                //                 color: #fff;
                //                 font-family: "Poppins", sans-serif;
                //                 display: flex;
                //                 flex-direction: column;
                //                 align-items: center;
                //                 justify-content: center;
                //                 height: 100vh;
                //                 margin: 0;
                //                 text-align: center;
                //                 }

                //                 @keyframes gradientBG {
                //                 0% {
                //                 background-position: 0% 50%;
                //                 }

                //                 50% {
                //                 background-position: 100% 50%;
                //                 }

                //                 100% {
                //                 background-position: 0% 50%;
                //                 }
                //             }

                //                 h1 {
                //                 font-size: 3em;
                //                 margin: 0;
                //                 }

                //                 p {
                //                 font-size: 1.2em;
                //                 margin-top: 10px;
                //                 opacity: 0.5;
                //                 }

                //                 .status {
                //                 margin-top: 30px;
                //                 background: rgba(255, 255, 255, 0.2);
                //                 display: inline-block;
                //                 padding: 10px 25px;
                //                 border-radius: 10px;
                //                 }
                //                 .nav{
                //                 display:flex;
                //                 }
                //                 .useradmin{
                //                 margin: 5px;
                //                 padding: 10px;
                //                 cursor:pointer;
                //                 border-radius: 10px;
                //                 }
                //             </style>
                //         </head>

                //         <body>
                //             <div class="nav">
                //                 <form action="/user" method="post">
                //                     <button class="useradmin" type="submit"><b>User</b></button>
                //                 </form>
                //                 <form action="/admin" method="post">
                //                     <button class="useradmin" type="submit"><b>Admin</b></button>
                //                 </form>
                //             </div>
                //             <h1>🚀 TradeFlow Project is Live!</h1>
                //             <p>Server is running smoothly on port <b>2907</b></p>
                //             <p>You are on User Dashboard.</p>
                //             <p>This project is Only Backend Part</p>
                //             <div class="status">✅ Status: <b>UP</b></div>

                //         </body>
                //     </html>
                // """;
    }


    @GetMapping("/admin")
    @ResponseBody
    
    public String Admin() {
        return """
                <html>
                            <head>
                            <title>TradeFlow App</title>
                            <style>
                                body {
                                background: linear-gradient(120deg, #1a2a6c, #b21f1f, #fdbb2d);
                                background-size: 600% 600%;
                                animation: gradientBG 8s ease infinite;
                                color: #fff;
                                font-family: "Poppins", sans-serif;
                                display: flex;
                                flex-direction: column;
                                align-items: center;
                                justify-content: center;
                                height: 100vh;
                                margin: 0;
                                text-align: center;
                                }

                                @keyframes gradientBG {
                                0% {
                                background-position: 0% 50%;
                                }

                                50% {
                                background-position: 100% 50%;
                                }

                                100% {
                                background-position: 0% 50%;
                                }
                            }

                                h1 {
                                font-size: 3em;
                                margin: 0;
                                }

                                p {
                                font-size: 1.2em;
                                margin-top: 10px;
                                opacity: 0.5;
                                }

                                .status {
                                margin-top: 30px;
                                background: rgba(255, 255, 255, 0.2);
                                display: inline-block;
                                padding: 10px 25px;
                                border-radius: 10px;
                                }
                                .nav{
                                display:flex;
                                }
                                .useradmin{
                                margin: 5px;
                                padding: 10px;
                                cursor:pointer;
                                border-radius: 10px;
                                }
                            </style>
                        </head>

                        <body>
                            <div class="nav">
                                <form action="/user" method="post">
                                    <button class="useradmin" type="submit"><b>User</b></button>
                                </form>
                                <form action="/admin" method="post">
                                    <button class="useradmin" type="submit"><b>Admin</b></button>
                                </form>
                            </div>
                            <h1>🚀 TradeFlow Project is Live!</h1>
                            <p>Server is running smoothly on port <b>2907</b></p>
                            <p>You are on Admin Page.</p>
                            <p>This project is Only Backend Part</p>
                            <div class="status">✅ Status: <b>UP</b></div>

                        </body>
                    </html>
                """;
    }
    
    
    @PostMapping("/register")
    @ResponseBody
    public String RegisterUser(@RequestBody UserModel user) {
        return userv.CreateUser(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public String UserLogin(@RequestBody LoginDto login) {
        
        return userv.LoginUser(login);
    }
    
    @GetMapping("/verify")
    @ResponseBody
    public String VerifyData(String authHeader) {
        return "Data has been Verify";
    }
    
    
}
