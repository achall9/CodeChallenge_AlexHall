package com.disneycodechallenge_alexhall.data.model

interface Section {
    companion object {
        const val header = 2
        const val item = 3
        const val info = 4
    }

    fun type(): Int

    fun sectionPosition(): Int
}