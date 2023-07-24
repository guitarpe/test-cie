package br.com.clima.application.enuns;

public enum Messages {
    MSG_INI_SUCCESS("Job Iniciado com Sucesso!"),
    MSG_FIN_SUCCESS("Job Finalizado com Sucesso!"),
    MSG_NOT_FOUND("Integração não encontrada, verifique o parâmetro informado"),
    MSG_CITY_NOT_FOUND("Cidade não informada, verifique o parâmetro informado");

    private final String value;

    Messages(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Messages fromValue(String v) {
        for (Messages c: Messages.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
