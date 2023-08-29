package co.tiagoaguiar.netflixremake

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import co.tiagoaguiar.netflixremake.adapters.MovieAdapter
import co.tiagoaguiar.netflixremake.databinding.ActivityMovieBinding
import co.tiagoaguiar.netflixremake.interfaces.OnClickForAdapter
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.model.OpenMovie
import co.tiagoaguiar.netflixremake.util.DownloadImageTask
import co.tiagoaguiar.netflixremake.util.MovieTask
import kotlin.system.measureNanoTime

class MovieActivity : AppCompatActivity(), MovieTask.CallBack {

    private lateinit var binding: ActivityMovieBinding
    private var idFilm: Int? = null
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.movieToolbar)

        idFilm = intent.getIntExtra("idMovie", 0)

        if (idFilm != null) {

            //var url = "https://api.tiagoaguiar.co/netflixapp/movie/3?apiKey=4a8c46ef-d5de-41e3-89ad-4e427c7fff3c"
            //var url = "https://api.tiagoaguiar.co/netflixapp/movie/2?apiKey=4a8c46ef-d5de-41e3-89ad-4e427c7fff3c"
            var url = "https://api.tiagoaguiar.co/netflixapp/movie/1?apiKey=4a8c46ef-d5de-41e3-89ad-4e427c7fff3c"

            MovieTask(this).execute(url)
        }

        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            setDisplayHomeAsUpEnabled(true)
            title = null
        }

        val layerDrawable = ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, ContextCompat.getDrawable(this, R.drawable.movie_4))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResult(parseMovie: OpenMovie) {

        DownloadImageTask(object : DownloadImageTask.CallBack {
            override fun onResute(bitmap: Bitmap) {
                binding.movieImg.setImageBitmap(bitmap)
            }
        }).execute(parseMovie.coverUrl)

        with(binding) {
//            movieImg.setImageBitmap(parseMovie.coverUrl)
            movieTxtTitle.text = parseMovie.title
            movieTxtDesc.text =  parseMovie.desc
            movieTxtCast.text = parseMovie.cast
            binding.movieRvSimilar.apply {
                layoutManager = GridLayoutManager(this@MovieActivity, 3)
                adapter = MovieAdapter(parseMovie.movies, R.layout.movie_item_similar)
            }
        }
    }
//        binding.progressMain.visibility = View.VISIBLE

    override fun onFailure(message: String) {
//        binding.progressMain.visibility = View.VISIBLE

    }

    override fun onPreExecute() {

//        binding.progressMain.visibility = View.VISIBLE
    }
}