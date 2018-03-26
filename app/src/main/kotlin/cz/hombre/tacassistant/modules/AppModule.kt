package cz.hombre.tacassistant.modules

import cz.hombre.tacassistant.services.DatabaseService
import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.EncodingService
import cz.hombre.tacassistant.services.LocaleService
import cz.hombre.tacassistant.services.LocationService
import cz.hombre.tacassistant.services.PreferencesService
import cz.hombre.tacassistant.services.impl.DatabaseServiceImpl
import cz.hombre.tacassistant.services.impl.DateTimeServiceImpl
import cz.hombre.tacassistant.services.impl.EncodingServiceImpl
import cz.hombre.tacassistant.services.impl.LocaleServiceImpl
import cz.hombre.tacassistant.services.impl.LocationServiceImpl
import cz.hombre.tacassistant.services.impl.PreferencesServiceImpl
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