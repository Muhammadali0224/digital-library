package uz.pdp.digitallibrary.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.digitallibrary.payload.SignInDTO;
import uz.pdp.digitallibrary.payload.SignUpDTO;
import uz.pdp.digitallibrary.service.AuthService;
import uz.pdp.digitallibrary.util.ApiResult;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication API", description = "Authentication CRUD API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResult<String>> signIn(@Valid @RequestBody SignInDTO signInDTO){

        return ResponseEntity.ok(authService.signIn(signInDTO));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResult<String>> signUp (@Valid @RequestBody SignUpDTO signUpDTO){

         return ResponseEntity.ok(authService.signUp(signUpDTO));
    }


}
