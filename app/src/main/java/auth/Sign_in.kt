package auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.core.content.ContextCompat
import com.example.studysync.MainActivity
import com.example.studysync.R
import common.ShowToast
import com.example.studysync.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class Sign_in : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        firebase
        firebaseAuth = FirebaseAuth.getInstance()
//        sign in
        binding.button.setOnClickListener{
            val usernameOrEmail = binding.emailOrUsernameEt.text.toString()
            val pass = binding.passET.text.toString()
            if(checkEmpty(usernameOrEmail, pass)){
//              fields contain data
//                email
                if(Patterns.EMAIL_ADDRESS.matcher(usernameOrEmail).matches()){
                    signInWithEmail(usernameOrEmail, pass)
                }else{
                    signInWithUsername(usernameOrEmail, pass)
                }
            }
        }

//        register
        binding.textView.setOnClickListener{
            startActivity(Intent(this, Sign_up::class.java))
            finish()
        }

//        status bar color
        window.statusBarColor=ContextCompat.getColor(this, R.color.black)
    }
//    check if fields are empty
    private fun checkEmpty(emailUsername: String, password: String):Boolean{
        if(emailUsername.length==0){
            ShowToast.show(this, "Please enter email or username")
            return false
        }else if(password.length==0){
            ShowToast.show(this, "Please enter password")
            return false
        }
        return true
    }


    //        sign in using email
    private fun signInWithEmail(email: String, pass: String){
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if(it.isSuccessful){
                startActivity(Intent(this, MainActivity::class.java))
                ShowToast.show(this, "Logged in as ${firebaseAuth.currentUser?.displayName}")
                finish()
            }else{
                Log.d("Sign Up error", it.exception.toString())
                ShowToast.show(this, "No user found")
            }
        }
    }

    //        sign in using username
    private fun signInWithUsername(username: String, pass: String){
        val user=firebaseAuth.currentUser
        if(user!=null && user.displayName==username){
            signInWithEmail(user.email ?:"", pass)
        }else{
            ShowToast.show(this, "No user found")
        }
    }

//    if user is logged in already
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}