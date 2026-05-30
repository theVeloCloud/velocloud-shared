package de.snenjih.velocloud.shared.events.definitions.service

import de.snenjih.velocloud.shared.events.Event
import de.snenjih.velocloud.shared.service.Service

/**
 * This event is called when the player count of a service changes.
 *
 * @param service The service which changed its player count.
 */
class ServiceChangePlayerCountEvent(val service: Service): Event