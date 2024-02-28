package org.mx;

/**
 * @author FizzPu
 * @since 2024/2/28 11:09
 */
public class StackTraceContext {
    private final StackTraceElement[] stackTraceElements;

    public StackTraceContext(StackTraceElement[] stackTraceElements) {
        this.stackTraceElements = stackTraceElements;
    }

    @Override
    public String toString() {
        StringBuilder stack = new StringBuilder();
        for (int i = stackTraceElements.length - 1; i >= 0; i--) {
            stack.append(stackTraceElements[i].toString());
            stack.append(";");
        }
        stack.append(" 1");
        return stack.toString();
    }
}
