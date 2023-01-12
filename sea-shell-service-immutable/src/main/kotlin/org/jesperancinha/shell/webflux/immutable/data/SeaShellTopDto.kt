package org.jesperancinha.shell.webflux.immutable.data

import lombok.Builder
import org.jesperancinha.shell.client.tops.Top

@JvmRecord
data class SeaShellTopDto @Builder constructor(val type: String, val color: String, val size: String) {
    companion object {
        fun create(top: Top?): SeaShellTopDto {
            return SeaShellTopDto.builder()
                .type(top!!.type)
                .color(top.color)
                .size(top.size)
                .build()
        }
    }
}