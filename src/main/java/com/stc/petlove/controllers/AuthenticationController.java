package com.stc.petlove.controllers;

import com.stc.petlove.dtos.AccountDto;
import com.stc.petlove.dtos.TokenDetails;
import com.stc.petlove.exceptions.InvalidException;
import com.stc.petlove.exceptions.UserNotFoundAuthenticationException;
import com.stc.petlove.securities.CustomUserDetailsService;
import com.stc.petlove.securities.JwtTokenUtils;
import com.stc.petlove.securities.JwtUserDetails;
import com.stc.petlove.securities.UserAuthenticationToken;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/rest/login")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtTokenUtils jwtTokenUtils;

    @Value("${google.verifyUrl}")
    private String googleVerifyUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService,
                                    JwtTokenUtils jwtTokenUtils) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @ApiOperation(value = "login form (username, password), avatar null")
    @PostMapping
    public ResponseEntity<TokenDetails> login(@Valid @RequestBody AccountDto dto) {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken(
                dto.getUsername(),
                dto.getPassword(),
                true
        );
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (UserNotFoundAuthenticationException | BadCredentialsException ex) {
            throw new InvalidException(ex.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        final JwtUserDetails userDetails = customUserDetailsService
                .loadUserByUsername(dto.getUsername());
        final TokenDetails result = jwtTokenUtils.getTokenDetails(userDetails, null);
        log.info(String.format("User %s login at %s", dto.getUsername(), new Date()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "login google (Access Token), lấy avatar google")
    @PostMapping("/google")
    public ResponseEntity<TokenDetails> loginGoogle(@RequestHeader(name = "accessToken") String accessToken) {
        String urlRequest = googleVerifyUrl + accessToken;
        String email;
        String avatar;
        try {
            ResponseEntity<HashMap> responseEntity = restTemplate.exchange(urlRequest, HttpMethod.GET, null, HashMap.class);
            HashMap<String, String> map = responseEntity.getBody();
            email = map.get("email");
            avatar = map.get("picture");
        } catch (Exception ex) {
            throw new InvalidException("Token không hợp lệ");
        }
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken(
                email,
                null,
                false
        );
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (UserNotFoundAuthenticationException | BadCredentialsException ex) {
            throw new InvalidException(ex.getMessage());
        }
        final JwtUserDetails userDetails = customUserDetailsService
                .loadUserByUsername(email);
        final TokenDetails result = jwtTokenUtils.getTokenDetails(userDetails, avatar);
        log.info(String.format("User %s login via google at %s", email, new Date()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sayHello(Principal principal) {
        return new ResponseEntity<>(String.format("Hello %s", principal.getName()), HttpStatus.OK);
    }

}
