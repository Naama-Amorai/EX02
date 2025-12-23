
# **Project: 2D Map Algorithms & GUI**
*Introduction to Computation | Computer Science Department | Ariel University*

---

## **Overview**
This project is the **second assignment** in the *Introduction to Computation* course.  
It focuses on **Object-Oriented Programming (OOP)** and **2D grid-based algorithms**.  
The project implements a **map system** capable of:
- Pathfinding
- Area filling
- Distance mapping  
All integrated with a **custom graphical user interface (GUI)**.

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

---

### **2. Index2D Class (implements Pixel2D)**
Represents a specific coordinate `(x, y)` on the grid.  
**Features:**
- Distance calculation between points.
- Equality checks.
- String representation (`toString`).
- Basic getters/setters.

---

### **3. Ex2_GUI Class**
The visual interface and file management layer.  
**Features:**
- **Graphics:** Built on the StdDraw library to visualize the map and algorithms in real-time.
- **I/O Operations:** Save and load map states from files.
- **Interaction:** Includes a `Main` method to run the application and handle user input.

---

## **Technical Specifications**
- **Language:** Java  
- **IDE:** IntelliJ IDEA  
- **Testing Framework:** JUnit 5  
- **External Libraries:** StdDraw for GUI  

---

## **How to Run**
1. Clone the repository:
   ```bash
   git clone <repository-url>
