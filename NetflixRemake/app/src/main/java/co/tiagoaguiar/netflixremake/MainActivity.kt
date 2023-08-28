package co.tiagoaguiar.netflixremake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.adapters.CategoryAdapter
import co.tiagoaguiar.netflixremake.adapters.MovieAdapter
import co.tiagoaguiar.netflixremake.databinding.ActivityMainBinding
import co.tiagoaguiar.netflixremake.databinding.CategoryItemBinding
import co.tiagoaguiar.netflixremake.interfaces.OnClickForAdapter
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.util.CategoryTask

class MainActivity : AppCompatActivity(), CategoryTask.CallBack {

    private lateinit var binding: ActivityMainBinding
    private val movies = mutableListOf<Movie>()
    private val categories = mutableListOf<Category>()
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        adapter = CategoryAdapter(categories, object: OnClickForAdapter {
            override fun onClick(itemPosition: Int) {
                startActivity(Intent(this@MainActivity, MovieActivity::class.java))
            }
        })
        binding.rvMain.adapter = adapter

        CategoryTask(this).execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=4a8c46ef-d5de-41e3-89ad-4e427c7fff3c")
    }

    override fun onResute(categories: List<Category>) {
        this.categories.clear()
        this.categories.addAll(categories)
        adapter.notifyDataSetChanged()
        binding.progressMain.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        binding.progressMain.visibility = View.GONE
    }

    override fun onPreExecute() {
        binding.progressMain.visibility = View.VISIBLE
    }
}