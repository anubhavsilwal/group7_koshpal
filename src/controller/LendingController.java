/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
 

package controller;

import dao.LoanDAO;
import java.time.LocalDate;
import java.util.List;
import model.Loan;

public class LendingController {
    private final LoanDAO loanDAO = new LoanDAO();
    private int currentUserId;

    public void setCurrentUserId(int id) { 
        this.currentUserId = id; 
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public int addLoan(String borrower, String item, double amount, LocalDate dueDate) {
        Loan loan = new Loan(borrower, item, dueDate, amount);
        return loanDAO.insertLoan(loan, currentUserId);
    }

    public boolean updateLoan(int loanId, double newAmount, LocalDate newDueDate) {
        if (loanId <= 0 || newAmount <= 0) {
            return false;
        }
        return loanDAO.updateLoan(loanId, newAmount, newDueDate);
    }

    public boolean markLoanAsReturned(int loanId) {
        return loanDAO.markLoanAsReturned(loanId);
    }

    public boolean deleteLoan(int loanId) {
        return loanDAO.deleteLoan(loanId);
    }

    public Loan getLoanById(int loanId) {
        if (loanId <= 0) return null;
        return loanDAO.getLoanById(loanId);
    }

    public List<Loan> getAllLoans() {
        return loanDAO.getAllLoans(currentUserId);
    }

    public double getTotalLoanedAmount() {
        return loanDAO.getTotalLoanedAmount(currentUserId);
    }

    public int getActiveLoanCount() {
        return loanDAO.getActiveLoansCount(currentUserId);
    }

    public int getOverdueLoanCount() {
        return loanDAO.getOverdueLoansCount(currentUserId);
    }

    public List<Double> getNewLoanSuggestions() {
        // Get user's previous loan amounts and suggest similar
        List<Loan> loans = getAllLoans();
        if (loans.isEmpty()) {
            return List.of(100.0, 250.0, 500.0, 750.0);
        }
        
        // Calculate average of previous loans
        double avg = loans.stream()
            .mapToDouble(Loan::getAmount)
            .average()
            .orElse(500.0);
            
        return List.of(avg * 0.5, avg, avg * 1.5, avg * 2.0);
    }
    
    public List<Loan> getOverdueLoans() {
        return getAllLoans().stream()
            .filter(loan -> loan.getDueDate().isBefore(LocalDate.now()) && 
                           "Active".equals(loan.getStatus()))
            .toList();
    }

    public static class DashboardListener {

        public DashboardListener() {
        }
    }
}
  