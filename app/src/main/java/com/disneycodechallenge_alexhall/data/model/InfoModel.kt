package com.disneycodechallenge_alexhall.data.model

data class InfoModel(
    var name: String = "",
    var section: Int = -1
) : Section {

    override fun type(): Int {
        return Section.info
    }

    override fun sectionPosition(): Int {
        return section
    }
}