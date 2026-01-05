# Transaction Risk Portfolio Engine ⚙️💼

A Java-based portfolio engine focused on **correctness, state integrity, and auditable transaction processing** for financial systems.

This project simulates how real-world portfolio engines apply BUY/SELL transactions, enforce invariants, prevent duplicate execution, and clearly explain *why* a transaction was rejected — not just whether it failed.

The primary goal is to demonstrate **engineering decision-making** around immutability, idempotency, validation, and extensible system design — rather than surface-level feature output.

---

## ❓ Why this project exists

Financial systems aren’t just about calculations — they’re about **controlled state transitions**.

This project explores:
- How to safely apply buy/sell transactions to a portfolio
- How to prevent invalid or duplicate transaction execution (idempotency)
- How to maintain portfolio correctness while optimising performance
- How to make transaction failures **observable and explainable**
- How to design systems that remain extensible as complexity grows 📈

---

## 🧠 Core Concepts Demonstrated

- **Invariant Enforcement**  
  Portfolio state is protected across all operations, ensuring invalid transitions cannot corrupt system state.

- **Transaction Identity & Immutability**  
  Transactions are immutable and uniquely identified, separating transaction definition from application and enforcement logic.

- **Idempotent Application**  
  The portfolio guarantees that a transaction can only be applied once — even if the same transaction is submitted multiple times.

- **Efficient State Aggregation**  
  Portfolio value is cached and updated via deltas, reducing repeated recomputation to **O(1)** while preserving correctness.

- **Auditable & Explainable Failures**  
  Every transaction attempt is logged. Rejected transactions include structured rejection reasons rather than silent failures or unchecked exceptions.

---

## 🏗 High-Level Architecture

- **Transaction**  
  Immutable representation of a financial action (BUY / SELL), containing identity, target asset, and amount.

- **Asset**  
  Represents individual holdings and encapsulates asset-level value state.

- **Portfolio**  
  Owns all mutable state and is solely responsible for:
  - validating transactions  
  - enforcing invariants  
  - applying state changes  
  - preventing duplicate execution  
  - recording audit entries  

- **Validation & Audit Domain Objects**  
  Structured types model validation outcomes, rejection reasons, and audit entries to keep decision-making explicit and observable.

This separation ensures **clear ownership of responsibility**, a key principle in maintainable backend and financial systems 🧩.

---

## 🧪 Example Usage

```java
Portfolio portfolio = new Portfolio(assets);

Transaction buy = new Transaction(1, Transaction.Type.BUY, "AAPL", 1000);
Transaction sell = new Transaction(2, Transaction.Type.SELL, "AAPL", 500);

portfolio.applyTransaction(buy);
portfolio.applyTransaction(sell);

double totalValue = portfolio.getTotalValue();

---

## ✅ Portfolio Guarantees

The portfolio guarantees:
- Transactions are applied at most once
- State remains consistent after every operation
- Invalid transactions are rejected with clear reasoning
- Every transaction attempt is auditable

---

## 🛠 Technologies Used

- Java 8  
- Object-Oriented Design  
- Collections & State Management  
- Invariant-based system design  

*(No external frameworks — emphasis is on language fundamentals and system correctness.)*

---

## ⚖️ Design Trade-offs & Decisions

**Why immutability for transactions?**  
Prevents accidental mutation and ensures transaction history is reliable.

**Why centralise enforcement in `Portfolio`?**  
Keeps all state mutation and invariants in one authoritative location.

**Why validate instead of throwing for expected failures?**  
Expected failures are part of normal system behaviour and should be observable, not exceptional.

**Why cache total portfolio value?**  
Improves performance while maintaining correctness through controlled updates.

These decisions mirror patterns used in real-world backend and financial systems.

---

## 🔮 Future Improvements

- More advanced risk and exposure models  
- Transaction reversal / replay  
- Persistent audit storage  
- Concurrency-safe transaction application  
- External API layer  

---

## 🎯 What this project demonstrates

This project is less about output and more about **how systems should behave under constraints**.

It reflects how I think about:
- Backend correctness  
- State ownership  
- Defensive design  
- Auditable systems  
- Scalable abstractions  
