public class task {
  
}
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