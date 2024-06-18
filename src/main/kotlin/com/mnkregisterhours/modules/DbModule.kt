package com.mnkregisterhours.modules

import com.mnkregisterhours.schema.DB
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides


@Module
class DbModule {
    @Provides
    fun provideDB(): HikariDataSource{
        return DB.ds
    }
}