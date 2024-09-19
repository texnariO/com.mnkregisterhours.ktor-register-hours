package com.mnkregisterhours.models.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object WorkHoursTable: IntIdTable() {
    val userId = integer("user_id").references(UserTable.id)
    val timeStart = datetime("time_start_work").nullable()
    val timeStop = datetime("time_stop_work").nullable()
    val photoStart = varchar("photo_start_work",150).nullable()
    val photoStopWork = varchar("photo_stop_work",150).nullable()
    val isPaused = bool("ispaused").default(false)

}