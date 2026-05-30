package de.snenjih.velocloud.shared.platform

import de.snenjih.velocloud.v1.groups.GroupPlatformSnapshot

data class PlatformIndex(val name: String, val version: String) {

    fun to(): GroupPlatformSnapshot {
        return GroupPlatformSnapshot.newBuilder()
            .setName(name)
            .setVersion(version)
            .build()
    }

    override fun toString(): String {
        return "$name-$version"
    }
}