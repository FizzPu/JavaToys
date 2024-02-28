package org.mx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * @author FizzPu
 * @since 2024/2/26 15:39
 */

public class DefaultStackTrackSimpler implements StackTraceSampler {
    private int DEFAULT_SAMPLE_INTERVAL_MILLIS = 20;

    private static final Logger logger = LoggerFactory.getLogger(DefaultStackTrackSimpler.class.getName());

    private final TaskQueue taskQueue;
    private final Executor simpleExecutor;
    private final SimpleTaskPool defaultSimpleTaskPool;

    private int sampleInterval = DEFAULT_SAMPLE_INTERVAL_MILLIS;

    public DefaultStackTrackSimpler(TaskQueue taskQueue, Executor simpleExecutor, SimpleTaskPool defaultSimpleTaskPool) {
        this.taskQueue = taskQueue;
        this.simpleExecutor = simpleExecutor;
        this.defaultSimpleTaskPool = defaultSimpleTaskPool;
    }

    public DefaultStackTrackSimpler() {
        this.taskQueue = SampleTaskQueue.DEFAULT;
        this.simpleExecutor = SampleExecutor.DEFAULT;
        this.defaultSimpleTaskPool = DefaultSimpleTaskPool.DEFAULT;
    }

    private final class SampleCommand implements Runnable {
        @Override
        public void run() {
            while (true) {
                for (TaskQueue.TaskNode taskNode : taskQueue) {
                    org.mx.SampleTask task = taskNode.getSampleTask();
                    if (task.isComplete()) {
                        taskQueue.remove(taskNode);
                    }
                    task.sampleTask();
                }

                for (SampleTask sampleTask : defaultSimpleTaskPool) {
                    if (sampleTask.isUnstarted()) {
                        TaskQueue.TaskNode taskNode = new SampleTaskQueue.SampleTaskNode(sampleTask);
                        taskQueue.enqueue(taskNode);
                    }
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void startUp() {
        logger.info("start simple...");
        simpleExecutor.execute(new SampleCommand());
    }

    @Override
    public void register(org.mx.SampleTask simpleTask) {
        defaultSimpleTaskPool.registerTask(simpleTask);
    }

    @Override
    public void closeUp() {
        logger.info("end simple...");
    }

}
