package co.tiagoaguiar.netflixremake.util

import android.util.Log
import java.io.IOException
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

                //Forma 1: simples e rápida
                val stream = urlConnection.inputStream //sequencia de bytes
                val jsonAsString = stream.bufferedReader().use { it.readText() } //transformação de bytes -> String (Recebe uma sequencia de binários como 0 e 1, e transforma em caracteres legíveis
                Log.i("jsonAsString", jsonAsString)


            }
        } catch (e: IOException) {
            Log.e("IOException", e.message ?: "erro desconhecido", e)
        }
    }
}