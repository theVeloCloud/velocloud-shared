package de.snenjih.velocloud.shared.events

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import de.snenjih.velocloud.shared.player.PlayerSerializer
import de.snenjih.velocloud.shared.player.VelocloudPlayer
import de.snenjih.velocloud.shared.service.Service
import de.snenjih.velocloud.shared.service.ServiceSerializer
import de.snenjih.velocloud.shared.template.Template
import de.snenjih.velocloud.shared.template.TemplateSerializer

/**
 * Abstract provider for shared events in the VeloCloud system.
 *
 * Responsible for calling events and subscribing to specific event types.
 * Also provides a preconfigured Gson instance for serializing services, templates, and players.
 */
abstract class SharedEventProvider {

    /**
     * Gson instance with custom serializers for Service, Template, and VelocloudPlayer.
     */
    val gsonSerializer: Gson = GsonBuilder()
        .registerTypeHierarchyAdapter(Service::class.java, ServiceSerializer())
        .registerTypeHierarchyAdapter(Template::class.java, TemplateSerializer())
        .registerTypeHierarchyAdapter(VelocloudPlayer::class.java, PlayerSerializer())
        .create()

    /**
     * Calls the given event, triggering all relevant subscribers.
     *
     * @param event the event to call
     */
    abstract fun call(event: Event)

    /**
     * Subscribes to a specific type of event.
     *
     * @param T the type of event
     * @param eventType the class of the event type to subscribe to
     * @param result a callback function that is invoked when the event is called
     */
    abstract fun <T : Event> subscribe(eventType: Class<T>, result: EventCallback<T>)
}
