package co.tiagoaguiar.netflixremake.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.databinding.MovieItemBinding

class MainAdapter: RecyclerView.Adapter<MainAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.MovieViewHolder {

        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MovieViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 20

    inner class MovieViewHolder(itemBinding: MovieItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    }
}