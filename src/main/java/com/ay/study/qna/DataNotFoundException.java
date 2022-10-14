package com.ay.study.qna;

public class DataNotFoundException extends RuntimeException {
    // Throwable or Exception 을 상속 받을 경우 -> Throw & Catch 문 사용해야함
    // RunTimeException -> 런타임에 종료되므로 Throw & Catch 문 사용 안함

    public DataNotFoundException(String message) {
        super(message);
    }
}
