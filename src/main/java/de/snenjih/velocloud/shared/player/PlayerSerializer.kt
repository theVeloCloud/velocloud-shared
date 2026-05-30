package de.snenjih.velocloud.shared.player

import com.google.gson.*
import de.snenjih.velocloud.shared.VelocloudSharedKeys
import java.lang.reflect.Type
import java.util.UUID

/**
 * Gson serializer and deserializer for [VelocloudPlayer].
 *
 * Serializes a VelocloudPlayer to JSON and deserializes JSON into a VelocloudPlayer
 * using predefined keys from [VelocloudSharedKeys].
 */
class PlayerSerializer : JsonSerializer<VelocloudPlayer>, JsonDeserializer<VelocloudPlayer> {

    /**
     * Serializes a [VelocloudPlayer] into a [JsonElement].
     *
     * @param src the VelocloudPlayer to serialize
     * @param typeOfSrc the type of the source object
     * @param context the JSON serialization context
     * @return a JsonElement representing the player
     */
    override fun serialize(
        src: VelocloudPlayer,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonObject().apply {
            addProperty(VelocloudSharedKeys.UNIQUE_ID, src.uniqueId())
            addProperty(VelocloudSharedKeys.NAME, src.name)
            addProperty(VelocloudSharedKeys.CURRENT_SERVER_NAME, src.currentServerName)
            addProperty(VelocloudSharedKeys.CURRENT_PROXY_NAME, src.currentProxyName)
        }
    }

    /**
     * Deserializes a [JsonElement] into a [VelocloudPlayer].
     *
     * @param json the JSON element to deserialize
     * @param typeOfT the target type
     * @param context the JSON deserialization context
     * @return a VelocloudPlayer instance represented by the JSON
     */
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): VelocloudPlayer {
        val obj = json.asJsonObject

        val uniqueId = UUID.fromString(obj.get(VelocloudSharedKeys.UNIQUE_ID).asString)
        val name = obj.get(VelocloudSharedKeys.NAME).asString
        val currentServer = obj.get(VelocloudSharedKeys.CURRENT_SERVER_NAME).asString
        val currentProxy = obj.get(VelocloudSharedKeys.CURRENT_PROXY_NAME).asString
        return VelocloudPlayer(
            name = name,
            uniqueId = uniqueId,
            currentServerName = currentServer,
            currentProxyName = currentProxy
        )
    }
}
