package com.project.mywidgets

import java.util.*

internal object NumberGenerator {
    // TODO 2: buat number generatot
    fun generate(max: Int) : Int {
        val random = Random()
        return random.nextInt(max)
    }
}