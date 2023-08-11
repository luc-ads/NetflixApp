package co.tiagoaguiar.netflixremake.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.databinding.CategoryItemBinding
import co.tiagoaguiar.netflixremake.databinding.MovieItemBinding
import co.tiagoaguiar.netflixremake.model.Movie

class MovieAdapter(
    private val listMovie: List<Movie>
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {

        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(itemBinding: MovieItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        private val textMovieName: ImageView = itemBinding.movieTitle

        fun bind(itemMovie: Movie) {
            textMovieName.setImageResource(itemMovie.coverUrl)
        }
    }
}