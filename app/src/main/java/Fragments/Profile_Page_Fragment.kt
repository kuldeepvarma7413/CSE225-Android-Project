package Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.studysync.R

class Profile_Page_Fragment : Fragment() {

    lateinit var profile_photo: ImageView
    lateinit var username: TextView
    lateinit var fullname: TextView
    lateinit var fullname_in_description: TextView
    lateinit var email: TextView
    lateinit var ques_asked_count: TextView
    lateinit var ques_answered_count: TextView
    lateinit var up_voted_count: TextView
    lateinit var date_joined: TextView
    lateinit var logout_btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile__page_, container, false)
    }
}