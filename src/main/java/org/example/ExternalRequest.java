package org.example;

public class ExternalRequest implements Request {
    private final int level;
    private final Direction direction;

    public ExternalRequest(int level, Direction direction) {
        this.level = level;
        this.direction = direction;
    }

    public int getLevel() {
        return level;
    }

    public Direction getDirection() {
        return direction;
    }
}

