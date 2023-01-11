package org.jesperancinha.shell.webflux.data

import lombok.Builder

@JvmRecord
data class SeaShellDto @Builder constructor(
    val name: String,
    val scientificName: String,
    val slogan: String,
    val persons: MutableList<SeaShellPersonDto?>,
    val costumes: MutableList<SeaShellCostumeDto?>,
    val personIds: List<Long>,
    val costumeIds: List<Long>
) {
    fun addPersons(persons: List<SeaShellPersonDto?>?) {
        this.persons.addAll(persons!!)
    }

    fun addCostumes(costumes: List<SeaShellCostumeDto?>?) {
        this.costumes.addAll(costumes!!)
    }
}