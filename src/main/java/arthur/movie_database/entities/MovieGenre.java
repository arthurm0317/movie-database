package arthur.movie_database.entities;

public enum MovieGenre {
    ACTION(28, "Ação"),
    ADVENTURE(12, "Aventura"),
    ANIMATION(16, "Animação"),
    COMEDY(35, "Comédia"),
    CRIME(80, "Crime"),
    DOCUMENTARY(99, "Documentário"),
    DRAMA(18, "Drama"),
    FANTASY(14, "Fantasia"),
    HORROR(27, "Terror"),
    ROMANCE(10749, "Romance"),
    SCI_FI(878, "Ficção Científica"),
    THRILLER(53, "Suspense");

    private final int id;
    private final String name;

    MovieGenre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getGenreNameById(int id) {
        for (MovieGenre genre : values()) {
            if (genre.getId() == id) {
                return genre.getName();
            }
        }
        return "Desconhecido";
    }
}