package com.mnkregisterhours.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime

fun kotlinxDateTimeToJavaTime(kotlinxTime: LocalDateTime): java.time.LocalDateTime {
    return kotlinxTime.toJavaLocalDateTime()
}