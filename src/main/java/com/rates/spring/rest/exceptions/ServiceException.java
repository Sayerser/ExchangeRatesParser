package com.rates.spring.rest.exceptions;

import com.rates.spring.rest.domain.base.Identifiable;
import com.rates.spring.rest.dto.response.base.ErrorResponse;
import java.util.function.Supplier;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    /**
     * Паттерн поиска имени этого класса в стактрейсе
     */
    private static final String STACK_TRACE_CLASS_NAME_PATTERN = ServiceException.class.getName().replace(".", "\\.") + "(\\$.*)?";

    /**
     * Полезная нагрузка, возвращается в ответе сервиса
     */
    private final ErrorResponse.Data payload = new ErrorResponse.Data();
    /**
     * Код ответа
     */
    private int status;
    /**
     * Код ошибки [0;99]
     */
    private int errorCode;
    /**
     * Сообщение об ошибке, возвращаемое сервисом
     */
    private String errorMessage;
    /**
     * Сообщение об ошибке, логируемое в обработчике
     */
    private String internalMessage;
    /**
     * Исключение, вызвавшее ошибку
     */
    private Throwable cause;
    /**
     * Флаг лога исключения в обработчике
     */
    private boolean logOnResponse = true;

    public static ServiceExceptionBuilder builder() {
        return new ServiceExceptionBuilder();
    }

    /**
     * Заполняет исключение данными из шаблона
     *
     * @param template шаблон исключения
     * @return Билдер исключения
     */
    public static ServiceExceptionBuilder fromTemplate(ExceptionTemplate template) {
        ServiceExceptionBuilder builder = builder();

        if (template == null) {
            return builder.status(500)
                    .errorCode(0)
                    .publicAndInternalMessages("Unknown error");
        }

        int responseStatus = template.getResponseStatus();
        int errorCode = template.getErrorCode();
        boolean logException = template.isLogException();
        String messageTemplate = template.getErrorMessageTemplate();

        builder.status(responseStatus);
        builder.errorCode(errorCode);
        builder.logException(logException);
        builder.errorMessage(messageTemplate);

        return builder;
    }

    /**
     * @return код ошибки, помещаемый в ответ сервиса.
     * Ограничен в 3 знака.
     */
    public int getErrorCode() {
        return errorCode % 1000;
    }

    /**
     * @return Внутреннее сообщение с информацией об ошибке
     */
    @Override
    public String getMessage() {
        return getInternalMessage();
    }

    /**
     * @return Исключение, которое привело к ошибке
     */
    @Override
    public Throwable getCause() {
        return cause;
    }

    /**
     * Билдер исключений
     */
    public static class ServiceExceptionBuilder {
        /**
         * Исключение, которое будет возвращено в методе {@link #build()}
         */
        private final ServiceException ex;

        private ServiceExceptionBuilder(ServiceException exception) {
            ex = exception;
        }

        private ServiceExceptionBuilder() {
            this(new ServiceException());

            ex.status = 500;
            ex.errorCode = -1;
            ex.errorMessage = "Unknown error";
            ex.internalMessage = ex.errorMessage;
        }

        /**
         * @return Сконструированный объект исключения
         */
        public ServiceException build() {
            return ex;
        }

        /**
         * @return Билдер статуса ответа
         */
        public MwaServiceExceptionStatusBuilder status() {
            return new MwaServiceExceptionStatusBuilder(this);
        }

        /**
         * Устанавливает в исключение переданный статус ответа
         *
         * @param httpStatusCode статус ответа сервиса
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder status(int httpStatusCode) {
            return status().byCode(httpStatusCode);
        }

        /**
         * Устанавливает в исключение переданный код ошибки
         *
         * @param code код ошибки [0; 99]
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder errorCode(int code) {
            ex.errorCode = code;
            return this;
        }

        /**
         * Устанавливает переданное сообщение как сообщение исключения
         * и сообщение, возвращаемое сервисом
         *
         * @param message тескт сообщения
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder publicAndInternalMessages(String message) {
            return errorMessage(message).internalMessage(message);
        }


        /**
         * Устанавливает сообщение, возвращаемое сервисом
         *
         * @param message текст сообщения
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder errorMessage(String message) {
            ex.errorMessage = message;
            return this;
        }

        /**
         * Устанавливает логируемое сообщение
         *
         * @param message текст сообщения
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder internalMessage(String message) {
            ex.internalMessage = message;
            return this;
        }

        /**
         * Устанавливает каким исключением была вызвана ошибка
         *
         * @param cause исключение, вызвавшее ошибку
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder causedBy(Throwable cause) {
            ex.cause = cause;
            ex.payload.setCausedBy(cause.getLocalizedMessage());

            return this;
        }

        /**
         * Устанавливает каким исключением была вызвана ошибка
         *
         * @param message исключение, вызвавшее ошибку
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder causedBy(String message) {
            ex.payload.setCausedBy(message);

            return this;
        }

        /**
         * Устанавливает флаг логирования исключения в обработчике ошибок
         *
         * @param flag {@code true} - логировать исключение, иначе {@code false}
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder logException(boolean flag) {
            ex.logOnResponse = flag;
            return this;
        }

        /**
         * Устанавливает идентификатор объекта, обработка которого вызвала ошибку
         *
         * @param id идентификатор объекта
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder payloadIdentity(Object id) {
            ex.payload.setId(id);
            return this;
        }

        /**
         * Устанавливает идентификатор объекта, обработка которого вызвала ошибку
         *
         * @param entity сущность с идентификатором
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder payloadIdentity(Identifiable<?> entity) {
            if (entity == null) {
                return payloadIdentity((Object) null);
            }

            return payloadIdentity(entity.getId());
        }

        /**
         * Устанавливает имя поля, значение которого не прошло проверку
         *
         * @param name имя поля
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder payloadFieldName(String name) {
            ex.payload.setFieldName(name);
            return this;
        }

        /**
         * Устанавливает значение поля, которое (значение) не прошло проверку
         *
         * @param value значение поля
         * @return Этот же билдер
         */
        public ServiceExceptionBuilder payloadFieldValue(Object value) {
            ex.payload.setFieldValue(value);
            return this;
        }
    }

    /**
     * Билдер статуса ответа сервиса
     */
    public static class MwaServiceExceptionStatusBuilder {
        private final ServiceExceptionBuilder builder;

        private MwaServiceExceptionStatusBuilder(
                ServiceExceptionBuilder builder) {
            this.builder = builder;
        }

        /**
         * Устанавливает переданный код ответа
         *
         * @param code код ответа
         * @return Билдер исключения с кодом ответа сервиса {@code code}
         */
        public ServiceExceptionBuilder byCode(int code) {
            builder.ex.status = code;
            return builder;
        }

        /**
         * Устанавливает код ответа на основе перечисления {@link HttpStatus}
         *
         * @param httpStatus элемент перечисления
         * @return Билдер исключения с кодом ответа сервиса {@link HttpStatus#value()}
         */
        public ServiceExceptionBuilder byHttpStatus(HttpStatus httpStatus) {
            return httpStatus != null
                    ? byCode(httpStatus.value())
                    : byCode(-1);
        }
    }
}
