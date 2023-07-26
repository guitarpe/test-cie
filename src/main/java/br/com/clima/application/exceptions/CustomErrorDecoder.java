package br.com.clima.application.exceptions;

import br.com.clima.application.enuns.Messages;
import feign.Response;
import feign.codec.ErrorDecoder;
import javassist.NotFoundException;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new NotFoundException(Messages.MSG_NOT_FOUND.value());
        }

        return defaultErrorDecoder().decode(methodKey, response);
    }

    private ErrorDecoder defaultErrorDecoder() {
        return new ErrorDecoder.Default();
    }
}
