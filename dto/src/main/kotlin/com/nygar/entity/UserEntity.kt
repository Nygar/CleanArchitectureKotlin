package com.nygar.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "cover_url")
    var cover_url: String = "",
    @ColumnInfo(name = "full_name")
    var full_name: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "followers")
    var followers: Int = 0,
    @ColumnInfo(name = "email")
    var email: String = "",
)
