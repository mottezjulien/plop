package com.julien.plop;

import com.julien.plop.auth.domain.AuthUseCase;
import com.julien.plop.game.domain.GameGeneratorUseCase;
import com.julien.plop.template.domain.TemplateInitUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TemplateInitUseCase.OutPort outPort;

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        TemplateInitUseCase templateInitUseCase = new TemplateInitUseCase(outPort);
        templateInitUseCase.apply();
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
