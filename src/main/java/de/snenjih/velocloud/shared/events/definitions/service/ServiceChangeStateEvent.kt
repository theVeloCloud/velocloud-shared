package de.snenjih.velocloud.shared.events.definitions.service

import de.snenjih.velocloud.shared.events.Event
import de.snenjih.velocloud.shared.service.Service

class ServiceChangeStateEvent(val service: Service) : Event