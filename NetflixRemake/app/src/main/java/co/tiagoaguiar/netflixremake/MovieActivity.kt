package co.tiagoaguiar.netflixremake

import android.content.Intent
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import co.tiagoaguiar.netflixremake.adapters.MovieAdapter
import co.tiagoaguiar.netflixremake.databinding.ActivityMovieBinding
import co.tiagoaguiar.netflixremake.interfaces.OnClickForAdapter
import co.tiagoaguiar.netflixremake.model.Movie

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private val movies = mutableListOf<Movie>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.movieToolbar)

        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            setDisplayHomeAsUpEnabled(true)
            title = null
        }

        val layerDrawable = ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, ContextCompat.getDrawable(this, R.drawable.movie_4))

        binding.movieImg.setImageDrawable(layerDrawable)
        binding.movieTxtTitle.text = "Batman Begins"
        binding.movieTxtDesc.text = "Essa é a descrição do filme Batman Begins"
        binding.movieTxtCast.text = getString(R.string.cast, "Ator A, Ator B, Ator C, Ator D, Ator E")

        for (i in 0 until 12) {
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

        binding.movieRvSimilar.apply {
            layoutManager = GridLayoutManager(this@MovieActivity, 3)
            adapter = MovieAdapter(movies, R.layout.movie_item_similar, object: OnClickForAdapter {
                override fun onClick(itemPosition: Int) {
                    startActivity(Intent(this@MovieActivity, MovieActivity::class.java))
                }
            })
        }
    }
}