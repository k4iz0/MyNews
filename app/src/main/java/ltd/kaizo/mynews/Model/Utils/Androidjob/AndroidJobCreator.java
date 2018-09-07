package ltd.kaizo.mynews.Model.Utils.Androidjob;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * The type Android job creator.
 */
public class AndroidJobCreator implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        return new NytShowNotificationJob();
    }
}
