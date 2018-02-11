package cz.hombre.tacassistant.di

import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.DateTimeServiceImpl
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule : Module = applicationContext {
    provide { DateTimeServiceImpl() as DateTimeService }
}