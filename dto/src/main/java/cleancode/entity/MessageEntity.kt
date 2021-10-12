package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * User Entity used in the data layer.
 */
@Entity
class MessageEntity {
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var messageId: Int = 0

    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    var imageUrl: String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = ""

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String = ""

}
