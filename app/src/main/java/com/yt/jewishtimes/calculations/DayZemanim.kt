package com.yt.jewishtimes.calculations

import com.kosherjava.zmanim.ComplexZmanimCalendar
import com.kosherjava.zmanim.hebrewcalendar.HebrewDateFormatter
import com.kosherjava.zmanim.hebrewcalendar.JewishDate
import com.kosherjava.zmanim.util.GeoLocation
import java.util.TimeZone


class DayZemanim() {

    val czc: ComplexZmanimCalendar
    var jd: JewishDate
    val hebrewFormatter: HebrewDateFormatter


    init {
        val locationName: String = "Lakewood, NJ"
        val latitude: Double = 40.096 //Lakewood, NJ
        val longitude: Double = -74.222 //Lakewood, NJ
        val elevation: Double = 0.0 //optional elevation
        val timeZone: TimeZone = TimeZone.getTimeZone("America/New_York")
        val location: GeoLocation =
            GeoLocation(locationName, latitude, longitude, elevation, timeZone)

        czc = ComplexZmanimCalendar(location)
        jd = JewishDate()
        hebrewFormatter = HebrewDateFormatter()
    }

    fun getAlotHashachar()  : String {
        return czc.alos19Degrees.toString()
    }

    fun getMonth() : String {
        return hebrewFormatter.formatMonth(this.jd)
    }

    fun getDate(): String {
        return jd.jewishDayOfMonth.toString()
    }

    fun getSt() : String {
        val zoneId: java.time.ZoneId = java.time.ZoneId.systemDefault()
        val currentZonedDateTime: java.time.ZonedDateTime = java.time.ZonedDateTime.now(zoneId)

        return currentZonedDateTime.hour.toString()


//        val currentTimestamp: Long = currentZonedDateTime.toInstant().toEpochMilli()



    }


}