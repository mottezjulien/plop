package com.julien.plop.i18n.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface I18nRepository extends JpaRepository<I18nEntity, String> {

}
