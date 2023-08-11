package co.tiagoaguiar.netflixremake.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.databinding.MovieItemBinding
import co.tiagoaguiar.netflixremake.model.Movie

class MainAdapter(
    private val listMovie: List<Movie>
): RecyclerView.Adapter<MainAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.MovieViewHolder {

        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MovieViewHolder, position: Int) {
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