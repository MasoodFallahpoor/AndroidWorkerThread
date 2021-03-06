package ir.fallahpoor.workerthread

import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

class HandcraftedWorkerThread(threadName: String) : Thread(threadName) {

    private val isAlive: AtomicBoolean = AtomicBoolean(true)
    private val taskQueue: ConcurrentLinkedQueue<Runnable> = ConcurrentLinkedQueue()

    init {
        start()
    }

    override fun run() {
        while (isAlive.get()) {
            val task: Runnable? = taskQueue.poll()
            task?.run()
        }
    }

    fun execute(task: Runnable): HandcraftedWorkerThread {
        taskQueue.add(task)
        return this
    }

    fun quit() {
        isAlive.set(false)
    }

}