package cleancode.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Category Entity used in the data layer.
 */

@Entity
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "image_url")
    var image_url: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
)
