package auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.studysync.MainActivity
import com.example.studysync.R
import common.ShowToast
import com.example.studysync.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest

class Sign_up : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confPass = binding.confirmPassEt.text.toString()


            if (PassswordValidator.validate(this, pass, confPass, email, username)) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {

                        val user=firebaseAuth.currentUser
                        val profileUpdates=UserProfileChangeRequest.Builder().setDisplayName(username).build()
//                        update profile
                        user?.updateProfile(profileUpdates)?.addOnCompleteListener { task->
                            if(task.isSuccessful){
//                                send email verification
                                startActivity(Intent(this, MainActivity::class.java))
                                ShowToast.show(this, "Logged in as ${username}")
                                finish()
                            }else{
                                throw Throwable("Unable to create profile")
                            }
                        }
                    } else {
                        if(it.exception is FirebaseAuthUserCollisionException){
                            ShowToast.show(this, "User already exist, sign in")
                        }else{
                            ShowToast.show(this, "Error, try again...")
                        }
                    }
                }
            }
        }
//        already registered
        binding.textView.setOnClickListener {
            startActivity(Intent(this, Sign_in::class.java))
            finish()
        }

//        status bar color
        window.statusBarColor= ContextCompat.getColor(this, R.color.black)
    }
}