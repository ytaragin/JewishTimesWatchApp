package com.yt.jewishtimes.complication

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.ComplicationType
import androidx.wear.watchface.complications.data.LongTextComplicationData
import androidx.wear.watchface.complications.data.PlainComplicationText
import androidx.wear.watchface.complications.data.RangedValueComplicationData
import androidx.wear.watchface.complications.data.ShortTextComplicationData
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceService
import androidx.wear.watchface.complications.datasource.ComplicationRequest

class JewishDateComplicationService : ComplicationDataSourceService() {

    override fun getPreviewData(type: ComplicationType): ComplicationData? {
        return when (type) {
            ComplicationType.SHORT_TEXT -> createShortTextComplicationData("5 Iyar")
            ComplicationType.LONG_TEXT -> createLongTextComplicationData("5 Iyar 5784")
            ComplicationType.RANGED_VALUE -> createRangedValueComplicationData(5, 1, 30)
            else -> null
        }
    }

    override fun onComplicationRequest(
        request: ComplicationRequest,
        listener: ComplicationRequestListener
    ) {
        val jewishDate = calculateJewishDate() // Implement this function to get the current Jewish date
        val complicationData = when (request.complicationType) {
            ComplicationType.SHORT_TEXT -> createShortTextComplicationData(jewishDate.shortFormat)
            ComplicationType.LONG_TEXT -> createLongTextComplicationData(jewishDate.longFormat)
            ComplicationType.RANGED_VALUE -> createRangedValueComplicationData(
                jewishDate.dayOfMonth,
                1,
                jewishDate.daysInMonth
            )
            else -> null
        }

            listener.onComplicationData(complicationData)
    }

    private fun createShortTextComplicationData(text: String): ShortTextComplicationData {
        return ShortTextComplicationData.Builder(
            text = PlainComplicationText.Builder(text).build(),
            contentDescription = PlainComplicationText.Builder("Jewish Date").build()
        ).setTapAction(createMainActivityIntent())
            .build()
    }

    private fun createLongTextComplicationData(text: String): LongTextComplicationData {
        return LongTextComplicationData.Builder(
            text = PlainComplicationText.Builder(text).build(),
            contentDescription = PlainComplicationText.Builder("Jewish Date").build()
        ).setTapAction(createMainActivityIntent())
            .build()
    }

    private fun createRangedValueComplicationData(
        value: Int,
        min: Int,
        max: Int
    ): RangedValueComplicationData {
        return RangedValueComplicationData.Builder(
            value = value.toFloat(),
            min = min.toFloat(),
            max = max.toFloat(),
            contentDescription = PlainComplicationText.Builder("Day of Jewish Month").build()
        ).setText(PlainComplicationText.Builder("$value/$max").build())
            .setTapAction(createMainActivityIntent())
            .build()
    }

    private fun createMainActivityIntent(): PendingIntent {
        val intent = Intent().apply {
            component = ComponentName(packageName, "com.yt.jewishtimes.presentation.MainActivity")
        }
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun calculateJewishDate(): JewishDate {
        // Implement Jewish date calculation logic here
        // This is a placeholder implementation
        return JewishDate(5, "Iyar", 5784, 30)
    }

    data class JewishDate(
        val dayOfMonth: Int,
        val month: String,
        val year: Int,
        val daysInMonth: Int
    ) {
        val shortFormat: String
            get() = "$dayOfMonth $month"
        val longFormat: String
            get() = "$dayOfMonth $month $year"
    }
}
