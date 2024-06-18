package com.mnkregisterhours.schema

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DB {
    var ds: HikariDataSource

    init {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
            username = "postgres"
            password = "admin"
            maximumPoolSize = 50
            minimumIdle = 15
            idleTimeout = 5000
        }
        println("I in")
        ds = HikariDataSource(config)
    }
}