package arthur.movie_database.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name ="filmes")
public class Movies implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer relaseYear;
    private String description;
    private String imageUrl;

    private Movies(){

    }

    public Movies(Integer id, String name, Integer year, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.relaseYear = year;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRelaseYear() {
        return relaseYear;
    }

    public void setRelaseYear(Integer relaseYear) {
        this.relaseYear = relaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return Objects.equals(name, movies.name) && Objects.equals(relaseYear, movies.relaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, relaseYear);
    }
}
