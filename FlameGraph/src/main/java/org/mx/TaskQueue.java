package org.mx;

import java.util.Collection;

/**
 * @author FizzPu
 * @since 2024/2/26 18:41
 */
public interface TaskQueue extends Iterable<TaskQueue.TaskNode> {
    interface TaskNode {
        SampleTask getSampleTask();

        TaskNode getNextNode();

        void setNextNode(TaskNode node);
    }

    void enqueue(TaskNode taskNode);

    void remove(TaskNode taskNode);
}
