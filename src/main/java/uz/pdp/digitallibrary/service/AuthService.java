package uz.pdp.digitallibrary.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.pdp.digitallibrary.payload.SignInDTO;
import uz.pdp.digitallibrary.payload.SignUpDTO;
import uz.pdp.digitallibrary.util.ApiResult;

public interface AuthService extends UserDetailsService {


    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    ApiResult<String> signIn(SignInDTO signInDTO);

    ApiResult<String> signUp(SignUpDTO signUpDTO);
}
