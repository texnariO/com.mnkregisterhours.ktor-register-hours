package com.mnkregisterhours.util

import kotlinx.datetime.LocalDateTime
import java.time.ZoneOffset
import kotlinx.datetime.toKotlinLocalDateTime

fun javaTimeToKotlinxDateTime(javaTime: java.time.LocalDateTime): LocalDateTime {
    return javaTime.toInstant(ZoneOffset.UTC).atZone(ZoneOffset.UTC).toLocalDateTime().toKotlinLocalDateTime()
}