package br.com.clima.application.enuns;

public enum Messages {
    MSG_INI_SUCCESS("Job Iniciado com Sucesso!"),
    MSG_FIN_SUCCESS("Job Finalizado com Sucesso!"),
    MSG_CIDADE_NOT_FOUND("Cidade não informada, verifique o parâmetro informado"),
    MSG_UF_NOT_FOUND("A UF da cidade não informada, verifique o parâmetro informado"),
    MSG_INTERVAL_NOT_FOUND("Intervalo não informado, verifique o parâmetro informado");

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
