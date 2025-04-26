
# Elevator Management System

This project implements a scalable **Elevator Management System** using Object-Oriented Design (OOD) principles and the **Strategy Design Pattern** to handle dynamic request allocation policies based on operational conditions such as peak and non-peak hours.

**Design Reference:**  
This design follows structured modeling principles inspired by the [Elevator OOD design from ycwkatie's repository](https://github.com/ycwkatie/OOD-Object-Oriented-Design/blob/main/ood/elevator.md).

---

## Key Components and Interacting Objects

| Class / Interface | Responsibility |
|-------------------|-----------------|
| `Elevator` | Represents a physical elevator handling movement, door operations, and requests. |
| `Request` (interface) | Base abstraction for all requests in the system. |
| `ExternalRequest` | Represents requests made from outside the elevator (with level and direction). |
| `InternalRequest` | Represents requests made inside the elevator (destination floor only). |
| `HandleRequestStrategy` (interface) | Strategy pattern interface for request assignment logic. |
| `PeakHourHandleRequestStrategy` | Strategy implementation prioritizing direction-matching elevators first. |
| `NormalHourHandleRequestStrategy` | Strategy implementation prioritizing idle elevators first. |
| `Direction` (enum) | Defines movement direction: `UP` or `DOWN`. |
| `Status` (enum) | Defines elevator status: `IDLE`, `UP`, or `DOWN`. |

---

## User Action Flow

The Elevator System supports complete operational flow:

1. **handleRequest(ExternalRequest r)**  
   A user presses an external UP/DOWN button at a floor.  
   System routes the request using the currently active `HandleRequestStrategy` and assigns an elevator.

2. **handleInternalRequest(InternalRequest r)**  
   A user selects a floor button inside the elevator.  
   Elevator validates and adds the destination to its stop queue.

3. **openGate(), closeGate()**  
   Simulate the opening and closing of the elevator doors before/after servicing a floor.

4. **isRequestValid(InternalRequest r)**  
   Validates if a selected floor is reachable based on current movement direction.

5. **setStrategy(HandleRequestStrategy s)**  
   Dynamically switches between request handling strategies (e.g., peak vs normal hours).

---

## Getting Started

To set up and run the project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/elevator-management-system.git
   ```

2. Navigate into the project directory:
   ```bash
   cd elevator-management-system
   ```

3. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

4. Compile and run the `Main` class to simulate elevator operations.

---

## Usage Example

```java
Elevator elevator1 = new Elevator();
Elevator elevator2 = new Elevator();
List<Elevator> elevators = Arrays.asList(elevator1, elevator2);

HandleRequestStrategy strategy = new NormalHourHandleRequestStrategy();
ElevatorSystem system = new ElevatorSystem(elevators, strategy);

ExternalRequest request = new ExternalRequest(5, Direction.UP);
system.handleRequest(request);
```

---

## Project Structure

| Package  | Responsibility |
|----------|-----------------|
| `core` | Contains Elevator, Request classes, and Enums. |
| `strategy` | Contains HandleRequestStrategy interface and implementations. |
| `controller` | Contains ElevatorSystem to coordinate elevators and requests. |
| `test` | Contains simulation via `Main` class. |

---

## Design Patterns

| Pattern | Description |
|---------|-------------|
| **Strategy Pattern** | Allows dynamic switching between different request allocation algorithms without changing system core. |

