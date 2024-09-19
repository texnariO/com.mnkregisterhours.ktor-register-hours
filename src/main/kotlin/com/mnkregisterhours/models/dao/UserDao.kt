package com.mnkregisterhours.models.dao

import com.mnkregisterhours.models.tables.UserTable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
@Serializable
data class User(val id: Int, val name: String, val pin: String)

object UserDao{
    fun getAll(): List<User> = transaction {
        UserTable.selectAll().map{ toUser(it)}
    }

    fun getUserFromID(id: Int): User? = transaction {
        UserTable.select{ UserTable.id eq id}.mapNotNull { toUser(it) }.singleOrNull()
    }

    fun getUserFromPin(pin: String): User? = transaction {
        UserTable.select{ UserTable.pin eq pin}.mapNotNull { toUser(it) }.singleOrNull()
    }

    private fun toUser(row: ResultRow): User =
        User(
            id = row[UserTable.id].value,
            name = row[UserTable.username],
            pin = row[UserTable.pin]
        )


}