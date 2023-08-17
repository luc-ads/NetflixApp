package co.tiagoaguiar.netflixremake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.tiagoaguiar.netflixremake.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.movieToolbar)

        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            setDisplayHomeAsUpEnabled(true)
            title = "Teste"

        }

    }
}