package de.snenjih.velocloud.shared.module

import java.net.URLClassLoader

data class LoadedModule(
    val velocloudModule: VelocloudModule,
    val classLoader: URLClassLoader,
    val metadata: ModuleMetadata
)