package com.disneycodechallenge_alexhall.data.model

data class GuestModel(
    var name: String = "",
    var guestType: Int = 0,
    var isSelect: Boolean = false,
    var section: Int = -1
) : Section {

    override fun type(): Int {
        return Section.item
    }

    override fun sectionPosition(): Int {
        return section
    }
}