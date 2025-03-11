package arthur.movie_database.resources;


import arthur.movie_database.DTO.UserDTO;
import arthur.movie_database.entities.User;
import arthur.movie_database.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController //Controller especifico para requisições http, basicamente gravam um objeto de dominio e não uma exibição. Basicamente usado para definir qual classe vai retornar o obj pedido na API
@RequestMapping(value = "/users") //Coloca o endpoint que vamos utilizar para que cada um tenha um endpoint proprio
public class UserResource {

    @Autowired //Injeção de dependência. Diz que precisamos desse Bean sem ter instanciar manualmente usando o "new"
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> page = userService.findAll(pageable);
        List<UserDTO> list = page.getContent(); // Pega só os itens da página
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}") //Diz que é um get mas necessita do id na URL
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping //Diz que é um post e usa o @RequestBody para garantir que o body será lido
    // Obtém a URI base da requisição atual (ex: "http://localhost:8080/users")
    public ResponseEntity<UserDTO> insertUser(@Valid @RequestBody UserDTO obj){
        UserDTO newObj = userService.insertUser(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);


    } //A URI (uniform Resource Identifier) representa o endereço onde o recurso pode ser encontrado na aplicação
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User obj){
        obj = userService.updateUser(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
