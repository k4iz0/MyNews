package ltd.kaizo.mynews.Model.Utils.Androidjob;

import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;

class NytShowNotificationJob extends Job {
    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        Log.i("jobTest", "Test OK! "+params.getTag());
        return Result.SUCCESS;
    }
}
