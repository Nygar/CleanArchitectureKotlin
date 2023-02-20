package cleancode.model.mappers

import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.model.CategoryModel
import com.nygar.domain.BuildConfig
import java.util.ArrayList

object MapperCategory {
    /**
     * Transform a [MessageEntity] into an [CategoryModel].
     *
     * @param input Object to be transformed.
     * @return [CategoryModel].
     */
    fun transform(input: CategoryEntity): CategoryModel {
        val output = CategoryModel()
        output.categoryId = input.categoryId
        output.imageUrl = BuildConfig.URL_BASE + input.imageUrl
        output.name = input.name

        return output
    }

    /**
     * Transform a Collection of [CategoryEntity] into a Collection of [CategoryModel].
     *
     * @param input Objects to be transformed.
     * @return List of [CategoryModel].
     */
    fun transform(input: Collection<CategoryEntity>): List<CategoryModel> {
        var output: List<CategoryModel> = ArrayList()

        if (input.isNotEmpty()) {
            output = ArrayList()
            for (category in input) {
                output.add(transform(category))
            }
        }
        return output
    }
}