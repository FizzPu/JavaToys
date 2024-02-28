package org.mx;

import java.util.concurrent.CompletableFuture;

/**
 * @author FizzPu
 * @since 2024/2/28 11:09
 */
public class BaseFileStorageCollector implements StackTraceCollector {
    private String directPath = "/Users/FizzPu/Desktop/FizzALL/project/FizzProject/JavaToys/FlameGraph/Runtime/";

    public BaseFileStorageCollector(String directPath) {
        this.directPath = directPath;
    }

    public BaseFileStorageCollector() {
    }

    @Override
    public void collect(StackTraceContext stackTraceContext) {
        String stack = stackTraceContext.toString();
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(stack);
            return null;
        });
    }
}
