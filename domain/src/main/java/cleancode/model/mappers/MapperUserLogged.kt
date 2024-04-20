package cleancode.model.mappers

import cleancode.entity.UserLoggedEntity
import cleancode.model.UserLoggedModel

object MapperUserLogged {

    /**
     * Transform a [UserLoggedEntity] into an [UserLoggedModel].
     *
     * @param input Object to be transformed.
     * @return [UserLoggedModel].
     */
    fun transform(input: UserLoggedEntity): UserLoggedModel {
        val output = UserLoggedModel()
        output.userLoggedId = input.id
        output.avatarUrl = "BuildConfig.URL_BASE" + input.avatar_url
        output.fullName = input.full_name

        return output
    }
}