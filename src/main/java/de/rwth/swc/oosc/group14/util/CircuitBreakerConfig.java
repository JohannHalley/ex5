package de.rwth.swc.oosc.group14.util;

public class CircuitBreakerConfig {

    // once fail turn into open
    private int failThreshold = 1;

    private int failCountWindowInMs = 60*1000;

    // 30 s reset-time for state open try to turn back to half open
    private int open2HalfOpenTimeoutInMs = 30*1000;

    // once success turn into close
    private int consecutiveSuccThreshold = 5;

    private CircuitBreakerConfig(){

    }

    public static CircuitBreakerConfig newDefault(){
        CircuitBreakerConfig config = new CircuitBreakerConfig();
        return config;
    }

    public int getFailThreshold() {
        return failThreshold;
    }

    public void setFailThreshold(int failThreshold) {
        this.failThreshold = failThreshold;
    }

    public int getFailCountWindowInMs() {
        return failCountWindowInMs;
    }

    public void setFailCountWindowInMs(int failCountWindowInMs) {
        this.failCountWindowInMs = failCountWindowInMs;
    }

    public int getOpen2HalfOpenTimeoutInMs() {
        return open2HalfOpenTimeoutInMs;
    }

    public void setOpen2HalfOpenTimeoutInMs(int open2HalfOpenTimeoutInMs) {
        this.open2HalfOpenTimeoutInMs = open2HalfOpenTimeoutInMs;
    }

    public int getConsecutiveSuccThreshold() {
        return consecutiveSuccThreshold;
    }

    public void setConsecutiveSuccThreshold(int consecutiveSuccThreshold) {
        this.consecutiveSuccThreshold = consecutiveSuccThreshold;
    }
}