package ru.academits.gerasimenko.phonebookhibernate.utilities;

import org.apache.commons.text.StringEscapeUtils;

public class TextUtilities {
    private TextUtilities() {
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static String getEscapedHtmlText(String text) {
        return text == null ? "" : StringEscapeUtils.escapeHtml4(text).trim();
    }
}