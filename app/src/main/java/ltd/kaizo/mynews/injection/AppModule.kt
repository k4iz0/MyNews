package ltd.kaizo.mynews.injection

import ltd.kaizo.mynews.controller.ui.news.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val nytModule : Module = module {
    viewModel { NewsViewModel() }
}