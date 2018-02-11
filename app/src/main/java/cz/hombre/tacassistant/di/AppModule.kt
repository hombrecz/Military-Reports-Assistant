package cz.hombre.tacassistant.di

import cz.hombre.tacassistant.services.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule : Module = applicationContext {
    provide { DateTimeServiceImpl() as DateTimeService }
    provide { LocationServiceImpl(androidApplication().baseContext) as LocationService }
    provide { ReportFormServiceImpl() as ReportFormService}
}