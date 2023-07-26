package br.com.clima.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.text.Normalizer;

@Getter
@Setter
@Slf4j
public class Utils {

    public static String removeAccentuation(String word) {
        String wordNoAccentuation = Normalizer.normalize(word, Normalizer.Form.NFD);
        wordNoAccentuation = wordNoAccentuation.replaceAll("[^\\p{ASCII}]", "");
        return wordNoAccentuation;
    }
}
