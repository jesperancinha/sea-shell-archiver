package org.jesperancinha.shell.webflux.immutable.data

import org.jesperancinha.shell.client.tops.Top

data class SeaShellTopDto(val type: String, val color: String, val size: String) {
    companion object {
        fun create(top: Top?): SeaShellTopDto {
            return SeaShellTopDto(
                type = top!!.type,
                color = top.color,
                size = top.size
            )
        }
    }
}