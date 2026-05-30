package de.snenjih.velocloud.shared.events.definitions.service

import de.snenjih.velocloud.shared.events.Event
import de.snenjih.velocloud.shared.service.Service

class ServiceLogEvent(val service: Service, val line: String) : Event