package uz.pdp.digitallibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.digitallibrary.enums.Role;
import uz.pdp.digitallibrary.entity.User;
import uz.pdp.digitallibrary.payload.SignInDTO;
import uz.pdp.digitallibrary.payload.SignUpDTO;
import uz.pdp.digitallibrary.repository.UserRepository;
import uz.pdp.digitallibrary.security.JwtProvider;
import uz.pdp.digitallibrary.util.ApiResult;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

    }


    @Override
    public ApiResult<String> signIn(SignInDTO signInDTO) {

        User user = userRepository.findByUsername(signInDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(signInDTO.getUsername()));

        boolean matches = passwordEncoder.matches(signInDTO.getPassword(), user.getPassword());

        if (!matches)
            throw new BadCredentialsException("Invalid username or password");


        String token = jwtProvider.generateToken(user);
        return ApiResult.success(token);

    }

    @Override
    public ApiResult<String> signUp(SignUpDTO signUpDTO) {

        Optional<User> byUsername = userRepository.findByUsername(signUpDTO.getUsername());

        if (byUsername.isPresent())
            throw new BadCredentialsException("Username is already in use");


        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setRole(Role.USER);


        userRepository.save(user);

        return ApiResult.success("User successfully created");
    }
}
