
package org.gladcherry.map.di


import org.gladcherry.map.view.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MapViewModel(get()) }
}
