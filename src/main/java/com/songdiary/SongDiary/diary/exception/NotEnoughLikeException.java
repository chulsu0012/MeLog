package com.songdiary.SongDiary.diary.exception;

public class NotEnoughLikeException extends RuntimeException{
    public NotEnoughLikeException() {
    }

    public NotEnoughLikeException(String message) {
        super(message);
    }

    public NotEnoughLikeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughLikeException(Throwable cause) {
        super(cause);
    }

    public NotEnoughLikeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
