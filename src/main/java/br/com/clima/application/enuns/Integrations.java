package br.com.clima.application.enuns;

public enum Integrations {
    INT_RAIN("rain"),
    INT_TEMPERATURE("temperature");

    private final String value;

    Integrations(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Integrations fromValue(String v) {
        for (Integrations c: Integrations.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }
}
