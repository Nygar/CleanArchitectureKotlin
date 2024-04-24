package com.nygar.model.mappers

import com.nygar.common.BuildConfig
import com.nygar.entity.MessageEntity
import com.nygar.model.MessageModel

object MapperMessage {
    /**
     * Transform a [MessageEntity] into an [MessageModel].
     *
     * @param input Object to be transformed.
     * @return [MessageModel].
     */
    fun transform(input: MessageEntity): MessageModel {
        val output = MessageModel()
        output.messageId = input.id
        output.imageUrl = BuildConfig.URL_BASE + input.image_url
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
        val output: MutableList<MessageModel> = arrayListOf()

        if (input.isNotEmpty()) {
            input.map { message ->
                output.add(transform(message))
            }
        }
        return output
    }
}
