package com.stolbov.emptyprojectstolbov.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface JustStringDAO {
    @Query("SELECT * FROM JustString")
    fun getStringsList(): List<JustString>

    @Update
    fun updateString(justString: JustString)

    @Insert
    fun insertString(justString: JustString)

    @Query("DELETE FROM JustString WHERE id = 0")
    fun deletePreviousData()
}