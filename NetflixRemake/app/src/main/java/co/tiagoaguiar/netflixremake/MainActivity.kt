package co.tiagoaguiar.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.adapters.MainAdapter
import co.tiagoaguiar.netflixremake.databinding.ActivityMainBinding
import co.tiagoaguiar.netflixremake.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filmes = mutableListOf<Movie>()

        for (i in 0 until 20) {
            filmes.add(
                Movie(R.drawable.movie)
            )
        }

        binding.rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rv.adapter = MainAdapter(filmes)
    }
}