package com.rosatel.Service;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rosatel.DTO.LoginCredentials;
import com.rosatel.Model.Cliente;
import com.rosatel.Repository.ClienteRepository;

@Service
public class AuthenticationService {

    private final ClienteRepository repoCliente;
    private final PasswordEncoder passwordEncoder;
    private final TokenService token;

    public AuthenticationService(
            ClienteRepository repoCliente,
            PasswordEncoder passwordEncoder,
            TokenService token) {
        this.repoCliente = repoCliente;
        this.passwordEncoder = passwordEncoder;
        this.token = token;
    }

    public String login(LoginCredentials credentials) {
        Cliente cliente = repoCliente.findByCorreo(credentials.getCorreo())
                .orElseThrow(() -> new BadCredentialsException("Correo o contraseña incorrectos"));

        if (!passwordEncoder.matches(credentials.getContraseña(), cliente.getContraseña())) {
            throw new BadCredentialsException("Correo o contraseña incorrectos");
        }
        return token.generate(cliente);
    }

    public String register(Cliente newUser) {
        if (repoCliente.findByCorreo(newUser.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está en uso"); // O una excepción personalizada
        }

        newUser.setContraseña(passwordEncoder.encode(newUser.getContraseña()));

        Cliente cliente = repoCliente.save(newUser);

        return token.generate(cliente);
    }

    public void logout(String tokenSesion) {
        if (token.validate(tokenSesion)) {
            token.remove(tokenSesion);
        } else {
            throw new BadCredentialsException("Token inválido");
        }
    }
}
