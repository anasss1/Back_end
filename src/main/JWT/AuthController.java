import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Endpoint pour se connecter et recevoir un token JWT
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Vérifier l'utilisateur (par exemple, via un service de validation)
        if ("user".equals(username) && "password".equals(password)) {
            // Générer un token JWT
            return jwtTokenProvider.generateToken(username);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
