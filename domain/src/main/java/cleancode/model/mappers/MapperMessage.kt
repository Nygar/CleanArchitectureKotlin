package cleancode.model.mappers

import cleancode.entity.MessageEntity
import cleancode.model.MessageModel
import java.util.ArrayList

object MapperMessage{

    /**
     * Transform a [MessageEntity] into an [MessageModel].
     *
     * @param input Object to be transformed.
     * @return [MessageModel].
     */
    fun transform(input: MessageEntity): MessageModel {
        val output = MessageModel()
        output.messageId = input.id
        output.imageUrl = "BuildConfig.URL_BASE" + input.image_url
        output.name = input.name
        output.description = input.description

        return output
    }

    /**
     * Transform a Collection of [MessageEntity] into a Collection of [MessageModel].
     *
     * @param input Objects to be transformed.
     * @return List of [MessageModel].
     */
    fun transform(input: Collection<MessageEntity>): List<MessageModel> {
        var output: List<MessageModel> = ArrayList()

        if (input.isNotEmpty()) {
            output = ArrayList()
            for (category in input) {
                output.add(transform(category))
            }
        }
        return output
    }
}