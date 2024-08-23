package io.github.moudjames23.scan2dojo.exception;


import io.github.moudjames23.scan2dojo.util.MessageUtil;
import org.springframework.shell.result.CommandNotFoundMessageProvider;

import static io.github.moudjames23.scan2dojo.util.MessageUtil.RED;


public class CommandNotFoundException implements CommandNotFoundMessageProvider {


    @Override
    public String apply(ProviderContext context) {
        String message = "Command '" + context.text() + "' not found. Please use 'help' to see the list of available commands.";
        MessageUtil.printlnWithBorder(RED, message);

        return "";
    }
}
