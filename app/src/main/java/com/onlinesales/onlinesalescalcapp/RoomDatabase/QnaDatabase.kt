package com.onlinesales.onlinesalescalcapp.RoomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QnaData::class], version = 2)
abstract class QnaDatabase : RoomDatabase() {

    abstract fun qnaDao(): QnaDao

}