package cz.hombre.militaryReportsAssistant.modules

import cz.hombre.militaryReportsAssistant.services.DatabaseService
import cz.hombre.militaryReportsAssistant.services.DateTimeService
import cz.hombre.militaryReportsAssistant.services.EncodingService
import cz.hombre.militaryReportsAssistant.services.LocaleService
import cz.hombre.militaryReportsAssistant.services.LocationService
import cz.hombre.militaryReportsAssistant.services.PreferencesService
import cz.hombre.militaryReportsAssistant.services.impl.DatabaseServiceImpl
import cz.hombre.militaryReportsAssistant.services.impl.DateTimeServiceImpl
import cz.hombre.militaryReportsAssistant.services.impl.EncodingServiceImpl
import cz.hombre.militaryReportsAssistant.services.impl.LocaleServiceImpl
import cz.hombre.militaryReportsAssistant.services.impl.LocationServiceImpl
import cz.hombre.militaryReportsAssistant.services.impl.PreferencesServiceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule: Module = applicationContext {
    bean { PreferencesServiceImpl(androidApplication().baseContext) as PreferencesService }
    bean { DateTimeServiceImpl(get()) as DateTimeService }
    bean { LocationServiceImpl(androidApplication().baseContext, get()) as LocationService }
    bean { EncodingServiceImpl(get()) as EncodingService }
    bean { LocaleServiceImpl(get()) as LocaleService }
    bean { DatabaseServiceImpl(androidApplication().baseContext, get()) as DatabaseService }
}