package org.mx;

/**
 * @author FizzPu
 * @since 2024/2/26 17:13
 */
public interface SimpleTaskPool extends Iterable<SampleTask> {
    /**
     * 将采样任务加到采样池
     * @param simpleTask 采样任务
     */
    void registerTask(SampleTask simpleTask);
}
