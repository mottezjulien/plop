package com.julien.plop;

import com.julien.plop.auth.AuthUseCase;
import com.julien.plop.game.domain.GameGeneratorUseCase;
import com.julien.plop.template.domain.TemplateInitUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup(TemplateInitUseCase.OutPort outPort) {
        TemplateInitUseCase templateInitUseCase = new TemplateInitUseCase(outPort);
        System.out.println("hello world, I have just started up");
    }

    @Bean
    public AuthUseCase authUseCase(AuthUseCase.OutPort port) {
        return new AuthUseCase(port);
    }

    @Bean
    public GameGeneratorUseCase gameGeneratorUseCase(GameGeneratorUseCase.DataOutput dataOutput) {
        return new GameGeneratorUseCase(dataOutput);
    }

}
