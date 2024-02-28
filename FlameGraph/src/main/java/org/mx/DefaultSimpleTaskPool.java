package org.mx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * must be thread safe
 * @author FizzPu
 * @since 2024/2/26 17:15
 */
public class DefaultSimpleTaskPool implements SimpleTaskPool {
    public static DefaultSimpleTaskPool DEFAULT = new DefaultSimpleTaskPool();

    private final List<SampleTask> poolList = new ArrayList<>();
    private  final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    @Override
    public void registerTask(SampleTask simpleTask) {
        Objects.requireNonNull(simpleTask, "sample task requires non null value");
        reentrantReadWriteLock.writeLock().lock();
        try {
            poolList.add(simpleTask);
        } finally {
           reentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override
    public Iterator<SampleTask> iterator() {
        return poolList.iterator();
    }
}
