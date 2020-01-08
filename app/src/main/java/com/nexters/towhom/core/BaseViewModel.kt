package com.nexters.towhom.core


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val job = SupervisorJob()


    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass job, you can cancel all coroutines
     * launched by uiScope by calling job.cancel()
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + job)


    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    fun launchDataLoad() {
        uiScope.launch {
            sortList()
            // Modify UI
        }
    }

    suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
    }
}