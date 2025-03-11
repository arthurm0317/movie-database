package arthur.movie_database.DTO;

import arthur.movie_database.entities.Movies;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

public class MoviesDTO {
    private Integer id;
    @NotBlank
    private String name;
    private List<String> genres;
    private Integer releaseYear;
    private String description;
    private String imageUrl;
    private Double rate;

    public MoviesDTO() {
    }

    public MoviesDTO(Integer id, String name, List<String> genre, String description, String imageUrl, Double rate) {
        this.id = id;
        this.name = name;
        this.genres = genre;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rate = rate;
    }

    public MoviesDTO(Integer id, String name, List<String> genre, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.genres = genre;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public MoviesDTO(Movies movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.releaseYear = movie.getReleaseYear();
        this.description = movie.getDescription();
        this.genres = movie.getGenres();
        this.imageUrl = movie.getImageUrl();
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MoviesDTO moviesDTO = (MoviesDTO) o;
        return Objects.equals(id, moviesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
