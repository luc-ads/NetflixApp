package co.tiagoaguiar.netflixremake.adapters

import android.graphics.Bitmap
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
import co.tiagoaguiar.netflixremake.util.DownloadImageTask
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val listMovie: List<Movie>,
    @LayoutRes private val layoutId: Int,
    private val onClickItem: ( (Int) -> Unit )? = null
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
//            textMovieName.setImageResource(itemMovie.coverUrl)
//            Picasso.get().load(itemMovie.coverUrl).into(textMovieName)

            DownloadImageTask(object : DownloadImageTask.CallBack {
                override fun onResute(bitmap: Bitmap) {
                    textMovieName.setImageBitmap(bitmap)
                }
            }).execute(itemMovie.coverUrl)

            itemView.setOnClickListener {
                onClickItem?.invoke(itemMovie.id)
            }
        }
    }
}