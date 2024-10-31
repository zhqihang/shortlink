package com.qihang.shortlink.project.common.convention.exception;

import com.qihang.shortlink.project.common.convention.errorcode.BaseErrorCode;
import com.qihang.shortlink.project.common.convention.errorcode.IErrorCode;

/**
 * @description: 远程异常
 * @author: zhqihang
 * @date: 2024/10/26
 */
public class RemoteException extends AbstractException {
    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
