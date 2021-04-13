package com.app.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Timer {
    private Long hours = 0L;
    private Long minutes = 0L;
    private Long seconds = 0L;

    public String getHours() {
        return addZeroIfRequired(hours.toString());
    }

    public String getMinutes() {
        return addZeroIfRequired(minutes.toString());
    }

    public String getSeconds() {
        return addZeroIfRequired(seconds.toString());
    }

    private String addZeroIfRequired(String result) {
        return result.length() == 1 ? "0" + result : result;
    }
}
