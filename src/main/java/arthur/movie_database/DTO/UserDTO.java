package arthur.movie_database.DTO;

import arthur.movie_database.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private Integer id;
    @NotBlank
    @Size(min = 3, max=40, message = "O nome deve ter entre 3 e 40 caracteres")
    private String username;
    private String email;
    private String password;
    private String imageUrl;

    public UserDTO() {

    }

    public UserDTO(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public UserDTO(Integer id, String username, String email, String imageUrl, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.password = password;
    }


    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.imageUrl = user.getImage(); // Se precisar
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
