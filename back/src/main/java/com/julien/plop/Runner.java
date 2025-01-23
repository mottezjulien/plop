package com.julien.plop;

import com.julien.plop.auth.Auth;
import com.julien.plop.game.GameGeneratorUseCase;
import com.julien.plop.game.GameRepository;
import com.julien.plop.template.TemplateRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Runner {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Bean
	public Auth auth() {
		return new Auth();
	}

	@Bean
	public GameGeneratorUseCase gameGeneratorUseCase(GameGeneratorUseCase.DataOutput dataOutput) {
		return new GameGeneratorUseCase(dataOutput);
	}

	@Bean
	public GameRepository gameRepository() {
		return new GameRepository.InMemory();
	}

	@Bean
	public TemplateRepository templateRepository() {
		return new TemplateRepository.InMemory();
	}

}
