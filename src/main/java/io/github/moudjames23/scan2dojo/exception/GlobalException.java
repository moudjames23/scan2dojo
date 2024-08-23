package io.github.moudjames23.scan2dojo.exception;

import static io.github.moudjames23.scan2dojo.util.MessageUtil.RED;
import static io.github.moudjames23.scan2dojo.util.MessageUtil.printlnWithBorder;

public class GlobalException extends RuntimeException {
    public GlobalException(String message) {
        super(message);

        printlnWithBorder(RED, message);
    }
}
