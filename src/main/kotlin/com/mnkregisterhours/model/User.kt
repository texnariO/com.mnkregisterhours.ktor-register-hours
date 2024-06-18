package com.mnkregisterhours.model

import java.util.Date
import java.util.UUID

data class User(
    val id: UUID,
    val userName: String,
    val pin: String
)


data class UserWork(
    val idRecord: UUID,
    val idUserName: String,
    val timeStartWork: Date,
    val photoStartWork: String,
    val timeCanselWork: Date,
    val photoCanselWork: String
)