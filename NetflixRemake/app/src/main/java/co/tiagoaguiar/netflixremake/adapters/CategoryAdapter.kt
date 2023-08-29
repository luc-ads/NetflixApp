package co.tiagoaguiar.netflixremake.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.R
import co.tiagoaguiar.netflixremake.databinding.CategoryItemBinding
import co.tiagoaguiar.netflixremake.interfaces.OnClickForAdapter
import co.tiagoaguiar.netflixremake.model.Category

class CategoryAdapter(
    private val listMovie: List<Category>,
    private val onClickListener: (Int) -> Unit
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {

        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.bind(listMovie[position], position)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class CategoryViewHolder(itemBinding: CategoryItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        private val textCategoryName: TextView = itemBinding.txtTitle
        private val rvMovies: RecyclerView = itemBinding.rvMovies

        fun bind(itemCategory: Category, position: Int) {
            textCategoryName.text = itemCategory.name
            rvMovies.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            rvMovies.adapter = MovieAdapter(itemCategory.movies, R.layout.movie_item, onClickListener)
        }
    }
}