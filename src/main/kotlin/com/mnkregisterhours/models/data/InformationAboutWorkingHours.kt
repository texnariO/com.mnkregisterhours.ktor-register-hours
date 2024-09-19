package com.mnkregisterhours.models.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.Serializable
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local


@Serializable
data class InformationAboutWorkingHours(
    val isStartWork: Boolean?,
    val isPaused: Boolean?,
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val howLongWorking: LocalDateTime?,
    val userName: String?
)
@Serializable
data class SendWorkHoursePause(
    val userid: Int,
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val timeStartPause: LocalDateTime?,
)

data class StopWorkHoursePause(
    val idPause: Int,
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val timeStopPause: LocalDateTime?
)