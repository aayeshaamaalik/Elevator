package org.example;

import java.util.*;

public class NormalHourHandleRequestStrategy implements HandleRequestStrategy {

    @Override
    public void handleRequest(Request request, List<Elevator> elevators) {
        if (!(request instanceof ExternalRequest)) return;

        ExternalRequest extReq = (ExternalRequest) request;
        int targetLevel = extReq.getLevel();
        Direction dir = extReq.getDirection();

        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        // 1. First priority: find nearest IDLE elevator
        for (Elevator elevator : elevators) {
            if (elevator.getStatus() == Status.IDLE) {
                int distance = Math.abs(elevator.getCurrentLevel() - targetLevel);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        // 2. If no IDLE elevator, pick elevator that can eventually serve the request
        if (bestElevator == null) {
            for (Elevator elevator : elevators) {
                if (elevator.getStatus() == Status.UP && dir == Direction.UP && targetLevel >= elevator.getCurrentLevel()) {
                    int distance = Math.abs(elevator.getCurrentLevel() - targetLevel);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                } else if (elevator.getStatus() == Status.DOWN && dir == Direction.DOWN && targetLevel <= elevator.getCurrentLevel()) {
                    int distance = Math.abs(elevator.getCurrentLevel() - targetLevel);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }

        // 3. Assign the request
        if (bestElevator != null) {
            bestElevator.handleExternalRequest(extReq);
            System.out.println("Assigned request at level " + targetLevel + " to Elevator at level " + bestElevator.getCurrentLevel());
        } else {
            System.out.println("No available elevator could serve request at level " + targetLevel);
        }
    }
}

