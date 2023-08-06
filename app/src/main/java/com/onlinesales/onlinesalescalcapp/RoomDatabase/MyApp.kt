package com.onlinesales.onlinesalescalcapp.RoomDatabase

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
    val database by lazy {
        Room.databaseBuilder(
            this,
            QnaDatabase::class.java,
            "QnaDatabase"
        ).build()
    }
}