# Java E-Commerce Management System

A simple Java project simulating an **e-commerce management system**, created by **Lee Rosenblit & Tomer Feldon**.  
The project models a small online marketplace that manages customers, sellers, managers, and products.

---

## 🧩 Project Structure

| Class | Description |
|-------|--------------|
| `Project.java` | The main entry point of the program. Runs the main logic and initializes the system. |
| `Product.java` | Represents a general product with attributes such as name, price, and category. |
| `PackagedProduct.java` | Extends `Product`, representing products that come in packages or bundles. |
| `Customer.java` | Represents a customer with an address and shopping cart. |
| `Seller.java` | Represents a seller who lists products and manages stock. |
| `Manager.java` | Represents a system manager overseeing operations. |
| `Address.java` | Stores address information for customers and sellers. |
| `EmptyCartException.java` | Custom exception thrown when attempting to checkout an empty cart. |

---

## 🖼 UML Diagram

A UML diagram illustrating class relationships is available in the file:  
`UML.jpg`

---

## ⚙️ How to Run

1. Open the project in **IntelliJ IDEA** (recommended).
2. Make sure all `.java` files are under the same source folder.
3. Locate and run `Project.java`.
4. Follow the console prompts to interact with the system.

---

## 📦 Requirements

- Java 17+  
- IntelliJ IDEA or any compatible Java IDE

---

## 🧠 Concepts Demonstrated

- Object-Oriented Programming (OOP)
  - Inheritance
  - Polymorphism
  - Encapsulation
- Exception Handling (`EmptyCartException`)
- Class Relationships (as shown in UML)
- Basic Console Input/Output

---

## 📄 License

This project is for **educational purposes** only and may be freely used or modified for learning.

