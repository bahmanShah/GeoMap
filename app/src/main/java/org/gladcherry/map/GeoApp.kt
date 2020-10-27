
package org.gladcherry.map

import android.app.Application

import org.gladcherry.map.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class GeoApp : Application() {

  override fun onCreate() {
    super.onCreate()
      configureDI()
  }
  private fun configureDI() = startKoin {
    androidContext(this@GeoApp)
    modules(appComponent)
  }
}
