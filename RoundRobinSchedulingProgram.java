import java.util.ArrayList;

class Process {
    String name;
    int timeToExecute;
    Process next;

    Process(String name, int timeToExecute) {
        this.name = name;
        this.timeToExecute = timeToExecute;
    }

    Process(String name, int timeToExecute, Process next) {
        this.name = name;
        this.timeToExecute = timeToExecute;
        this.next = next;
    }
}

public class RoundRobinSchedulingProgram {
    public static void main(String args[]) {
        final int limitTimePerProcess = 20;

        Process process_D = new Process("D", 15);
        Process process_C = new Process("C", 25, process_D);
        Process process_B = new Process("B", 20, process_C);
        Process process_A = new Process("A", 30, process_B);
        process_D.next = process_A;

        Process node = process_A, prevNode = process_D;

        ArrayList<String> orderedExecutedList = new ArrayList<>();

        while (true) {
            orderedExecutedList.add(node.name);

            if (node.timeToExecute > limitTimePerProcess) {
                node.timeToExecute -= limitTimePerProcess;

                prevNode = node;
            } else {
                if (prevNode == node) {
                    break;
                }

                prevNode.next = node.next;
            }

            node = node.next;
        }

        System.out.println(String.join(" -> ", orderedExecutedList));

    }
}
