package co.tiagoaguiar.netflixremake.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class DownloadImageTask(private val callBack: CallBack) {

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    interface CallBack {
        fun onResute(bitmap: Bitmap)
    }

    fun execute(url: String) {
        executor.execute {
            var urlConnection: HttpsURLConnection? = null
            var stream: InputStream? = null

            try {
                val requestUrl = URL(url) //abrir uma URL
                urlConnection = requestUrl.openConnection() as HttpsURLConnection
                urlConnection.readTimeout = 2000 //tempo de leitura
                urlConnection.connectTimeout = 2000 //tempo de conexão

                val statusCode: Int = urlConnection.responseCode //HTTP response code

                if (statusCode > 400) {
                    throw IOException("Erro na comunicação do servidor")
                }

                stream = urlConnection.inputStream //sequencia de bytes
                val bitmap = BitmapFactory.decodeStream(stream)

                handler.post {
                    callBack.onResute(bitmap)
                }

            } catch (e: IOException) { }
            finally {
                urlConnection?.disconnect()
                stream?.close()
            }
        }
    }
}