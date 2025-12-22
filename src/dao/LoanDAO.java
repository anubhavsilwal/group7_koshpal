package dao;

import model.Loan;
import database.Mysqlconnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    private Mysqlconnection mysql = new Mysqlconnection();
    public int getOverdue;

    // Insert a new loan
    public int insertLoan(Loan loan, int userId) {
        int generatedId = -1;
        String sql = "INSERT INTO loans (user_id, borrower_name, item_name, due_date, loan_amount, status, loan_date, item_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, userId);
            stmt.setString(2, loan.getBorrowerName());
            stmt.setString(3, loan.getItemName());
            stmt.setDate(4, Date.valueOf(loan.getDueDate()));
            stmt.setDouble(5, loan.getAmount());
            stmt.setString(6, loan.getStatus());
            stmt.setDate(7, Date.valueOf(loan.getLoanDate()));
            stmt.setInt(8, loan.getItemId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    // Update existing loan
    public boolean updateLoan(int loanId, double newAmount, LocalDate newDueDate) {
        String sql = "UPDATE loans SET loan_amount = ?, due_date = ? WHERE loan_id = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newAmount);
            stmt.setDate(2, Date.valueOf(newDueDate));
            stmt.setInt(3, loanId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mark loan as returned
    public boolean markLoanAsReturned(int loanId) {
        String sql = "UPDATE loans SET status = 'Returned' WHERE loan_id = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loanId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete loan
    public boolean deleteLoan(int loanId) {
        String sql = "DELETE FROM loans WHERE loan_id = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loanId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get loan by ID
    public Loan getLoanById(int loanId) {
        String sql = "SELECT * FROM loans WHERE loan_id = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loanId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractLoanFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to extract loan from ResultSet
    private Loan extractLoanFromResultSet(ResultSet rs) throws SQLException {
        return new Loan(
                rs.getInt("loan_id"),
                rs.getString("borrower_name"),
                rs.getString("item_name"),
                rs.getDate("due_date").toLocalDate(),
                rs.getDouble("loan_amount"),
                rs.getString("status"),
                rs.getDate("loan_date").toLocalDate(),
                rs.getInt("item_id")
        );
    }

    // Other methods (getAllLoans, getTotalLoanedAmount, getActiveLoansCount, getOverdueLoansCount) remain as in your code

    public double getTotalLoanedAmount(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Loan> getAllLoans(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getActiveLoansCount(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getOverdueLoansCount(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
