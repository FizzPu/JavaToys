package org.mx;

/**
 * @author FizzPu
 * @since 2024/2/26 15:36
 */
public interface StackTraceSampler {
    /**
     * 开启采样
     */
    void startUp();

    /**
     * 开启采样
     */
    void register(SampleTask simpleTask);

    /**
     * 采样结束
     */
    void closeUp();
}
