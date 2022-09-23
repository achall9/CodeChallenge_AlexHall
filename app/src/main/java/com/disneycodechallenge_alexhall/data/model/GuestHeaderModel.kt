package com.disneycodechallenge_alexhall.data.model

data class GuestHeaderModel(
    var name: String = "",
    var section: Int = -1
) : Section {

    override fun type(): Int {
        return Section.header
    }

    override fun sectionPosition(): Int {
        return section
    }

}