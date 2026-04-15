package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.dto.RegisterDTO;
import ru.job4j.socialmediaapi.dto.SignupRequestDTO;
import ru.job4j.socialmediaapi.model.ERole;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.Role;
import ru.job4j.socialmediaapi.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.socialmediaapi.repository.ImageRepository;
import ru.job4j.socialmediaapi.repository.PostRepository;
import ru.job4j.socialmediaapi.repository.RoleRepository;
import ru.job4j.socialmediaapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

@Service
//@AllArgsConstructor
public class UserServiceDB implements UserService {

    private PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceDB(PasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user) > 0L;
    }

    @Override
    public boolean delete(Long id) {
        return userRepository.delete(id) > 0L;
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public RegisterDTO signUp(SignupRequestDTO signUpRequest) {
        if (Boolean.FALSE.equals(userRepository.findByName(signUpRequest.getName()).isEmpty())
                || Boolean.TRUE.equals(userRepository.findByEmail(signUpRequest.getEmail()))) {
            return new RegisterDTO(HttpStatus.BAD_REQUEST, "Error: Username or Email is already taken!" );
        }

        /* Create new person's account */
        User user = new User(signUpRequest.getName(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        Supplier<RuntimeException> supplier = () -> new RuntimeException("Error: Role is not found.");

        if (strRoles == null) {
            roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(supplier));
                    case "mod" -> roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(supplier));
                    default -> roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new RegisterDTO(HttpStatus.OK, "Person registered successfully!" );
    }
}
