package com.grigoryfedorov.teamwork.services.encoders

interface Encoder {
    fun encode(value: String): String
}