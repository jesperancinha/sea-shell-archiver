package org.jesperancinha.shell.webflux.immutable.data

import lombok.Builder
import org.jesperancinha.shell.client.lowers.Lower

@JvmRecord
data class SeaShellLowerDto @Builder constructor(val type: String, val color: String, val size: String) {
    init {
        size = size
        color = color
        type = type
    }

    companion object {
        fun create(lower: Lower?): SeaShellLowerDto {
            return SeaShellLowerDto.builder()
                .type(lower!!.type)
                .color(lower.color)
                .size(lower.size)
                .build()
        }

        fun create(lower: SeaShellLowerDto?): SeaShellLowerDto? {
            return null
        }
    }
}