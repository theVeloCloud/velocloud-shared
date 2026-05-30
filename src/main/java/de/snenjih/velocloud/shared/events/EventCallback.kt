package de.snenjih.velocloud.shared.events

fun interface EventCallback<T> {
    fun call(event: T)
}