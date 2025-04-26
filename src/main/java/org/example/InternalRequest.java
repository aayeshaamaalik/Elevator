package org.example;

public class InternalRequest implements Request {
    private final int level;

    public InternalRequest(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

