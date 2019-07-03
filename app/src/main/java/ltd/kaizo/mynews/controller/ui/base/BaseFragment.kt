package ltd.kaizo.mynews.controller.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife

/**
 * The type Base fragment.
 */
abstract class BaseFragment : Fragment() {

    /**
     * Gets fragment layout.
     *
     * @return the fragment layout
     */
    protected abstract val fragmentLayout: Int

    /**
     * Configure design.
     */
    protected abstract fun configureDesign()

    /**
     * Update design.
     */
    protected abstract fun updateDesign()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragmentLayout, container, false)
        this.configureDesign()
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.configureViewModel()
        this.configureObserver()
        this.updateDesign()
    }
    abstract fun configureViewModel()
    abstract fun configureObserver()

}
