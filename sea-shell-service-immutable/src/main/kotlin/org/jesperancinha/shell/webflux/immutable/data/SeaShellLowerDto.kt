package org.jesperancinha.shell.webflux.immutable.data

import org.jesperancinha.shell.client.lowers.Lower

data class SeaShellLowerDto(val type: String, val color: String, val size: String) {

    companion object {
        fun create(lower: Lower?) = SeaShellLowerDto(
            type = lower!!.type,
            color = lower.color,
            size = lower.size
        )

        fun create(lower: SeaShellLowerDto?): SeaShellLowerDto? {
            return null
        }
    }
}