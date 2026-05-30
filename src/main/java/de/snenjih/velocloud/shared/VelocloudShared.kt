package de.snenjih.velocloud.shared

import de.snenjih.velocloud.shared.groups.SharedGroupProvider
import de.snenjih.velocloud.shared.information.SharedCloudInformationProvider
import de.snenjih.velocloud.shared.platform.SharedPlatformProvider
import de.snenjih.velocloud.shared.player.SharedPlayerProvider
import de.snenjih.velocloud.shared.service.SharedServiceProvider
import de.snenjih.velocloud.shared.template.SharedTemplateProvider
import de.snenjih.velocloud.shared.events.SharedEventProvider
import kotlin.properties.Delegates

var velocloudShared: VelocloudShared by Delegates.notNull()
    private set

// setShared is needed because without the flag its impossible
// to use more than one SDK instance in one process (e.g. off premise bridge)
abstract class VelocloudShared(setShared: Boolean) {

    abstract fun eventProvider(): SharedEventProvider
    abstract fun serviceProvider(): SharedServiceProvider<*>
    abstract fun groupProvider(): SharedGroupProvider<*>
    abstract fun playerProvider(): SharedPlayerProvider<*>
    abstract fun cloudInformationProvider(): SharedCloudInformationProvider<*>
    abstract fun platformProvider(): SharedPlatformProvider<*>
    abstract fun templateProvider(): SharedTemplateProvider<*>

    init {
        if (setShared) {
            velocloudShared = this
        }
    }
}