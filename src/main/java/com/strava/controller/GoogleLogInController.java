//package com.strava.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.strava.service.GoogleLoginService;
//
//@RestController
//@RequestMapping("/google/login")
//public class GoogleLogInController {
//
//    private final GoogleLoginService googleLoginService;
//
//    public GoogleLogInController(GoogleLoginService googleLoginService) {
//        this.googleLoginService = googleLoginService;
//    }
//
//    @GetMapping("/check-email")
//    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
//        return ResponseEntity.ok(googleLoginService.isEmailRegistered(email));
//    }
//
//    @PostMapping("/validate")
//    public ResponseEntity<Boolean> validateCredentials(@RequestParam String email, @RequestParam String password) {
//        return ResponseEntity.ok(googleLoginService.validateCredentials(email, password));
//    }
//    
//    
//    //ARREGLAR
//    @PostMapping("/register")
//    public ResponseEntity<Void> register(@RequestParam String email, @RequestParam String password) {
//        googleLoginService.register(email, password, null, null, null, null, null, null, password);
//        return ResponseEntity.ok().build();
//    }
//}
//
