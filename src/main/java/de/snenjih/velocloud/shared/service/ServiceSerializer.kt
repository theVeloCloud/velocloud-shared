package de.snenjih.velocloud.shared.service

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.reflect.TypeToken
import de.snenjih.velocloud.shared.VelocloudSharedKeys
import de.snenjih.velocloud.shared.template.Template
import de.snenjih.velocloud.v1.groups.GroupType
import de.snenjih.velocloud.v1.services.ServiceState
import java.lang.reflect.Type

class ServiceSerializer : JsonSerializer<Service>, JsonDeserializer<Service> {

    override fun serialize(
        src: Service,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        val data = JsonObject()

        data.addProperty(VelocloudSharedKeys.NAME, src.groupName)
        data.addProperty(VelocloudSharedKeys.ID, src.id)
        data.addProperty(VelocloudSharedKeys.HOSTNAME, src.hostname)
        data.addProperty(VelocloudSharedKeys.PORT, src.port)
        data.addProperty(VelocloudSharedKeys.STATE, src.state.name)
        data.addProperty(VelocloudSharedKeys.TYPE, src.type.name)
        data.addProperty(VelocloudSharedKeys.INFORMATION, src.information.toString())
        data.addProperty(VelocloudSharedKeys.MIN_MEMORY, src.minMemory)
        data.addProperty(VelocloudSharedKeys.MAX_MEMORY, src.maxMemory)
        data.addProperty(VelocloudSharedKeys.MAX_PLAYER_COUNT, src.maxPlayerCount)
        data.addProperty(VelocloudSharedKeys.PLAYER_COUNT, src.playerCount)
        data.addProperty(VelocloudSharedKeys.MEMORY_USAGE, src.memoryUsage)
        data.addProperty(VelocloudSharedKeys.CPU_USAGE, src.cpuUsage)
        data.addProperty(VelocloudSharedKeys.MOTD, src.motd)

        data.add(VelocloudSharedKeys.TEMPLATES, context.serialize(src.templates))
        data.add(VelocloudSharedKeys.PROPERTIES, context.serialize(src.properties.map { it.key to it.value }.toMap()))

        return data
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Service {
        val data = json.asJsonObject

        val name = data.get(VelocloudSharedKeys.NAME).asString
        val id = data.get(VelocloudSharedKeys.ID).asInt
        val hostname = data.get(VelocloudSharedKeys.HOSTNAME).asString
        val port = data.get(VelocloudSharedKeys.PORT).asInt
        val type = GroupType.valueOf(data.get(VelocloudSharedKeys.TYPE).asString)
        val state = ServiceState.valueOf(data.get(VelocloudSharedKeys.STATE).asString)
        val templatesType = object : TypeToken<List<Template>>() {}.type
        val templates = context.deserialize<List<Template>>(data.get(VelocloudSharedKeys.TEMPLATES), templatesType)
        val information = ServiceInformation.fromJson(data.get(VelocloudSharedKeys.INFORMATION).asString)
        val minMemory = data.get(VelocloudSharedKeys.MIN_MEMORY).asInt
        val maxMemory = data.get(VelocloudSharedKeys.MAX_MEMORY).asInt
        val maxPlayerCount = data.get(VelocloudSharedKeys.MAX_PLAYER_COUNT).asInt
        val playerCount = data.get(VelocloudSharedKeys.PLAYER_COUNT).asInt
        val memoryUsage = data.get(VelocloudSharedKeys.MEMORY_USAGE).asDouble
        val cpuUsage = data.get(VelocloudSharedKeys.CPU_USAGE).asDouble
        val motd = data.get(VelocloudSharedKeys.MOTD).asString

        val propertiesType = object : TypeToken<Map<String, String>>() {}.type
        val properties = context.deserialize<Map<String, String>>(data.get(VelocloudSharedKeys.PROPERTIES), propertiesType)

        return Service(
            name,
            id,
            state,
            type,
            properties,
            hostname,
            port,
            templates,
            information,
            minMemory,
            maxMemory,
            playerCount,
            maxPlayerCount,
            memoryUsage,
            cpuUsage,
            motd
        )
    }
}