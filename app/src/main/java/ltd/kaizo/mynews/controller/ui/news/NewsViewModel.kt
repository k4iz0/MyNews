package ltd.kaizo.mynews.controller.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ltd.kaizo.mynews.utils.DataRecordManager.read
import ltd.kaizo.mynews.utils.KEY_SECTION

class NewsViewModel : ViewModel() {
    val message = MutableLiveData<String>()
    val section = MutableLiveData<String>()

    init {
        section.value = read(KEY_SECTION,"world")
    }

}