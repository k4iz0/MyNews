package ltd.kaizo.mynews.controller.ui.notification

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator

/**
 * The type Android job creator.
 */
class AndroidJobCreator : JobCreator {
    override fun create(tag: String): Job? {
        return NytShowNotificationJob()
    }
}
