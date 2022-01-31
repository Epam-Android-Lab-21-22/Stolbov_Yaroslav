package com.stolbov.emptyprojectstolbov.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class JustString {
    @PrimaryKey
    var id = 0
    @ColumnInfo(name = "string")
    var string: String? = null
}