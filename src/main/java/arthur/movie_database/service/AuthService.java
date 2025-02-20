package arthur.movie_database.service;

import arthur.movie_database.security.jwt.AcessDTO;
import arthur.movie_database.security.jwt.AuthenticationDTO;
import arthur.movie_database.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AcessDTO login(AuthenticationDTO authDTO){
        try {
            //Cria mecanismo de credencial para spring
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());

            //Prepara mecanismo para autenticação
            Authentication authentication = authenticationManager.authenticate(userAuth);

            //Busca usuario logado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            AcessDTO acessDTO = new AcessDTO(token);
            return acessDTO;
        }catch(BadCredentialsException e){
            System.out.println("Erro no login");
        }
        return null;
    }
}
