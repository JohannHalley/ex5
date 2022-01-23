package de.rwth.swc.oosc.group14.util;

public enum CircuitBreakerState {
    CLOSED,    // working normally, calls are transparently passing through
    OPEN,      // method calls are being intercepted and CircuitBreakerExceptions are being thrown instead
    HALF_OPEN  // method calls are passing through; if another blacklisted exception is thrown, reverts back to OPEN
}