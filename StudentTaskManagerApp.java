import java.util.*;

// === User Class ===
class User {
    String username;
    String password;
    ArrayList<Task> tasks;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tasks = new ArrayList<>();
    }
}

// === Task Class ===
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

// === Main App Class ===
public class StudentTaskManagerApp {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, User> users = new HashMap<>();
    static User currentUser;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Welcome to Student Task Manager ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> signUp();
                case 2 -> {
                    if (logIn()) {
                        taskMenu();
                    }
                }
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void signUp() {
        System.out.print("Choose a username: ");
        String username = sc.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already taken!");
            return;
        }

        System.out.print("Choose a password: ");
        String password = sc.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Signup successful! You can now log in.");
    }

    static boolean logIn() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            currentUser = user;
            System.out.println("Login successful! Welcome " + currentUser.username + "!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    static void taskMenu() {
        while (true) {
            System.out.println("\n--- Task Menu for " + currentUser.username + " ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Delete Task");
            System.out.println("5. Log Out");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> markComplete();
                case 4 -> deleteTask();
                case 5 -> {
                    currentUser = null;
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();
        System.out.print("Enter description: ");
        String desc = sc.nextLine();
        currentUser.tasks.add(new Task(title, desc));
        System.out.println("Task added.");
    }

    static void viewTasks() {
        if (currentUser.tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        System.out.println("--- Your Tasks ---");
        for (int i = 0; i < currentUser.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + currentUser.tasks.get(i));
        }
    }

    static void markComplete() {
        viewTasks();
        if (currentUser.tasks.isEmpty()) return;

        System.out.print("Enter task number to mark as complete: ");
        int num = sc.nextInt();
        if (num < 1 || num > currentUser.tasks.size()) {
            System.out.println("Invalid number.");
            return;
        }
        currentUser.tasks.get(num - 1).isCompleted = true;
        System.out.println("Task marked complete.");
    }

    static void deleteTask() {
        viewTasks();
        if (currentUser.tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int num = sc.nextInt();
        if (num < 1 || num > currentUser.tasks.size()) {
            System.out.println("Invalid number.");
            return;
        }
        currentUser.tasks.remove(num - 1);
        System.out.println("Task deleted.");
    }
}
