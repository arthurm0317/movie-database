import arthur.movie_database.service.TMDbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    private final TMDbService tmdbService;

    public TesteController(TMDbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/filme")
    public ResponseEntity<String> buscarFilme(@RequestParam String titulo) {
        String resultado = tmdbService.buscarFilmePorTitulo(titulo);
        return ResponseEntity.ok(resultado);
    }
}
