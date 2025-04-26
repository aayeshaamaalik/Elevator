package org.example;

import java.util.List;

public interface HandleRequestStrategy {
    void handleRequest(Request request, List<Elevator> elevators);
}
