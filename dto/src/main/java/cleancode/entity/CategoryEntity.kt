package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Category Entity used in the data layer.
 */

@Serializable
@Entity
data class CategoryEntity(
    @SerialName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var categoryId: Int = 0,

    @SerialName("image_url")
    @ColumnInfo(name = "image_url")
    var imageUrl: String = "",

    @SerialName("name")
    @ColumnInfo(name = "name")
    var name: String = ""

)