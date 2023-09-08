class TodoTask {
    int id;
    String description;
    boolean isCompleted;
    TodoTask next;

    TodoTask() {
    }

    TodoTask(int id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    public void toggleComplete() {
        this.isCompleted = !this.isCompleted;
    }
}

class TodoList {
    int currentAvailId;
    TodoTask head;
    TodoTask tail;

    public TodoList() {
        this.currentAvailId = 1;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void showAllTasks() {
        if (isEmpty()) {
            System.out.println("No Tasks in the list");
        } else {
            TodoTask node = head;
            while (node != null) {
                System.out.printf("%d - %s - %b\n", node.id, node.description, node.isCompleted);
                node = node.next;
            }
        }
    }

    public void addTask(String description) {
        TodoTask task = new TodoTask(currentAvailId, description);

        if (isEmpty()) {
            this.head = task;
            this.tail = task;
        } else {
            this.tail.next = task;
            this.tail = this.tail.next;
        }

        System.out.printf("Task ID (%d) Content '%s' Added Successfully\n", currentAvailId, description);

        this.currentAvailId++;
    }

    public void removeTask(int id) {
        if (this.head.id == id) {
            this.head = this.head.next;
        } else {
            TodoTask node = this.head.next, prevNode = this.head;

            while (node != null && node.id != id) {
                prevNode = node;
                node = node.next;
            }

            if (node != null) {
                prevNode.next = node.next;
                System.out.printf("Task ID (%d) Removed Successfully\n", id);
            } else {
                System.out.println("Task ID Not Found");
            }
        }
    }
}

public class TodoListProgram {
    public static void main(String args[]) {
        TodoList todoList = new TodoList();

        todoList.addTask("Do 1");
        todoList.addTask("Do 2");

        todoList.showAllTasks();

        todoList.addTask("Do 3");

        todoList.removeTask(2);

        todoList.showAllTasks();
    }
}
