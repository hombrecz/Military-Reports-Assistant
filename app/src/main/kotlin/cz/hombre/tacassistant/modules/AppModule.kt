package cz.hombre.tacassistant.modules

import cz.hombre.tacassistant.services.DateTimeService
import cz.hombre.tacassistant.services.EncodingService
import cz.hombre.tacassistant.services.LocationService
import cz.hombre.tacassistant.services.PreferencesService
import cz.hombre.tacassistant.services.impl.DateTimeServiceImpl
import cz.hombre.tacassistant.services.impl.EncodingServiceImpl
import cz.hombre.tacassistant.services.impl.LocationServiceImpl
import cz.hombre.tacassistant.services.impl.PreferencesServiceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule: Module = applicationContext {
    provide { DateTimeServiceImpl() as DateTimeService }
    provide { LocationServiceImpl(androidApplication().baseContext, get()) as LocationService }
    provide { PreferencesServiceImpl(androidApplication().baseContext) as PreferencesService }
    provide { EncodingServiceImpl(get()) as EncodingService }
}