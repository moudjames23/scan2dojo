package io.github.moudjames23.scan2dojo;

import io.github.moudjames23.scan2dojo.exception.CommandNotFoundException;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.result.CommandNotFoundMessageProvider;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}


	@Bean
	public OkHttpClient okHttpClient()
	{
		return new OkHttpClient.Builder()
				.connectTimeout(60, TimeUnit.SECONDS)
				.readTimeout(75, TimeUnit.SECONDS)
				.writeTimeout(120, TimeUnit.SECONDS)
				.build();
	}

	@Bean
	public CommandNotFoundMessageProvider provider()
	{
		return new CommandNotFoundException();
	}
}
