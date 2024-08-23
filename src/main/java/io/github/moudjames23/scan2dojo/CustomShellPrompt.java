package io.github.moudjames23.scan2dojo;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class CustomShellPrompt implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("scan2dojo:>");
    }
}
