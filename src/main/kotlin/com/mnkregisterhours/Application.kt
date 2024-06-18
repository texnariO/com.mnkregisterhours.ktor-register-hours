package com.mnkregisterhours


import com.mnkregisterhours.plugins.configureDatabases
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    //configureDatabases()
    routing {
        get("/"){
            call.respondText ("Hello, Ktor server is work")
        }
    }
}
