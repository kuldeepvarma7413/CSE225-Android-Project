package Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import apiConnector.APIConnect
import com.example.studysync.R
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class Resources_Page_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("res", APIConnect.get("https://jsonplaceholder.typicode.com/users/1"))
        return inflater.inflate(R.layout.fragment_resources__page_, container, false)
    }
}