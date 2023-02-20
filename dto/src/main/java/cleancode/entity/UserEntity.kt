package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UserEntity(
    @SerialName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var userId: Int = 0,

    @SerialName("cover_url")
    @ColumnInfo(name = "cover_url")
    var coverUrl: String = "",

    @SerialName("full_name")
    @ColumnInfo(name = "full_name")
    var fullname: String = "",

    @SerialName("description")
    @ColumnInfo(name = "description")
    var description: String = "",

    @SerialName("followers")
    @ColumnInfo(name = "followers")
    var followers: Int = 0,

    @SerialName("email")
    @ColumnInfo(name = "email")
    var email: String = ""

)