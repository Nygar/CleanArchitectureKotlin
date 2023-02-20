package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * User Entity used in the data layer.
 */
@Serializable
@Entity
data class MessageEntity(
    @SerialName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var messageId: Int = 0,

    @SerialName("image_url")
    @ColumnInfo(name = "image_url")
    var imageUrl: String = "",

    @SerialName("name")
    @ColumnInfo(name = "name")
    var name: String = "",

    @SerialName("description")
    @ColumnInfo(name = "description")
    var description: String = ""

)
