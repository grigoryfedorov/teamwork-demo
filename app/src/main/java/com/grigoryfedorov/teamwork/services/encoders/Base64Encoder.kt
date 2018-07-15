package com.grigoryfedorov.teamwork.services.encoders

import android.util.Base64

class Base64Encoder : Encoder {
    override fun encode(value: String): String = Base64.encodeToString(value.toByteArray(), Base64.NO_WRAP)
}