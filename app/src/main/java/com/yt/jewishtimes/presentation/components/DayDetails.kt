package com.yt.jewishtimes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.yt.jewishtimes.calculations.DayZemanim

@Composable
fun DayDetails() {
    val dz = DayZemanim()

    Column {
        LabelWithTime(label = "Month", time = dz.getMonth())
        LabelWithTime(label = "Date", time = dz.getDate())
        LabelWithTime("Alot Hashachar", dz.getAlotHashachar())
        Spacer(modifier = Modifier.width(8.dp))
        LabelWithTime("Chatzot", "12:00")
        LabelWithTime(label = "Hours", time = dz.getSt())
    }
}

@Composable
fun LabelWithTime(label: String, time: String) {
    Text(text = "$label: $time")
}