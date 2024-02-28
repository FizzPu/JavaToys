package org.mx;

/**
 * @author FizzPu
 * @since 2024/2/26 17:14
 */
public class BasedThreadSimpleTask implements SampleTask {
    private TaskState taskState = TaskState.UN_STATED;

    private final Thread sampleThread;
    private final StackTraceCollector stackTraceCollector;

    public BasedThreadSimpleTask(Thread sampleThread, StackTraceCollector stackTraceCollector) {
        this.sampleThread = sampleThread;
        this.stackTraceCollector = stackTraceCollector;
    }

    public BasedThreadSimpleTask() {
        this.sampleThread = Thread.currentThread();
        stackTraceCollector = new BaseFileStorageCollector();
    }

    @Override
    public void sampleTask() {
        if (sampleThread.isAlive()) {
            taskState = TaskState.RUNNING;
            StackTraceElement[] stackTraceElement = sampleThread.getStackTrace();
            StackTraceContext stackTraceContext = new StackTraceContext(stackTraceElement);

            stackTraceCollector.collect(stackTraceContext);
        } else {
            taskState = TaskState.COMPLETED;
        }
    }

    @Override
    public boolean isComplete() {
        return TaskState.COMPLETED.equals(taskState);
    }

    @Override
    public boolean isUnstarted() {
        return TaskState.UN_STATED.equals(taskState);
    }
}

