package ltd.kaizo.mynews.Model.Utils;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * The type Leak canary application.
 */
public class LeakCanaryApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

}
