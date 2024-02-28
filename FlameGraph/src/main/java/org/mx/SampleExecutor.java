package org.mx;

import java.util.concurrent.Executor;

/**
 * @author FizzPu
 * @since 2024/2/26 17:48
 */
public class SampleExecutor implements Executor {
    public static final SampleExecutor DEFAULT = new SampleExecutor();

    @Override
    public void execute(Runnable command) {
        Thread thread = new Thread(command);
        thread.setDaemon(true);
        thread.start();
    }
}
