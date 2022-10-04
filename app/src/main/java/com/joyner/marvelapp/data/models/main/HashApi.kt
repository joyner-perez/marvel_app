package com.joyner.marvelapp.data.models.main

import com.joyner.marvelapp.BuildConfig
import com.joyner.marvelapp.data.models.request.MarvelRequest
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

object HashApi {
    fun createMarvelApiHash(): MarvelRequest {
        val ts: String = UUID.randomUUID().toString()
        val md = MessageDigest.getInstance("MD5")
        val input = ts + BuildConfig.PRIVATE_API_KEY + BuildConfig.PUBLIC_API_KEY
        val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        return MarvelRequest(BuildConfig.PUBLIC_API_KEY, ts, hash)
    }
}