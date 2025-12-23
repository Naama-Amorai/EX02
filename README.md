# **Project: 2D Map Algorithms & GUI**
*Introduction to Computation | Computer Science Department | Ariel University*

---

## **Overview**
This project is the **second assignment** in the *Introduction to Computation* course.  
It focuses on **Object-Oriented Programming (OOP)** and **2D grid-based algorithms**.  
The project implements a **map system** capable of:
- **Pathfinding** (Shortest path calculations)
- **Area filling** (Flood fill logic)
- **Distance mapping** (BFS-based exploration)  
All integrated with a **custom graphical user interface (GUI)** based on the StdDraw library.

---

## **Project Structure**
The project consists of three core classes:

### **1. Map Class (implements Map2D)**
The main engine of the project, representing a **2D grid of integers**.  
**Key Features:**
- **Initialization:** Multiple constructors and deep-copy support.
- **Map Operations:** Grid scaling (resize), scalar multiplication, and map addition.
- **Analytical Tools:** Pixel validation, dimension comparison, and value-equality checks.
- **Core Algorithms (BFS-based):**
  - **Fill:** Fills a connected area of pixels with a new color from a starting point.
  - **Shortest Path:** Calculates the optimal route between two points, avoiding obstacles and supporting cyclic (wrap-around) movement.
  - **All Distances:** Generates a complete distance map from a source point to all reachable pixels.

### **2. Index2D Class (implements Pixel2D)**
Represents a specific coordinate `(x, y)` on the grid.  
**Features:**
- Distance calculation between points.
- Equality checks and value validation.
- String representation (`toString`).
- Basic getters and setters.

### **3. Ex2_GUI Class**
The visual interface and file management layer.  
**Features:**
- **Graphics:** Real-time visualization of the map and algorithms using `StdDraw`.
- **I/O Operations:** Support for saving and loading map states from files.
- **Interaction:** Includes a `Main` method to run the application and handle user input.

---

## **Testing & Robustness**
We performed **comprehensive unit tests** for all map operations using **JUnit 5**, focusing on:
- **Algorithm Accuracy:** Ensuring pathfinding precision in both cyclic and non-cyclic modes.
- **Grid Scaling:** Verifying that map resizing preserves relative pixel positions and data integrity.
- **Error Handling:** The system robustly manages edge cases, such as invalid inputs or boundary violations, by **throwing RuntimeExceptions**. This ensures internal consistency and prevents illegal actions during map operations.

---

## **Technical Specifications**
- **Language:** Java  
- **IDE:** IntelliJ IDEA  
- **Testing Framework:** JUnit 5  
- **External Libraries:** StdDraw for GUI  
