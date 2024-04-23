package apiConnector

import android.util.Log
import androidx.appcompat.app.AlertDialog
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class APIConnect {
    companion object{
        fun get(url: String): String{
            val client= OkHttpClient()
            val req= Request.Builder().url(url).build()
            try{
                val res=client.newCall(req).execute()
                if(res.isSuccessful){
                    return res.peekBody(Long.MAX_VALUE).string()
                }
            }catch(e: IOException){
                Log.d("Error occured", e.toString())
            }
            return "error fetching data"
        }
    }
}