package com.example.sportrates.conroller;

import com.example.sportrates.db_model.Balance;
import com.example.sportrates.db_model.User;
import com.example.sportrates.model.LoginUserCredentials;
import com.example.sportrates.model.RegisterUserCredentials;
import com.example.sportrates.repository.BalanceRepository;
import com.example.sportrates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    @CrossOrigin(origins = "*")
    public ResponseEntity register(
            @RequestBody RegisterUserCredentials userCredentials
    ) {
        User checkUser = userRepository.findByLogin(userCredentials.getLogin());
        if (checkUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            userRepository.save(new User(userCredentials.getUserName(),
                    userCredentials.getLogin(),
                    userCredentials.getPassword(),
                    "client",
                    new Balance(userCredentials.getInitialBalanceSum(), 0)));
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
    }

    @GetMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<LoginUserCredentials> login(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password
    ) {
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            LoginUserCredentials userCredentials = new LoginUserCredentials();
            userCredentials.setId(user.getUserId());
            userCredentials.setStatus(user.getRole());
            return ResponseEntity.ok(userCredentials);
        }
    }
}
