package com.onlinesales.onlinesalescalcapp.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.time.LocalDateTime

@Entity(tableName = "qnadata")
data class QnaData(

    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val expression: String,
    val submissionDate: Long

)
