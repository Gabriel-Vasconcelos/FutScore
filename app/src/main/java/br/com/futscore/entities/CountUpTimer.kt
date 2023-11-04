import android.os.Handler

class CountUpTimer(private val onTickCallback: (Long) -> Unit) {
    private val handler = Handler()
    private var elapsedTime = 0L
    private var isRunning = false

    fun start() {
        isRunning = true
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isRunning) {
                    elapsedTime++
                    onTickCallback.invoke(elapsedTime)
                    handler.postDelayed(this, 1000)
                }
            }
        }, 1000)
    }

    fun stop() {
        isRunning = false
    }
}
