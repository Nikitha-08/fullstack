package com.java.agrofund.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.java.agrofund.dto.Request.ForgotPassworRequest;
import com.java.agrofund.dto.Request.LoginRequest;
import com.java.agrofund.dto.Request.RegisterRequest;
import com.java.agrofund.dto.Response.BasicsResponse;
import com.java.agrofund.dto.Response.LoginResponse;
import com.java.agrofund.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/agrofund/api/v1/authentication")
@RequiredArgsConstructor
@Tag(name="Authentication",description = "is used to authenticated and authorise the user.")
public class AuthenticationController {
private final AuthenticationService authenticationService;
@SuppressWarnings("null")
    @PostMapping("/register")
    @Operation(summary="user registration",description = "for the reg process")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest)
    {
        BasicsResponse<String> response =new BasicsResponse<>();
        try{
            response=authenticationService.register(registerRequest);
            return  new ResponseEntity<>(response,HttpStatus.ACCEPTED);

        }
        catch(Exception e){
            response.setMessage("something went wrong");
            response.setData("");
            return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);


        }

    }
    @PostMapping("/login")
    @Operation(summary="user authentication",description = "for the reg process")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
         BasicsResponse<LoginResponse> response =new BasicsResponse<>();
         try{
            response=authenticationService.login(loginRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);

         }
         catch(Exception e){
            System.out.println(e.getMessage());
            response.setMessage("something went wrong");
            response.setData(LoginResponse.builder().accessToken("").build());
            return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);


         }
    }
    @PatchExchange("/forgot-password")
    @Operation(summary="Reset user pasword",description = "for the reg process")
    public ResponseEntity<?> forgotpassword(@RequestBody ForgotPassworRequest forgotPasswordRequest)
    {
        BasicsResponse<String> response= new BasicsResponse<>();
        try{
            response = authenticationService.forgotPassword(forgotPasswordRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e)
        {
            response.setMessage("something went wrong");
            response.setData("");
            return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
        }
    }
}
