package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class UserEntity {
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var userId: Int = 0

    @SerializedName("cover_url")
    @ColumnInfo(name = "cover_url")
    var coverUrl: String = ""

    @SerializedName("full_name")
    @ColumnInfo(name = "full_name")
    var fullname: String = ""

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String = ""

    @SerializedName("followers")
    @ColumnInfo(name = "followers")
    var followers: Int = 0

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String = ""

}