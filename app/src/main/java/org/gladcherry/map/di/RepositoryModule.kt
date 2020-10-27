
package org.gladcherry.map.di

import org.gladcherry.map.view.map.MapRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MapRepository(get()) }
}
