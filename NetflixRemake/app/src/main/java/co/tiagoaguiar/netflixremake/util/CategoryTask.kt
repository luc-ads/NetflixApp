package co.tiagoaguiar.netflixremake.util

import android.util.Log
import co.tiagoaguiar.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.model.Movie
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask {

    fun execute(url: String) {
        //classe nativa do android para abrir uma nova thread pra ser executada em paralela

        try {
            val executor = Executors.newSingleThreadExecutor()
            executor.execute {
                //Aqui começa a nova Thread
                val requestUrl = URL(url) //abrir uma URL
                val urlConnection = requestUrl.openConnection() as HttpsURLConnection
                urlConnection.readTimeout = 2000 //tempo de leitura
                urlConnection.connectTimeout = 2000 //tempo de conexão

                val statusCode: Int = urlConnection.responseCode //HTTP response code

                if (statusCode > 400) {
                    throw IOException("Erro na comunicação do servidor")
                }

                //Forma 1: simples e rápida {

                val stream = urlConnection.inputStream //sequencia de bytes
                val jsonAsString = stream.bufferedReader().use { it.readText() } //transformação de bytes -> String (Recebe uma sequencia de binários como 0 e 1, e transforma em caracteres legíveis

                val parseCategory = toCategories(jsonAsString)
                Log.i("jsonAsString", parseCategory.toString())
                //}

                //Forma 2: bytes -> string
//                val stream = urlConnection.inputStream //sequencia de bytes
//                val buffer = BufferedInputStream(stream)
//                val jsonAsString2 = toString(buffer)

                //Neste momento o JSON já está preparado para ser parseado em um data class (model)
//                Log.i("jsonAsString", jsonAsString2)

            }
        } catch (e: IOException) {
            Log.i("jsonAsString", e.message.toString())
        }
    }

    private fun toString(stream: InputStream): String {
        val bytes = ByteArray(1024)
        val baos = ByteArrayOutputStream()
        var read: Int
        while (true) {
            read = stream.read(bytes)
            if (read <= 0) {
                break
            }
            baos.write(bytes, 0, read)
        }
        return String(baos.toByteArray())
    }

    private fun toCategories(jsonAsString: String): List<Category> {
        val categories = mutableListOf<Category>()
        val jsonRoot = JSONObject(jsonAsString)
        val jsonCategories = jsonRoot.getJSONArray("category")

        for (i in 0 until jsonCategories.length()) {
            val jsonCategory = jsonCategories.getJSONObject(i)
            val title = jsonCategory.getString("title")
            val jsonMovies = jsonCategory.getJSONArray("movie")

            val movies = mutableListOf<Movie>()
            for (j in 0 until jsonMovies.length()) {
                val jsonMovie = jsonMovies.getJSONObject(j)
                val id = jsonMovie.getInt("id")
                val coverUrl = jsonMovie.getString("cover_url")

                movies.add(
                    Movie(
                        id,
                        coverUrl
                    )
                )
            }
            categories.add(
                Category(
                    title,
                    movies
                )
            )
        }
        return categories
    }
}