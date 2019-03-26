package cuongduong.developer.android.stackoverflow.ui.provider

import java.text.SimpleDateFormat
import java.util.*

class FormatDateProvider {
    fun formatLongToDateString(value: Long): String {
        val date = Date(value * 1000L)
        val df2 = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        return df2.format(date)
    }
}