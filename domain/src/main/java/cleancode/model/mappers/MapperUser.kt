package cleancode.model.mappers

import cleancode.entity.UserEntity
import cleancode.model.UserModel
import java.util.ArrayList

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
        output.coverUrl = "BuildConfig.URL_BASE" + input.cover_url
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
        var output: List<UserModel> = ArrayList()

        if (input.isNotEmpty()) {
            output = ArrayList()
            for (category in input) {
                output.add(transform(category))
            }
        }
        return output
    }
}