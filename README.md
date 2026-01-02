# Transaction Risk Portfolio Engine ⚙️💼

A Java-based portfolio engine focused on **correctness, state management, and risk modelling** for financial transactions.  
This project simulates how real-world portfolio systems apply transactions, enforce invariants, and compute risk under changing market conditions.

The primary goal is to demonstrate **engineering decision-making** around immutability, idempotency, and extensible system design — not just feature output.

---

## ❓ Why this project exists

Financial systems are not just about calculations — they are about **correct state transitions**.

This project explores:
- How to safely apply buy/sell transactions to a portfolio
- How to prevent invalid or duplicate transaction execution
- How to maintain portfolio correctness while optimising performance
- How to design systems that remain extensible as complexity grows

---

## 🧠 Core Concepts Demonstrated

- **Invariant Enforcement**  
  Portfolio state is protected across all buy/sell operations to prevent invalid transitions.

- **Transaction Identity & Immutability**  
  Transactions are immutable and uniquely identified, separating transaction definition from application logic to prevent double execution.

- **Idempotent Application**  
  The portfolio enforces that a transaction can only be applied once, even if attempted multiple times.

- **Efficient State Aggregation**  
  Portfolio value is cached and updated incrementally, reducing total valuation from repeated recomputation to **O(1)**.

- **Extensible Risk Modelling**  
  Risk calculations are abstracted to support future asset types and volatility models without rewriting core logic.

---

## 🏗 High-Level Architecture

- **Transaction**  
  Immutable representation of a financial action (buy/sell), containing identity and value.

- **Asset**  
  Represents holdings and valuation logic for individual assets.

- **Portfolio**  
  Owns all mutable state and is solely responsible for applying transactions and enforcing invariants.

- **RiskCalculator**  
  Encapsulates portfolio-level risk computation with clean separation from core state logic.

This separation ensures **clear ownership of responsibility**, a key principle in maintainable backend systems.

---

## 🧪 Example Usage

```java
Portfolio portfolio = new Portfolio();

Transaction buy = new Transaction(Transaction.Type.BUY, "AAPL", 1000);
Transaction sell = new Transaction(Transaction.Type.SELL, "AAPL", 500);

portfolio.applyTransaction(buy);
portfolio.applyTransaction(sell);

double totalValue = portfolio.getTotalValue();
double portfolioRisk = portfolio.calculateRisk();
```

The portfolio guarantees:
- Transactions are applied once
- State remains consistent after every operation
- Risk reflects the current portfolio state

---

## 🛠 Technologies Used

- Java 8  
- Object-Oriented Design  
- Collections & State Management  
- Core risk simulation logic  

*(No external frameworks — focus is on language fundamentals and system design.)*

---

## ⚖️ Design Trade-offs & Decisions

**Why immutability for transactions?**  
Prevents accidental mutation and makes transaction history reliable.

**Why enforce application logic in `Portfolio`?**  
Centralises state mutation and avoids scattered business rules.

**Why cache total portfolio value?**  
Improves performance while maintaining correctness through controlled updates.

These decisions mirror patterns used in real-world backend and financial systems.

---

## 🔮 Future Improvements

- More advanced risk models  
- Historical transaction replay  
- Persistent storage  
- Concurrency-safe transaction application  
- REST API interface  

---

## 🎯 What this project demonstrates

This project is less about output and more about **how systems should behave under constraints**.

It reflects how I think about:
- Backend correctness  
- State ownership  
- Defensive design  
- Scalable abstractions  

