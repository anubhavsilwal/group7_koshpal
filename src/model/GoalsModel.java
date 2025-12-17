package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalsModel {
    private List<Goal> goals;
    private List<GoalsModelListener> listeners;
    
    public GoalsModel() {
        goals = new ArrayList<>();
        listeners = new ArrayList<>();
        // Add sample data for testing
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        addGoal(new Goal("Vacation Fund", "Vacation", 10000, 7500, 
                        LocalDate.of(2024, 12, 31), "Location"));
        addGoal(new Goal("Vacation Fund", "Vacation", 10000, 7500, 
                        LocalDate.of(2024, 12, 31), "Location"));
    }
    
    public void addGoal(Goal goal) {
        goals.add(goal);
        notifyListeners();
    }
    
    public void updateGoal(int index, Goal goal) {
        if (index >= 0 && index < goals.size()) {
            goals.set(index, goal);
            notifyListeners();
        }
    }
    
    public void removeGoal(int index) {
        if (index >= 0 && index < goals.size()) {
            goals.remove(index);
            notifyListeners();
        }
    }
    
    public void addMoneyToGoal(int index, double amount) {
        if (index >= 0 && index < goals.size()) {
            Goal goal = goals.get(index);
            goal.addMoney(amount);
            notifyListeners();
        }
    }
    
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