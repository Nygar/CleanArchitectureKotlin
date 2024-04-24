package com.nygar.model.mappers

import com.nygar.common.BuildConfig
import com.nygar.entity.CategoryEntity
import com.nygar.entity.MessageEntity
import com.nygar.model.CategoryModel

object MapperCategory {
    /**
     * Transform a [MessageEntity] into an [CategoryModel].
     *
     * @param input Object to be transformed.
     * @return [CategoryModel].
     */
    fun transform(input: CategoryEntity): CategoryModel {
        val output = CategoryModel()
        output.categoryId = input.id
        output.imageUrl = BuildConfig.URL_BASE + input.image_url
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
        val output: MutableList<CategoryModel> = arrayListOf()

        if (input.isNotEmpty()) {
            input.map { category ->
                output.add(transform(category))
            }
        }
        return output
    }
}
