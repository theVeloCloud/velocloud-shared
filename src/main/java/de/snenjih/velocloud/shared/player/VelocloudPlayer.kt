package de.snenjih.velocloud.shared.player

import com.google.gson.JsonObject
import de.snenjih.velocloud.shared.VelocloudSharedKeys
import de.snenjih.velocloud.v1.player.PlayerSnapshot
import java.util.UUID

/**
 * Represents a player in the VeloCloud system.
 *
 * @property name the name of the player
 * @property uniqueId the unique identifier (UUID) of the player
 * @property currentServerName the name of the service the player is currently connected to
 * @property currentProxyName the name of the proxy the player is currently connected to
 */
open class VelocloudPlayer(
    val name: String,
    val uniqueId: UUID,
    val currentServerName: String,
    val currentProxyName: String
) {

    /**
     * Returns the string representation of the player's UUID.
     */
    fun uniqueId(): String = uniqueId.toString()

    companion object {

        /**
         * Creates a VelocloudPlayer from a PlayerSnapshot.
         *
         * @param snapshot the snapshot to bind
         * @return a VelocloudPlayer instance
         */
        fun from(snapshot: PlayerSnapshot): VelocloudPlayer {
            return VelocloudPlayer(
                name = snapshot.name,
                uniqueId = UUID.fromString(snapshot.uniqueId),
                currentServerName = snapshot.currentServerName,
                currentProxyName = snapshot.currentProxyName
            )
        }
    }

    /**
     * Converts this VelocloudPlayer into a PlayerSnapshot.
     *
     * @return PlayerSnapshot representing this player
     */
    fun to(): PlayerSnapshot {
        return PlayerSnapshot.newBuilder()
            .setName(name)
            .setUniqueId(uniqueId.toString())
            .setCurrentServerName(currentServerName)
            .setCurrentProxyName(currentProxyName)
            .build()
    }
}

/**
 * Converts the VelocloudPlayer to a JSON object using predefined keys.
 *
 * @return JsonObject representing this player
 */
fun VelocloudPlayer.toJson(): JsonObject {
    return JsonObject().apply {
        addProperty(VelocloudSharedKeys.NAME, name)
        addProperty(VelocloudSharedKeys.UNIQUE_ID, uniqueId.toString())
        addProperty(VelocloudSharedKeys.CURRENT_SERVER_NAME, currentServerName)
        addProperty(VelocloudSharedKeys.CURRENT_PROXY_NAME, currentProxyName)
    }
}
