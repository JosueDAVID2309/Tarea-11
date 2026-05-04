package com.rosatel.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.rosatel.Model.Cliente;

@Service
public class TokenService {
    private final ConcurrentHashMap<String, Cliente> tokenStorage = new ConcurrentHashMap<>();

    public String generate(Cliente cliente){
        String token = UUID.randomUUID().toString();
        tokenStorage.put(token, cliente);
        return token;
    }

    public boolean validate(String token) {
        return tokenStorage.containsKey(token);
    }

    public void remove(String token){
        tokenStorage.remove(token);
    }

    public Cliente obtenerUsuarioDelToken(String token) {
        return tokenStorage.get(token);
    }
}
