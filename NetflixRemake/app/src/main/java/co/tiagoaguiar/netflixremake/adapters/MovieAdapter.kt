package co.tiagoaguiar.netflixremake.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.R
import co.tiagoaguiar.netflixremake.databinding.CategoryItemBinding
import co.tiagoaguiar.netflixremake.databinding.MovieItemBinding
import co.tiagoaguiar.netflixremake.interfaces.OnClickForAdapter
import co.tiagoaguiar.netflixremake.model.Movie

class MovieAdapter(
    private val listMovie: List<Movie>,
    @LayoutRes private val layoutId: Int,
    private val onClickItem: OnClickForAdapter
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(listMovie[position], position)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textMovieName: ImageView = itemView.findViewById(R.id.movie_title)

        fun bind(itemMovie: Movie, position: Int) {
            //textMovieName.setImageResource(itemMovie.coverUrl)
            itemView.setOnClickListener {
                onClickItem.onClick(position)
            }
        }
    }
}