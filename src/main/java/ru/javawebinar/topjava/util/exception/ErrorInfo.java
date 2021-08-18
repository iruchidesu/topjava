package ru.javawebinar.topjava.util.exception;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String typeI18N;
    private final String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String typeI18N, String[] details) {
        this.url = url.toString();
        this.type = type;
        this.typeI18N = typeI18N;
        this.details = details;
    }
}