package com.mnkregisterhours.models.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable: IntIdTable(){
    val username = varchar("username",150)
    val pin = varchar("pin",4)
}
