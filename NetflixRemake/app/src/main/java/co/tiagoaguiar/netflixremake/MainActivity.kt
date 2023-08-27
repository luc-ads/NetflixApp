package co.tiagoaguiar.netflixremake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.adapters.CategoryAdapter
import co.tiagoaguiar.netflixremake.databinding.ActivityMainBinding
import co.tiagoaguiar.netflixremake.databinding.CategoryItemBinding
import co.tiagoaguiar.netflixremake.interfaces.OnClickForAdapter
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.util.CategoryTask

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movies = mutableListOf<Movie>()
        val categories = mutableListOf<Category>()

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = CategoryAdapter(categories, object: OnClickForAdapter {
            override fun onClick(itemPosition: Int) {
                startActivity(Intent(this@MainActivity, MovieActivity::class.java))
            }
        })

        CategoryTask().execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=4a8c46ef-d5de-41e3-89ad-4e427c7fff3c")
    }
}