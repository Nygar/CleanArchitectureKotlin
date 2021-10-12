package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class UserLoggedEntity {
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var userLoggedId: Int = 0

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String = ""

    @SerializedName("full_name")
    @ColumnInfo(name = "full_name")
    var fullname: String = ""

}