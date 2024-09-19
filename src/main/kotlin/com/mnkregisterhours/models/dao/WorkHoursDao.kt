package com.mnkregisterhours.models.dao

import com.mnkregisterhours.models.data.SendWorkHoursePause
import com.mnkregisterhours.models.data.StopWorkHoursePause

import com.mnkregisterhours.models.tables.WorkHoursPauseTable
import com.mnkregisterhours.models.tables.WorkHoursTable
import com.mnkregisterhours.util.javaTimeToKotlinxDateTime
import com.mnkregisterhours.util.kotlinxDateTimeToJavaTime
import io.ktor.http.*
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction


@Serializable
data class WorkHours(
    val id: Int,
    val userId: Int,
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val timeStart: LocalDateTime?,
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    val timeStop: LocalDateTime?,
    val photoStart: String?,
    val photoStopWork: String?,
    val isPaused: Boolean
)

object WorkHoursDao{
    fun getInformationFROMID(id: Int): WorkHours? = transaction {
        WorkHoursTable.select{
            (WorkHoursTable.userId eq id) and (WorkHoursTable.timeStart neq null) and (WorkHoursTable.timeStop eq null)}.mapNotNull { toWorkHour(it) }.singleOrNull()
        }

    fun insertStartPause(pause: SendWorkHoursePause) = transaction {
        WorkHoursPauseTable.insert {
            it[userId] = pause.userid
            it[timeStartPause] = kotlinxDateTimeToJavaTime(pause.timeStartPause!!)
        }
    }
    fun insertStopPause(pause: StopWorkHoursePause) = transaction {
        WorkHoursPauseTable.update({WorkHoursPauseTable.id eq pause.idPause}) {it[timeStopPause] = kotlinxDateTimeToJavaTime(pause.timeStopPause!!)}
    }

    fun searchActualPause(id: Int) = transaction {
        WorkHoursPauseTable.select{
            (WorkHoursPauseTable.userId eq id) and (WorkHoursPauseTable.timeStartPause neq null) and (WorkHoursPauseTable.timeStopPause eq null)}.mapNotNull{ toIDPause(it)}.singleOrNull()
    }
    fun updatePause(id: Int, paused: Boolean) = transaction {
        WorkHoursTable.update({(WorkHoursTable.userId eq id)and(WorkHoursTable.timeStart neq null) and (WorkHoursTable.timeStop eq null)}) {it[isPaused] = paused}
    }

    private fun toIDPause(row: ResultRow): Int = row[WorkHoursPauseTable.id].value
    private fun toWorkHour(row: ResultRow): WorkHours =
        WorkHours(
            id = row[WorkHoursTable.id].value,
            userId = row[WorkHoursTable.userId],
            timeStart = if(row[WorkHoursTable.timeStart]!=null) javaTimeToKotlinxDateTime(row[WorkHoursTable.timeStart]!!)else null,
            timeStop =  if(row[WorkHoursTable.timeStop]!= null )javaTimeToKotlinxDateTime(row[WorkHoursTable.timeStop]!!) else null,
            photoStart = row[WorkHoursTable.photoStart],
            photoStopWork = row[WorkHoursTable.photoStopWork],
            isPaused = row[WorkHoursTable.isPaused]
        )
    }