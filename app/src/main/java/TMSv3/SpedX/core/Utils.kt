package TMSv3.SpedX.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import TMSv3.SpedX.core.Constants.TAG
import java.security.MessageDigest

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = makeText(context, message, LENGTH_LONG).show()
    }
//    fun generateToken(login: String, password: String, date: String): String {
//        val hashedPassword = hashString(password).substring(0, 19)
//        return hashString("$login:$hashedPassword:$date").substring(0, 32)
//    }
//
//    private fun hashString(input: String): String {
//        val bytes = MessageDigest.getInstance("MD5").digest(input.toByteArray())
//        return bytes.joinToString("") { "%02x".format(it) }
//    }
}
