package cz.hombre.tacassistant.di

import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.DateTimeServiceImpl
import cz.hombre.tacassistant.services.LocationService
import cz.hombre.tacassistant.services.LocationServiceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule : Module = applicationContext {
    provide {  }
    provide { DateTimeServiceImpl() as DateTimeService }
    provide { LocationServiceImpl(androidApplication().baseContext) as LocationService }
}