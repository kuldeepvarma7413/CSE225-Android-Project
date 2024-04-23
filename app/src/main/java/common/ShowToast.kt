package common

import android.content.Context
import android.widget.Toast

class ShowToast{
    companion object{
        private var currToast : Toast? =null
        fun show(context: Context,message: String){
            currToast?.cancel()
            currToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            currToast?.show()
        }
    }
}