package com.mnkregisterhours.routes

import com.mnkregisterhours.models.dao.UserDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllUsers(){
    get("/users"){
        val users = UserDao.getAll()
        call.respond(users)
    }
}
fun Route.getUserFromID(){
    get("/getUserFromID/{id}"){
        val userID = call.parameters["id"]?: return@get call.respondText(
            "Nie istnieje użytkownika",
            status = HttpStatusCode.BadRequest
        )
        val userIDInt = userID.toInt()
        val user = UserDao.getUserFromID(userIDInt)
        if(user!=null){
            call.respond(user)
        }else{
            call.respondText("Użytkownik nie znależiony", status = HttpStatusCode.BadRequest)
        }
    }
}


fun Route.getUserFromPin(){
    get("/getUserFromPin/{pin}"){
        val userPin = call.parameters["pin"]?: return@get call.respondText(
            "Nie podano żadnego PIN",
            status = HttpStatusCode.BadRequest
        )
        val user = UserDao.getUserFromPin(userPin)
        if(user!=null){
            call.respond(user)
        }else{
            call.respondText("Użytkownik nie znależiony", status = HttpStatusCode.BadRequest)
        }
    }
}
