    package com.example.springbootfirst.controllers;

    import com.example.springbootfirst.jwt.JwtResponseDto;
    import com.example.springbootfirst.models.LoginDetailsDto;
    import com.example.springbootfirst.models.UserDetailsDto;
    import com.example.springbootfirst.models.RegisterDetails;
    import com.example.springbootfirst.services.AuthService;
    import com.example.springbootfirst.services.RegisterService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Map;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        @Autowired
        AuthService authService;

        @PostMapping("/register")
        public String addNewUser(@RequestBody UserDetailsDto register) {
            return authService.addNewEmployee(register);
        }

        @Autowired
        private RegisterService registerService;
        @PostMapping("/login")
        public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDetailsDto loginRequest) {
            Map<String, Object> response = registerService.authenticateAndGenerateToken(
                    loginRequest.getUsername(), loginRequest.getPassword());

            return ResponseEntity.ok(response);
        }

        @PutMapping("/update/{id}")
        public String updateUser(@PathVariable int id, @RequestBody RegisterDetails updatedDetails) {
            return authService.updateRegisterById(id, updatedDetails);
        }




    }
