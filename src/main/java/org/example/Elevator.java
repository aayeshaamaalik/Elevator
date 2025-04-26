package org.example;

import java.util.*;

public class Elevator {
    private int currLevel;
    private boolean gateOpen;
    private float weightLimit;
    private Status status = Status.IDLE;
    private final List<Integer> upStops = new ArrayList<>();
    private final List<Integer> downStops = new ArrayList<>();

    public void handleExternalRequest(ExternalRequest request) {
        if (request.getDirection() == Direction.UP) {
            upStops.add(request.getLevel());
        } else {
            downStops.add(request.getLevel());
        }
    }

    public void handleInternalRequest(InternalRequest request) {
        int level = request.getLevel();
        if (isRequestValid(request)) {
            if (level > currLevel) upStops.add(level);
            else downStops.add(level);
        }
    }

    public boolean isRequestValid(InternalRequest r) {
        if (status == Status.UP && r.getLevel() < currLevel) return false;
        if (status == Status.DOWN && r.getLevel() > currLevel) return false;
        return true;
    }

    public void openGate() {
        gateOpen = true;
    }

    public void closeGate() {
        gateOpen = false;
    }

    public int getCurrentLevel() {
        return currLevel;
    }

    public float getWeightLimit() {
        return weightLimit;
    }

    public Status getStatus() {
        return status;
    }

}
