package arthur.movie_database.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
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
    private Integer releaseYear;
    @ElementCollection
    private List<String> genres;
    @Lob
    private String description;
    private String imageUrl;
    @Column(unique = true)
    private Long tmdbId;


    private Movies() {

    }

    public Movies(String name, Integer year, String description, String imageUrl, Long tmdbId) {
        this.name = name;
        this.releaseYear = year;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tmdbId = tmdbId;
    }

    public Movies(Integer id, String name, Integer year, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.releaseYear = year;
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return Objects.equals(tmdbId, movies.tmdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tmdbId);
    }
}
