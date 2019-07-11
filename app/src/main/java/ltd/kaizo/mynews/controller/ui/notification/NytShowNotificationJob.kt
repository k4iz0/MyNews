package ltd.kaizo.mynews.controller.ui.notification

import android.util.Log

import com.evernote.android.job.Job
import com.evernote.android.job.JobManager
import com.evernote.android.job.JobRequest

import java.util.concurrent.TimeUnit

import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import ltd.kaizo.mynews.model.NytSearchArticleAPI.NytSearchArticleApiData
import ltd.kaizo.mynews.model.SearchQuery
import ltd.kaizo.mynews.model.repository.stream.NytStream

import ltd.kaizo.mynews.utils.DataRecordManager.getSearchQueryFromSharedPreferences
import ltd.kaizo.mynews.utils.KEY_SEARCHQUERY_NOTIFICATION

/**
 * The type Nyt show notification job.
 */
class NytShowNotificationJob : Job() {
    /**
     * The Search query.
     */
    private var searchQuery: SearchQuery? = null
    /**
     * The Disposable.
     */
    private var disposable: Disposable? = null

    override fun onRunJob(params: Job.Params): Job.Result {

        this.searchQuery = getSearchQueryFromSharedPreferences(KEY_SEARCHQUERY_NOTIFICATION)

        executeStreamFetchSearchArticleFromNotification()
        return Job.Result.SUCCESS
    }


    /**
     * Execute stream fetch search article from notification.
     */
    private fun executeStreamFetchSearchArticleFromNotification() {
        this.disposable = NytStream.streamFetchSearchArticle(
                searchQuery!!.queryTerms,
                searchQuery!!.queryFields,
                searchQuery!!.beginDate!!,
                searchQuery!!.endDate!!)
                .subscribeWith(object : DisposableObserver<NytSearchArticleApiData>() {
                    override fun onNext(nytSearchArticleApiData: NytSearchArticleApiData) {
                        if (nytSearchArticleApiData.nytSearchArticleResponse.nytSearchArticleDocs.size > 0) {
                            //launch the notification
                            configureNotification()
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.i("StreamInfo", "search error : $e")
                    }

                    override fun onComplete() {
                        Log.i("StreamInfo", "search complete")
                    }
                })

    }

    /**
     * Configure notification.
     */
    private fun configureNotification() {
        val notificationHelper = NotificationHelper(context)
        val builder = notificationHelper.notificationBuilder()
        notificationHelper.getManager()!!.notify(3, builder.build())
    }

    companion object {
        /**
         * The constant JOB_TAG.
         */
        val JOB_TAG = "NytShowNotificationJobTag"

        /**
         * Schedule daily job int.
         *
         * @return the int jobID
         */
        fun schedulePeriodicJob(): Int {
            return JobRequest.Builder(JOB_TAG)
                    .setPeriodic(TimeUnit.DAYS.toMillis(1))
                    .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                    .setUpdateCurrent(true)
                    .build()
                    .schedule()
        }

        /**
         * Cancel job.
         *
         * @param jobId the job id
         */
        fun cancelJob(jobId: Int) {
            JobManager.instance().cancel(jobId)
        }
    }


}
