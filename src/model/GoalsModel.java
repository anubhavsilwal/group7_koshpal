package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalsModel {
    private List<Goal> goals;
    private List<GoalsModelListener> listeners;
    
    public GoalsModel() {
        goals = new ArrayList<>();
        listeners = new ArrayList<>();
        loadGoalsFromDatabase(); // Load from database instead of sample data
    }
    
    private void loadGoalsFromDatabase() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM goals ORDER BY due_date";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            goals.clear(); // Clear existing goals
            
            while (resultSet.next()) {
                Goal goal = new Goal(
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getDouble("target_amount"),
                    resultSet.getDouble("saved_amount"),
                    resultSet.getDate("due_date").toLocalDate(),
                    resultSet.getString("location")
                );
                goals.add(goal);
            }
            
            System.out.println("Loaded " + goals.size() + " goals from database.");
            
        } catch (SQLException e) {
            System.err.println("Error loading goals from database: " + e.getMessage());
            e.printStackTrace();
            // Fallback to sample data if database fails
            initializeSampleData();
        } finally {
            closeResources(resultSet, statement, connection);
        }
    }
    
    public void addGoal(Goal goal) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO goals (name, category, target_amount, saved_amount, due_date, location) " +
                          "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, goal.getName());
            statement.setString(2, goal.getCategory());
            statement.setDouble(3, goal.getTargetAmount());
            statement.setDouble(4, goal.getSavedAmount());
            statement.setDate(5, Date.valueOf(goal.getDueDate()));
            statement.setString(6, goal.getLocation());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Goal added to database: " + goal.getName());
                goals.add(goal); // Add to local list
                notifyListeners();
            }
            
        } catch (SQLException e) {
            System.err.println("Error adding goal to database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, statement, connection);
        }
    }
    
    public void addMoneyToGoal(int index, double amount) {
    if (index >= 0 && index < goals.size()) {
        Goal goal = goals.get(index);
        double newAmount = goal.getSavedAmount() + amount;
        
        // Ensure we don't go below 0
        if (newAmount < 0) {
            newAmount = 0;
        }
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            String query = "UPDATE goals SET saved_amount = ? WHERE name = ? AND due_date = ?";
            statement = connection.prepareStatement(query);
            statement.setDouble(1, newAmount);
            statement.setString(2, goal.getName());
            statement.setDate(3, Date.valueOf(goal.getDueDate()));
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Update the goal object
                goal.setSavedAmount(newAmount);
                notifyListeners();
            }
            
        } catch (SQLException e) {
            System.err.println("Error updating goal in database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(null, statement, connection);
        }
    }
}
    
    public void removeGoal(int index) {
        if (index >= 0 && index < goals.size()) {
            Goal goal = goals.get(index);
            
            Connection connection = null;
            PreparedStatement statement = null;
            
            try {
                connection = DatabaseConnection.getConnection();
                String query = "DELETE FROM goals WHERE name = ? AND due_date = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, goal.getName());
                statement.setDate(2, Date.valueOf(goal.getDueDate()));
                
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    goals.remove(index);
                    notifyListeners();
                    System.out.println("Goal removed from database: " + goal.getName());
                }
                
            } catch (SQLException e) {
                System.err.println("Error removing goal from database: " + e.getMessage());
                e.printStackTrace();
            } finally {
                closeResources(null, statement, connection);
            }
        }
    }
    
    // Helper method to close database resources
    private void closeResources(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
    
    // Fallback sample data (in case database connection fails)
    private void initializeSampleData() {
        System.out.println("Loading sample data...");
        addGoal(new Goal("Vacation to Hawaii", "Travel", 5000, 2500, 
                        LocalDate.of(2024, 12, 31), "Hawaii"));
        addGoal(new Goal("New Laptop", "Electronics", 1500, 800, 
                        LocalDate.of(2024, 6, 30), "Apple Store"));
        addGoal(new Goal("Emergency Fund", "Savings", 10000, 5000, 
                        LocalDate.of(2024, 12, 31), "Bank"));
        addGoal(new Goal("Car Down Payment", "Transportation", 5000, 3000, 
                        LocalDate.of(2024, 9, 30), "Dealership"));
    }
    
    // Rest of your existing methods remain the same...
    public List<Goal> getGoals() {
        return new ArrayList<>(goals);
    }
    
    public int getTotalGoals() {
        return goals.size();
    }
    
    public double getTotalTarget() {
        return goals.stream().mapToDouble(Goal::getTargetAmount).sum();
    }
    
    public double getTotalSaved() {
        return goals.stream().mapToDouble(Goal::getSavedAmount).sum();
    }
    
    public double getAverageProgress() {
        if (goals.isEmpty()) return 0;
        double totalProgress = goals.stream().mapToDouble(Goal::getProgress).sum();
        return totalProgress / goals.size();
    }
    
    // Listener pattern for updates
    public interface GoalsModelListener {
        void goalsUpdated();
    }
    
    public void addListener(GoalsModelListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(GoalsModelListener listener) {
        listeners.remove(listener);
    }
    
    private void notifyListeners() {
        for (GoalsModelListener listener : listeners) {
            listener.goalsUpdated();
        }
    }
}