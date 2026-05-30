package de.snenjih.velocloud.shared.events.definitions

import de.snenjih.velocloud.shared.events.Event
import de.snenjih.velocloud.shared.player.VelocloudPlayer

/**
 * Event is called if a player joins the network
 */
class PlayerLeaveEvent(val player: VelocloudPlayer) : Event