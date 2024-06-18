package com.mnkregisterhours.repository

import com.mnkregisterhours.model.User
import java.util.*

class UserReposiory {

    private val users = mutableListOf<User>()

    fun findAll(): List<User> = users
    fun findById(id: UUID): User? = users.firstOrNull { it.id == id }

    fun findByUsername(userName: String): User? =
        users.firstOrNull { it.userName == userName }



}