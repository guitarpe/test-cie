package br.com.clima.application.enuns;

public enum Messages {
    MSG_SUCCESS("Realizado com Sucesso!"),
    MSG_SAVE_SUCCESS("Salvo com Sucesso!"),
    MSG_INI_SUCCESS("Job Iniciado com Sucesso!"),
    MSG_FIN_SUCCESS("Job Finalizado com Sucesso!"),
    MSG_CIDADE_NOT_FOUND("Cidade não informada, verifique o parâmetro informado"),
    MSG_UF_NOT_FOUND("A UF da cidade não informada, verifique o parâmetro informado"),
    MSG_INTERVAL_NOT_FOUND("Intervalo não informado, verifique o parâmetro informado"),
    MSG_NOT_FOUND("Recurso não encontrado"),
    MSG_ERROR_START_CALL("Erro ao iniciar serviço"),
    MSG_ERROR_STOP_CALL("Erro ao finalizar serviço"),
    MSG_ERROR_LIST_CITIES("Erro ao listar cidades"),
    MSG_ERROR_SAVE_CITIES("Erro ao salvar cidades");;

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
