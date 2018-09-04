package ltd.kaizo.mynews.Model.Utils.Androidjob;

import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;

public class NytShowNotificationJob extends Job {
    public static final String JOB_TAG = "NytShowNotificationJobTag";
    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        Log.i("jobTest", "Test OK! "+params.getTag());
        return Result.SUCCESS;
    }
}
