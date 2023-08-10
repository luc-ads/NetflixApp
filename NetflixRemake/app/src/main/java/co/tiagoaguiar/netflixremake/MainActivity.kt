package co.tiagoaguiar.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.databinding.ActivityMainBinding
import co.tiagoaguiar.netflixremake.databinding.MovieItemBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.adapter = MainAdapter()
    }

    private inner class MainAdapter: RecyclerView.Adapter<MainAdapter.MovieViewHolder>() {
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

        private inner class MovieViewHolder(itemBinding: MovieItemBinding) :RecyclerView.ViewHolder(itemBinding.root) {

        }
    }
}