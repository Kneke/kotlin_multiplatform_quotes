package de.kneke.common.viewmodel

class ViewModelObservable<T> {

    private var triggerFunction: ((T?) -> Unit)? = null
    var value: T? = null
        set(value) {
            field = value
            triggerFunction?.let { field?.let(it) }
        }

    fun watch(block: (T?) -> Unit) {
        triggerFunction = block
        value?.let(triggerFunction!!)
    }

    fun unwatch() {
        triggerFunction = null
    }
}