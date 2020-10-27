package com.duya.shopping.utils;

import java.io.Serializable;
import java.util.Objects;

/*
这里定义的是service层的Result。有时候在controller层只会作一些简单的参数校验，在service层会作进一步的校验，
如果controller中需要统一返回一个JsonResult可以将ServiceResult作一个简单的转换器进行转换。
 */

public class ServiceResult<T, C> implements Serializable {

    public static final CodeMessage<String> SUCCESS = new DefaultMessage<>("00000000", "success");

    private T data;
    private CodeMessage<C> message;
    private boolean isSuccess;

    ServiceResult(T data, boolean isSuccess, CodeMessage<C> message) {
        this.data = data;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public T getData() {
        return data;
    }

    public CodeMessage<C> getCodeMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public static <D, C> ServiceResultBuilder<D, C> success() {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.isSuccess(true);
    }

    public static <D, C> ServiceResultBuilder<D, C> success(CodeMessage<C> codeMessage) {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.isSuccess(true).code(codeMessage.getCode()).message(codeMessage.getMessage());
    }

    /*
    默认SUCCESS
     */
    public static <D> ServiceResult<D, String> success(D data) {
        ServiceResultBuilder<D, String> success = success(SUCCESS);
        return success.data(data).build();
    }

    /*
    传进去一个 CodeMessage
     */
    public static <D, C> ServiceResult<D, C> error(CodeMessage<C> codeMessage) {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.isSuccess(false).code(codeMessage.getCode()).message(codeMessage.getMessage()).build();
    }

    public static <D, C> ServiceResultBuilder<D, C> error() {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.isSuccess(false);
    }


    static <D, C> ServiceResultBuilder<D, C> builder() {
        return new ServiceResultBuilder<>();
    }

    public static class DefaultMessage<C> implements CodeMessage<C>, java.io.Serializable {

        private C code;
        private String message;

        public DefaultMessage(C code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public C getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "DefaultMessage{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }


    public static class ServiceResultBuilder<T, C> {

        private T data;
        private C code;
        private String message;
        private boolean isSuccess;

        ServiceResultBuilder() { //package private
        }

        public ServiceResultBuilder<T, C> data(T data) {
            this.data = data;
            return this;
        }

        ServiceResultBuilder<T, C> isSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public ServiceResultBuilder<T, C> code(C code) {
            this.code = code;
            return this;
        }

        public ServiceResultBuilder<T, C> message(String message) {
            this.message = message;
            return this;
        }

        public ServiceResult<T, C> build() {
            Objects.requireNonNull(code, "code");
            Objects.requireNonNull(code, "message");
            return new ServiceResult<>(data, isSuccess, new DefaultMessage<>(code, message));
        }
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "data=" + data +
                ", message=" + message +
                ", isSuccess=" + isSuccess +
                '}';
    }
}