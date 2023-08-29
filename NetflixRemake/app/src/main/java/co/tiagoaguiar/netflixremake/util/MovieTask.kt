package co.tiagoaguiar.netflixremake.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import co.tiagoaguiar.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.model.OpenMovie
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class MovieTask(private val callBack: MovieTask.CallBack) {

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    interface CallBack {

        fun onResult(parseMovie: OpenMovie)
        fun onFailure(message: String)
        fun onPreExecute()
    }


    fun execute(url: String) {

        executor.execute {
            var urlConnection: HttpsURLConnection? = null
            var stream: InputStream? = null

            try {
                val requestUrl = URL(url)

                urlConnection = requestUrl.openConnection() as HttpsURLConnection
                urlConnection.readTimeout = 2000 //tempo de leitura
                urlConnection.connectTimeout = 2000 //tempo de conexão

                val statusCode = urlConnection.responseCode

                if (statusCode > 400) {
                    throw IOException("Erro na comunicação do servidor")
                }

                stream = urlConnection.inputStream
                val jsonString = stream.bufferedReader().use { it.readText() }

                Log.i("jsonString Movies", jsonString)

                val parseMovie = toMovie(jsonString)

                handler.post {
                    callBack.onResult(parseMovie)
                }


            } catch (e: IOException) {
                Log.i("jsonString Movies", e.message.toString())
            } finally {
                urlConnection?.disconnect()
                stream?.close()
            }
        }
    }

    private fun toMovie(jsonString: String): OpenMovie {
        val jsonRoot = JSONObject(jsonString)
        val jsonMovies = jsonRoot.getJSONArray("movie")
        val moviesList = mutableListOf<Movie>()

        for (i in 0 until jsonMovies.length()) {
            val movie = jsonMovies.getJSONObject(i)
            moviesList.add(Movie(
                id = movie.getInt("id"),
                coverUrl = movie.getString("cover_url")
            ))
        }

        return OpenMovie(
            id = jsonRoot.getInt("id"),
            title = jsonRoot.getString("title"),
            desc = jsonRoot.getString("desc"),
            cast = jsonRoot.getString("cast"),
            coverUrl = jsonRoot.getString("cover_url"),
            movies = moviesList
        )
    }
}