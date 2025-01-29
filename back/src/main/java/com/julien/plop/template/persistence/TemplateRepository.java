package com.julien.plop.template.persistence;

import com.julien.plop.template.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<TemplateEntity, String> {

    @Query("FROM TemplateEntity template" +
            " JOIN template.scenario scenario" +
            " JOIN template.board board" +
            " WHERE template.code = :code")
    Optional<Template> findByCode(@Param("code") String code);

}
