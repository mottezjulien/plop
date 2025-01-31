package com.julien.plop;

import java.util.Map;

public record I18n(String description, Map<Language, String> values) {

}
