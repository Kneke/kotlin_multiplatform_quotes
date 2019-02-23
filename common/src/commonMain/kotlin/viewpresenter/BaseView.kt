package viewpresenter

interface BaseView {
    fun showError(error: Throwable)

    fun showLoadingSpinner(visibility: Boolean)
}