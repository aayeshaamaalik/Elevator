package org.example;

import java.util.*;

public class PeakHourHandleRequestStrategy implements HandleRequestStrategy {

    @Override
    public void handleRequest(Request request, List<Elevator> elevators) {
        if (!(request instanceof ExternalRequest)) return;

        ExternalRequest extReq = (ExternalRequest) request;
        int targetLevel = extReq.getLevel();
        Direction dir = extReq.getDirection();

        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        // 1. First pass: find elevator moving in same direction and can pick the request
        for (Elevator elevator : elevators) {
            if (elevator.getStatus() == Status.IDLE) continue;

            if (elevator.getStatus() == Status.UP && dir == Direction.UP && targetLevel > elevator.getCurrentLevel()) {
                int distance = Math.abs(elevator.getCurrentLevel() - targetLevel);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            } else if (elevator.getStatus() == Status.DOWN && dir == Direction.DOWN && targetLevel < elevator.getCurrentLevel()) {
                int distance = Math.abs(elevator.getCurrentLevel() - targetLevel);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        // 2. Second pass: if none found, pick nearest IDLE elevator
        if (bestElevator == null) {
            minDistance = Integer.MAX_VALUE;
            for (Elevator elevator : elevators) {
                if (elevator.getStatus() == Status.IDLE) {
                    int distance = Math.abs(elevator.getCurrentLevel() - targetLevel);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }

        // 3. Assign the request if we found a match
        if (bestElevator != null) {
            bestElevator.handleExternalRequest(extReq);
            System.out.println("Assigned request at level " + targetLevel + " to Elevator at level " + bestElevator.getCurrentLevel());
        } else {
            System.out.println("No suitable elevator found for request at level " + targetLevel);
        }
    }
}
