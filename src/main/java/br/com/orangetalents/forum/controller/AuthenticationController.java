package br.com.orangetalents.forum.controller;


import br.com.orangetalents.forum.config.security.TokenService;
import br.com.orangetalents.forum.controller.dto.TokenDTO;
import br.com.orangetalents.forum.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<TokenDTO> toAuthenticate(@RequestBody @Valid LoginForm form){

        UsernamePasswordAuthenticationToken loginData = form.converter();

        try {
            Authentication authenticate = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authenticate);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();

        }

    }
}
