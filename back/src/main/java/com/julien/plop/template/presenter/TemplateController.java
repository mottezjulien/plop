package com.julien.plop.template.presenter;

import com.julien.plop.template.persistence.TemplateEntity;
import com.julien.plop.template.persistence.TemplateRepository;
import com.julien.plop.tools.StringTools;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateRepository repository;

    public TemplateController(TemplateRepository repository) {
        this.repository = repository;
    }


    @GetMapping({"", "/"})
    public TemplateResponseDTO findByCode(@RequestParam("code") String code) {
        if(StringTools.isFilled(code)) {
            List<TemplateEntity> entities = repository.findByCode(code);
            if(entities.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Template not found");
            }
            TemplateEntity entity = entities.stream().findFirst().orElseThrow();
            return TemplateResponseDTO.fromEntity(entity);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code is required");
    }

}
