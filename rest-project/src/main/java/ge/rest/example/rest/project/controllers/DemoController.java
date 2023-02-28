/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import ge.rest.example.rest.project.domain.user.User;
import ge.rest.example.rest.project.domain.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vako
 */
@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  private final UserRepository userRepository;

    public DemoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

  @GetMapping("/myUser")
  public User findUser (@RequestParam String email){
      User user =userRepository.findByEmail(email).get();
      if(user!= null) {
       ResponseEntity.ok("exists");
       return user;
      }else  {
       ResponseEntity.ok("no");
      }
      return null;
  }
 
}