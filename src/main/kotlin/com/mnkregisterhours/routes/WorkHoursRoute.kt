package com.mnkregisterhours.routes

import com.mnkregisterhours.models.dao.UserDao
import com.mnkregisterhours.models.dao.WorkHoursDao
import com.mnkregisterhours.models.data.InformationAboutWorkingHours
import com.mnkregisterhours.models.data.SendWorkHoursePause
import com.mnkregisterhours.models.data.StopWorkHoursePause
import com.mnkregisterhours.models.tables.WorkHoursPauseTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.*
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local


fun Route.getInfoAboutWorkingHoursUsingID(){
    get("/getUserInfoAboutWorkingHours/{id}"){
        val userID = call.parameters["id"]?: return@get call.respondText(
            "Nie istnieje użytkownika",
            status = HttpStatusCode.BadRequest
        )
        val userIDInt = userID.toInt()
        val information = WorkHoursDao.getInformationFROMID(userIDInt)
        if(information == null){
            val infoAboutUser = InformationAboutWorkingHours(false,false, LocalDateTime.parse("2023-06-19T13:45:30"),"" )
            call.respond(infoAboutUser)
        }else{
            val userinfo = UserDao.getUserFromID(information.userId)
            val infoAboutUser = InformationAboutWorkingHours(true,information.isPaused,information.timeStart,userinfo!!.name)
            call.respond(infoAboutUser)
        }
    }
    put("stopPause/{id}"){
        val userID = call.parameters["id"]?: return@put call.respondText(
            "Nie istnieje użytkownika",
            status = HttpStatusCode.BadRequest
        )
        val userIDInt = userID.toInt()
        val searchActualPause = WorkHoursDao.searchActualPause(userIDInt)
        if(searchActualPause==null)
            return@put call.respondText(
                "Nie jest na przerwie",
                status = HttpStatusCode.BadRequest
            )
        val time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val info = WorkHoursDao.insertStopPause(StopWorkHoursePause(searchActualPause,time))
        val stopPause = WorkHoursDao.updatePause(userIDInt, false)
        call.respond(true)
        }


    post("/startPause/{id}"){
        val userID = call.parameters["id"]?: return@post call.respondText(
            "Nie istnieje użytkownika",
            status = HttpStatusCode.BadRequest
        )
        val userIDInt = userID.toInt()
        val time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val info = WorkHoursDao.insertStartPause(SendWorkHoursePause(userIDInt,time))
        val startPause = WorkHoursDao.updatePause(userIDInt,true)
        call.respond(true)
    }
}