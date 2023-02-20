package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UserLoggedEntity(
    @SerialName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var userLoggedId: Int = 0,

    @SerialName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String = "",

    @SerialName("full_name")
    @ColumnInfo(name = "full_name")
    var fullname: String = ""

)