package ltd.kaizo.mynews.Model.notification.Androidjob;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleApiData;
import ltd.kaizo.mynews.Model.SearchQuery;
import ltd.kaizo.mynews.Model.notification.NotificationHelper;
import ltd.kaizo.mynews.Model.repository.stream.NytStream;

import static ltd.kaizo.mynews.Utils.DataRecordManager.KEY_SEARCHQUERY_NOTIFICATION;
import static ltd.kaizo.mynews.Utils.DataRecordManager.getSearchQueryFromSharedPreferences;

    /**
     * The type Nyt show notification job.
     */
    public class NytShowNotificationJob extends Job {
    /**
     * The constant JOB_TAG.
     */
    public static final String JOB_TAG = "NytShowNotificationJobTag";
    /**
     * The Search query.
     */
    private SearchQuery searchQuery;
    /**
     * The Disposable.
     */
    private Disposable disposable;

    /**
     * Schedule daily job int.
     *
     * @return the int jobID
     */
    public static int schedulePeriodicJob() {
        return new JobRequest.Builder(NytShowNotificationJob.JOB_TAG)
                .setPeriodic(TimeUnit.DAYS.toMillis(1))
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

    /**
     * Cancel job.
     *
     * @param jobId the job id
     */
    public static void cancelJob(int jobId) {
        JobManager.instance().cancel(jobId);
    }

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {

        this.searchQuery = getSearchQueryFromSharedPreferences(KEY_SEARCHQUERY_NOTIFICATION);

        executeStreamFetchSearchArticleFromNotification();
        return Result.SUCCESS;
    }


    /**
     * Execute stream fetch search article from notification.
     */
    private void executeStreamFetchSearchArticleFromNotification() {
        this.disposable = NytStream.streamFetchSearchArticle(
                searchQuery.getQueryTerms(),
                searchQuery.getQueryFields(),
                searchQuery.getBeginDate(),
                searchQuery.getEndDate())
                .subscribeWith(new DisposableObserver<NytSearchArticleApiData>() {
                    @Override
                    public void onNext(NytSearchArticleApiData nytSearchArticleApiData) {
                        if (nytSearchArticleApiData.getNytSearchArticleResponse().getNytSearchArticleDocs().size() > 0) {
                            //launch the notification
                            configureNotification();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("StreamInfo", "search error : " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("StreamInfo", "search complete");
                    }
                });

    }

    /**
     * Configure notification.
     */
    private void configureNotification() {
        NotificationHelper notificationHelper = new NotificationHelper(getContext());
        NotificationCompat.Builder builder = notificationHelper.notificationBuilder();
        notificationHelper.getManager().notify(3, builder.build());
    }


}
