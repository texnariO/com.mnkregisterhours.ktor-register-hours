package com.mnkregisterhours



import com.mnkregisterhours.models.tables.UserTable
import com.mnkregisterhours.models.tables.WorkHoursPauseTable
import com.mnkregisterhours.models.tables.WorkHoursTable
import com.mnkregisterhours.routes.getAllUsers
import com.mnkregisterhours.routes.getInfoAboutWorkingHoursUsingID
import com.mnkregisterhours.routes.getUserFromID
import com.mnkregisterhours.routes.getUserFromPin
import com.sun.tools.javac.file.Locations
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


/*
fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
*/
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}
fun Application.module() {
    install(ContentNegotiation) {
        json(Json{
            prettyPrint = true
            isLenient = true
        }
        )
    }
   Database.connect(
       url  = "jdbc:postgresql://localhost:5432/postgres",
       driver = "org.postgresql.Driver",
       user = "postgres",
       password = "admin"
   )

    transaction {
        SchemaUtils.create(UserTable, WorkHoursTable, WorkHoursPauseTable)
    }

    routing {
        getAllUsers()
        getUserFromPin()
        getUserFromID()
        getInfoAboutWorkingHoursUsingID()
    }
}
