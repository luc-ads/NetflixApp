package co.tiagoaguiar.netflixremake.model

data class OpenMovie(
    val id: Int,
    val title: String,
    val desc: String,
    val cast: String,
    val coverUrl: String,
    val movies: List<Movie>
)
