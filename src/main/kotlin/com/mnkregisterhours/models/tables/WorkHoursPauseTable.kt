package com.mnkregisterhours.models.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object WorkHoursPauseTable: IntIdTable() {
    val userId = integer("user_id").references(UserTable.id)
    val timeStartPause = datetime("time_start_pause").nullable()
    val timeStopPause = datetime("time_stop_pause").nullable()
}