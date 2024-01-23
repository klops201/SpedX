package TMSv3.SpedX.core

import android.content.Context
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import TMSv3.SpedX.core.Constants.TAG
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.time.LocalDate

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = makeText(context, message, LENGTH_LONG).show()

        fun convertDate(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("dd.MM.yyyy")
            return format.format(date)
        }


        fun generateSMS(country: String, date: Long, hour: Int, minute: Int,  ):  String {
            val dateSMS = Date(date)
            val format = SimpleDateFormat("MM.dd")
            val selectedLocalDate: LocalDate? = date?.let {
                LocalDate.ofEpochDay(it / (24 * 60 * 60 * 1000))
            }
            var isToday: Boolean? = selectedLocalDate?.isEqual(LocalDate.now())
            var dataSMS: String
            when (country) {
                "Łotwa" -> dataSMS = "LV"
                "Litwa" -> dataSMS = "LT"
                "Estonia" -> dataSMS = "EE"
                else -> dataSMS = "Wybierz kraj"
            }
            if (isToday == true) {
                dataSMS = dataSMS + " " + hour+1 + ":" + minute+2
            }else{
                dataSMS = dataSMS + " " + format.format(dateSMS) + " " + (hour+1) + ":" + (minute+2)
            }
            return dataSMS
        }


        fun sendSMS(textSMS: String, context: Context)
        {
            val uri = Uri.parse("smsto:+37128914888")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", textSMS)
            context.startActivity(intent)
        }








    }

}
