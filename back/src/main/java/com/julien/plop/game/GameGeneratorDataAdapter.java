package com.julien.plop.game;

import com.julien.plop.template.Template;
import com.julien.plop.template.TemplateRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GameGeneratorDataAdapter implements GameGeneratorUseCase.DataOutput {

    private final GameRepository repository;
    private final TemplateRepository templateRepository;

    public GameGeneratorDataAdapter(GameRepository repository, TemplateRepository templateRepository) {
        this.repository = repository;
        this.templateRepository = templateRepository;
    }

    @Override
    public Optional<Template> findByCode(String code) {
        return templateRepository.findByCode(code);
    }

    @Override
    public void save(Game game) {
        repository.save(game);
    }
}
