package auth

import android.content.Context
import common.ShowToast

class PassswordValidator{
    companion object{
        fun validate(context: Context,password1: String, password2: String, email: String, username: String): Boolean{
            if(username.length==0){
                ShowToast.show(context, "Please fill in the username.")
                return false;
            }else if(email.length==0){
                ShowToast.show(context, "Please fill in the email.")
                return false;
            }else if(!isValidEmail(email)){
                ShowToast.show(context, "Please enter valid email.")
                return false;
            }else if(password1.length==0){
                ShowToast.show(context, "Please fill in the password.")
                return false;
            }else if(password2.length==0){
                ShowToast.show(context, "Please fill in the confirm password.")
                return false;
            }else if(password1.length < 8 || password1.length >15){
                ShowToast.show(context, "Password length must be in between 8-15 characters.")
                return false;
            }else if(isCommanPassword(password1)){
                ShowToast.show(context, "Avoid using common passwords.")
                return false;
            }else if(!isValidPassword(password1)){
                ShowToast.show(
                    context,
                    "Password must contain Capital, small letter, symbol and number."
                )
                return false;
            }
            return true
        }
        fun isCommanPassword(pass: String): Boolean{
            val commonPasswords = listOf("password", "123456", "qwerty", "admin", "letmein", "welcome", "123abc")
            return commonPasswords.contains(pass.toLowerCase())
        }
        fun isValidPassword(password: String): Boolean {
            val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@.#\$!%*?&^])[A-Za-z\\d@.#\$!%*?&]+\$")
            return regex.matches(password)
        }
        fun isValidEmail(email: String):Boolean{
            val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
            return emailRegex.matches(email)
        }
    }

}