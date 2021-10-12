package cleancode.model.mappers

import cleancode.entity.UserLoggedEntity
import cleancode.model.UserLoggedModel
import com.nygar.remote.BuildConfig

object MapperUserLogged {

    /**
     * Transform a [UserLoggedEntity] into an [UserLoggedModel].
     *
     * @param input Object to be transformed.
     * @return [UserLoggedModel].
     */
    fun transform(input: UserLoggedEntity): UserLoggedModel {
        val output = UserLoggedModel()
        output.userLoggedId = input.userLoggedId
        output.avatarUrl = BuildConfig.URL_BASE + input.avatarUrl
        output.fullName = input.fullname

        return output
    }
}