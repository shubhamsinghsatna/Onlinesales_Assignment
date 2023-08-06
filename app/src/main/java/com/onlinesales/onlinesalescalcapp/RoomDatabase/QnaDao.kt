package com.onlinesales.onlinesalescalcapp.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QnaDao {

    @Insert
    fun insertQna(qnaData: QnaData)

    @Query("SELECT * FROM qnadata ORDER BY submissionDate DESC")
    fun getAllQna(): LiveData<List<QnaData>>

}