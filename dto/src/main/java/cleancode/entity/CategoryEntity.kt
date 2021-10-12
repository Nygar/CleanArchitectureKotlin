package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Category Entity used in the data layer.
 */

@Entity
class CategoryEntity{
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var categoryId: Int = 0

    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    var imageUrl: String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = ""

}