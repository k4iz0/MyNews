package ltd.kaizo.mynews.controller.ui.base

import android.app.Application
import ltd.kaizo.mynews.injection.nytModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin { androidContext(this@MainApplication)
            modules(nytModule) }

//        uncomment for debug purpose
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
    }
}