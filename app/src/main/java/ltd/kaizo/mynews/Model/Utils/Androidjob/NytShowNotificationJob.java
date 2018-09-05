package ltd.kaizo.mynews.Model.Utils.Androidjob;

import android.app.Notification;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import ltd.kaizo.mynews.Model.NytArticleConverter;
import ltd.kaizo.mynews.Model.NytSearchArticleAPI.NytSearchArticleApiData;
import ltd.kaizo.mynews.Model.SearchQuery;
import ltd.kaizo.mynews.Model.Utils.NotificationHelper;
import ltd.kaizo.mynews.Model.Utils.NytStream;

import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.Key_SEARCHQUERY_NOTIFICATION;
import static ltd.kaizo.mynews.Model.Utils.DataRecordManager.read;
import static ltd.kaizo.mynews.Model.Utils.NotificationHelper.NYT_CHANNEL_ID;

public class NytShowNotificationJob extends Job {
    public static final String JOB_TAG = "NytShowNotificationJobTag";
    private SearchQuery searchQuery;
    private String gsonStr = "";
    private Disposable disposable;
    private NytArticleConverter nytArticleConverter;

    public static int schedulePeriodicJob() {
        return new JobRequest.Builder(NytShowNotificationJob.JOB_TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15))
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

    public static void cancelJob(int jobId) {
        JobManager.instance().cancel(jobId);
    }

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {

        this.searchQuery = this.getSearchQueryFromSharedPreferences();
        Log.i("jobInfo", "u search for " + searchQuery.getQueryTerms()
                + "\n and u checked :" + searchQuery.getQueryFields()
        );
        executeStreamFetchSearchArticleFromNotification();
        return Result.SUCCESS;
    }

    private SearchQuery getSearchQueryFromSharedPreferences() {
        Gson gson = new Gson();
        return gson.fromJson(read(Key_SEARCHQUERY_NOTIFICATION, this.gsonStr), SearchQuery.class);
    }

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

    private void configureNotification() {
        NotificationHelper notificationHelper = new NotificationHelper(getContext());
        NotificationCompat.Builder builder = notificationHelper.notificationBuilder();
        notificationHelper.getManager().notify(3, builder.build());
    }


}
