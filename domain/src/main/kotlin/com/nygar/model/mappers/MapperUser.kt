package com.nygar.model.mappers

import com.nygar.common.BuildConfig
import com.nygar.entity.UserEntity
import com.nygar.model.UserModel

object MapperUser {
    /**
     * Transform a [UserEntity] into an [UserModel].
     *
     * @param input Object to be transformed.
     * @return [UserModel].
     */
    fun transform(input: UserEntity): UserModel {
        val output = UserModel()
        output.userId = input.id
        output.coverUrl = BuildConfig.URL_BASE + input.cover_url
        output.fullName = input.full_name
        output.description = input.description
        output.followers = input.followers
        output.email = input.email

        return output
    }

    /**
     * Transform a Collection of [UserEntity] into a Collection of [UserModel].
     *
     * @param input Objects to be transformed.
     * @return List of [UserModel].
     */
    fun transform(input: Collection<UserEntity>): List<UserModel> {
        val output: MutableList<UserModel> = arrayListOf()

        if (input.isNotEmpty()) {
            input.map { user ->
                output.add(transform(user))
            }
        }
        return output
    }
}
