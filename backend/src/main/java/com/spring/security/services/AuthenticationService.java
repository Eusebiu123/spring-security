package com.spring.security.services;

import java.util.HashSet;
import java.util.Set;

import com.spring.security.models.ApplicationUser;
import com.spring.security.models.LoginResponseDTO;
import com.spring.security.repository.RoleRepository;
import com.spring.security.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.security.models.Role;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
    }

//    public LoginResponseDTO loginUser(String username, String password){
//
//        try{
//            Authentication auth = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            String token = tokenService.generateJwt(auth);
//
////            return new ResponseEntity<String>(HttpStatus.OK);
//
//            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
//
//        } catch(AuthenticationException e){
//            return new LoginResponseDTO(null,"");
////            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//        }
//    }
//    public Response loginUser(String username, String password){
//
//        try{
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            String token = tokenService.generateJwt(auth);
//            LoginResponseDTO response = new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
//
//            return Response.status(200).entity(response).build();
//
//
//        } catch(AuthenticationException e){
//            return Response.status(401).entity("Username or Password are incorrect!").build();
//        }
//    }
public ResponseEntity<String> loginUser(String username, String password){

    try{
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = tokenService.generateJwt(auth);

            return new ResponseEntity<String>(HttpStatus.OK);

//        return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

    } catch(AuthenticationException e){

            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}

}
