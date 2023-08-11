package co.tiagoaguiar.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.adapters.CategoryAdapter
import co.tiagoaguiar.netflixremake.databinding.ActivityMainBinding
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movies = mutableListOf<Movie>()
        val categories = mutableListOf<Category>()

        for (j in 0 until 5) {
            for (i in 0 until 10) {
                if (i % 2 == 0) {
                    movies.add(
                        Movie(R.drawable.movie)
                    )
                } else {
                    movies.add(
                        Movie(R.drawable.movie_4)
                    )
                }
            }
            categories.add(
                Category("Categoria $j", movies)
            )
        }

        categories.forEach {
            Log.i("Teste de for", it.toString())
        }

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = CategoryAdapter(categories)
    }
}