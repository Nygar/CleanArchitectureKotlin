package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLoggedEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "avatar_url")
    var avatar_url: String = "",

    @ColumnInfo(name = "full_name")
    var full_name: String = ""

)