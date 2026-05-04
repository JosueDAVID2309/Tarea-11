package com.rosatel.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rosatel.DTO.ApiResponse;
import com.rosatel.Service.AuthenticationService;
import com.rosatel.DTO.LoginCredentials;
import com.rosatel.Model.Cliente;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService service;
    public AuthController(AuthenticationService service){
        this.service = service;
    }
    
    @PostMapping("/login")
    public ApiResponse<String> iniciarSesion(@RequestBody LoginCredentials credentials){
        String token = service.login(credentials);

        return new ApiResponse<String>(true, token, "Usuario inicio sesión correctamente");
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> registrarse(@RequestBody Cliente newCliente){
        String token = service.register(newCliente);
        return new ApiResponse<String>(true, token, "Se registro un nuevo usuario");
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> cerrarSesion(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, null, "Token no proporcionado"));
        }

        String token = authHeader.substring(7);
        service.logout(token);
        return ResponseEntity.ok(new ApiResponse<>(true, null, "Sesión cerrada correctamente"));
    }

}
