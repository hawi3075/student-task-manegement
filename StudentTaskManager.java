import java.util.ArrayList;
import java.util.Scanner;

class Task {
    String title;
    String description;
    boolean isCompleted;

    Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    public String toString() {
        return (isCompleted ? "[✔] " : "[ ] ") + title + " - " + description;
    }
}

public class StudentTaskManager {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> markTaskComplete();
                case 4 -> deleteTask();
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option, try again!");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n--- Student Task Manager ---");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Mark Task as Complete");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();
        System.out.print("Enter task description: ");
        String desc = sc.nextLine();
        tasks.add(new Task(title, desc));
        System.out.println("Task added successfully!");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
            return;
        }
        System.out.println("\n--- Task List ---");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void markTaskComplete() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to mark as complete: ");
        int num = sc.nextInt();
        if (num < 1 || num > tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }
        tasks.get(num - 1).isCompleted = true;
        System.out.println("Task marked as complete!");
    }

    static void deleteTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int num = sc.nextInt();
        if (num < 1 || num > tasks.size()) {
            System.out.println("Invalid task number!");
            return;
        }
        tasks.remove(num - 1);
        System.out.println("Task deleted successfully!");
    }
}
