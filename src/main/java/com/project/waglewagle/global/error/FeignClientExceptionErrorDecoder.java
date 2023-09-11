package com.project.waglewagle.global.error;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class FeignClientExceptionErrorDecoder implements ErrorDecoder {

    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("{} 요청실패,  status : {}, reason : {} ", methodKey, response.status(), response.reason());

        FeignException exception = FeignException.errorStatus(methodKey, response);
        log.info("FeignException **************** " + exception.getMessage(), exception.responseBody());
        HttpStatus httpStatus = HttpStatus.valueOf(response.status());

        // 서버 일시적 부하시, http 요청 재시도
        if(httpStatus.is5xxServerError()) {
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    null,
                    response.request()
            );
        }
        return errorDecoder.decode(methodKey,response);
    }
}
