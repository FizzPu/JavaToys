package org.mx;

import java.util.Iterator;

/**
 * @author FizzPu
 * @since 2024/2/26 18:42
 */
public class SampleTaskQueue implements TaskQueue {
    public static final SampleTaskQueue DEFAULT = new SampleTaskQueue();

    private SampleTaskNode tail;
    private final SampleTaskNode head = new SampleTaskNode();

    static class SampleTaskNode implements TaskNode {
        private SampleTask task;
        private TaskNode next;

        public SampleTaskNode() {
        }

        public SampleTaskNode(SampleTask task) {
            this.task = task;
        }

        @Override
        public SampleTask getSampleTask() {
            return task;
        }

        @Override
        public TaskNode getNextNode() {
            return next;
        }

        @Override
        public void setNextNode(TaskNode node) {
            next = node;
        }
    }

    @Override
    public Iterator<TaskNode> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<TaskNode> {
        TaskNode node = head.next;

        @Override
        public boolean hasNext() {
            return  (node != null);
        }

        @Override
        public TaskNode next() {
            TaskNode ret = node;
            node = node.getNextNode();
            return ret;
        }
    }

    @Override
    public void enqueue(TaskNode taskNode) {
        SampleTaskNode node = (SampleTaskNode) taskNode;
        if (tail == null) {
            tail = node;
            head.next = tail;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    @Override
    public void remove(TaskNode taskNode) {
        TaskNode pre = head;
        TaskNode sampleTaskNode = head.next;
        while (sampleTaskNode != null) {
            if (sampleTaskNode == taskNode) {
                pre.setNextNode(sampleTaskNode.getNextNode());
            } else {
                pre = pre.getNextNode();
                sampleTaskNode = sampleTaskNode.getNextNode();
            }
        }
    }
}
