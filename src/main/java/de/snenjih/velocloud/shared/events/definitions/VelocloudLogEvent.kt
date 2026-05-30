package de.snenjih.velocloud.shared.events.definitions

import de.snenjih.velocloud.shared.events.Event

/**
 * Event is called if a log is created in VeloCloud
 */
class VelocloudLogEvent(val log: String) : Event