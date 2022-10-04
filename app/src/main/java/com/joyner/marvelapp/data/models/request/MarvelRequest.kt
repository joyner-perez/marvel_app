package com.joyner.marvelapp.data.models.request

import java.util.*

data class MarvelRequest(
    val apiKey: String,
    var ts: String,
    val hash: String
) {
    fun createHash() {
        ts = UUID.randomUUID().toString()
    }
}