# 🏦 Bank Management System - Java Console App

A simple Object-Oriented Bank Management System built using **Java** and file-based storage (serialization).  
It supports both **Admin** and **User** roles, each with dedicated features.

---

## 🚀 Features

### 👤 User Functionalities
- Account creation with PIN
- Login using Account Number and PIN
- Deposit and Withdraw money
- Check account balance
- View transaction history
- Calculate compound interest

### 🛡 Admin Functionalities
- Login with admin username and password
- View all customer accounts and their balances
- Create new admin accounts (max 3 allowed)
- Delete any bank account

---

## 📂 Technologies Used

- **Java**
- **OOPs Concepts** (Encapsulation, Classes, Objects)
- **Java Serialization** for file persistence
- **IntelliJ IDEA** (Development environment)

---

## 💡 How It Works

- Account and admin data are stored in files (`accounts.dat`, `admins.dat`) using Java's `ObjectOutputStream`.
- On each run, the system loads saved data, allowing permanent account storage.
- Admin access is restricted to valid credentials, with a max limit of 3 admin users.

---

## 🏁 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Bank-Management-System-Java.git
2. Open in IntelliJ IDEA or any Java IDE.

3. Run Main.java
