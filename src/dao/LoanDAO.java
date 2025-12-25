package dao;

import database.Mysqlconnection;
import model.Loan;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    private Connection connection;
    private database.Database db;   
    
    public LoanDAO(Connection connection) {
        this.connection = connection;
        this.db = new Mysqlconnection();   
    }
    
        // If connection is null, open a new one
        private Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = db.openConnection();
        }
        return connection;
    }
    

    // Insert new loan
    public int insertLoan(Loan loan) throws SQLException {
        String sql = "INSERT INTO loans (user_id, borrower_name, item_name, due_date, loan_amount, status, loan_date, item_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
           PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, loan.getUserId());
            stmt.setString(2, loan.getBorrowerName());
            stmt.setString(3, loan.getItemName());
            stmt.setDate(4, Date.valueOf(loan.getDueDate()));
            stmt.setDouble(5, loan.getAmount());
            stmt.setString(6, loan.getStatus());
            stmt.setDate(7, Date.valueOf(loan.getLoanDate()));
            stmt.setInt(8, loan.getItemId());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    // Get all loans for user
    public List<Loan> getAllLoans(int userId) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans WHERE user_id = ? ORDER BY due_date";
        
        try (Connection conn = getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Loan loan = new Loan(
                    rs.getInt("loan_id"),
                    rs.getString("borrower_name"),
                    rs.getString("item_name"),
                    rs.getDate("due_date").toLocalDate(),
                    rs.getDouble("loan_amount"),
                    rs.getString("status"),
                    rs.getDate("loan_date").toLocalDate(),
                    rs.getInt("item_id"),
                    rs.getInt("user_id")
                );
                loans.add(loan);
            }
        }
        return loans;
    }

    // Update loan
    public boolean updateLoan(Loan loan) throws SQLException {
        String sql = "UPDATE loans SET borrower_name=?, item_name=?, due_date=?, loan_amount=?, status=? WHERE loan_id=?";
        
        try (Connection conn = getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, loan.getBorrowerName());
            stmt.setString(2, loan.getItemName());
            stmt.setDate(3, Date.valueOf(loan.getDueDate()));
            stmt.setDouble(4, loan.getAmount());
            stmt.setString(5, loan.getStatus());
            stmt.setInt(6, loan.getLoanId());
            
            return stmt.executeUpdate() > 0;
        }
    }

    // Delete loan
    public boolean deleteLoan(int loanId) throws SQLException {
        String sql = "DELETE FROM loans WHERE loan_id=?";
        
        try (Connection conn = getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Mark as returned
    public boolean markAsReturned(int loanId) throws SQLException {
        String sql = "UPDATE loans SET status='Returned' WHERE loan_id=?";
        
        try (Connection conn = getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Get total loaned amount
    public double getTotalLoanedAmount(int userId) throws SQLException {
        String sql = "SELECT SUM(loan_amount) FROM loans WHERE user_id=? AND status='Active'";
        
        try (Connection conn = getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0;
    }

    // Get active loans count
    public int getActiveLoansCount(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM loans WHERE user_id=? AND status='Active'";
        
        try (Connection conn = getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Get overdue loans count
    public int getOverdueLoansCount(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM loans WHERE user_id=? AND status='Active' AND due_date < CURDATE()";
        
        try (Connection conn = getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
// Add this method to your LoanDAO.java file:
    public void createTable() throws SQLException {
            String sql = "CREATE TABLE IF NOT EXISTS loans (" +
                 "loan_id INT PRIMARY KEY AUTO_INCREMENT, " +
                 "user_id INT NOT NULL, " +
                 "borrower_name VARCHAR(100) NOT NULL, " +
                 "item_name VARCHAR(200) NOT NULL, " +
                 "due_date DATE NOT NULL, " +
                 "loan_amount DECIMAL(10,2) NOT NULL, " +
                 "status VARCHAR(20) DEFAULT 'Active', " +
                 "loan_date DATE NOT NULL, " +
                 "item_id INT DEFAULT 1" +
                 ")";
    
                try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Loans table is ready!");
        }
    }
    
}