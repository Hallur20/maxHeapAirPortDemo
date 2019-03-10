package mymaxheapdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author Hallur
 */
public class MyMaxHeapDemo {

    static ArrayList<Passenger> passengers = new ArrayList<>();
    static ArrayList<Passenger> queue = new ArrayList<>();
    static Node root;
    static List<List<Node>> levels;

    public static void main(String[] args) throws Exception {
        passengers.add(new Passenger("monkey"));
        passengers.add(new Passenger("disabled"));
        passengers.add(new Passenger("businessClass"));
        passengers.add(new Passenger("family"));
        passengers.add(new Passenger("lateForFlight"));
        
        queue.add(getRandomPassenger(passengers));

        //queue.add(getRandomPassenger(passengers));
        root = insertLevelOrder(queue, root, 0);
        printLevelWise(root);
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        insertNewPassengerHeapify(root, getRandomPassenger(passengers));
        System.out.println("-------------------------------------");
        printLevelWise(root);
    }

    static void insertNewPassengerHeapify(Node root, Passenger passenger) {

        Queue<Node> q = new LinkedList<>();
        Node insertNode = new Node(passenger);
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.left != null && node.right != null) {
                q.add(node.left);
                q.add(node.right);
            } else {
                if (node.left == null) {
                    node.left = insertNode;
                } else {
                    node.right = insertNode;
                }
                break;
            }
        }
        
        if (FindParent(root, insertNode) != null) {
            System.out.println("times for: " + insertNode.data.getName());
            tryOutParents(insertNode);
        }

    }

    static void tryOutParents(Node insertNode) {
        if (FindParent(root, insertNode) != null) {
            Node parent = FindParent(root, insertNode);
            if (parent.data.getPriority() < insertNode.data.getPriority()) {
                Passenger placeHolder = parent.data;
                parent.data = insertNode.data;
                insertNode.data = placeHolder;
                tryOutParents(parent);
            }
        }
    }

    static Node FindParent(Node root, Node node) {
        if (root == null || node == null) {
            return null;
        } else if ((root.right != null && root.right.data == node.data) || (root.left != null && root.left.data == node.data)) {
            return root;
        } else {
            Node found = FindParent(root.right, node);
            if (found == null) {
                found = FindParent(root.left, node);
            }
            return found;
        }
    }

    static void trySwap(Node root) {
        if (root.left != null) {
            if (root.left.data.getPriority() > root.data.getPriority() && root.left.data.getPriority() > root.right.data.getPriority()) {
                Passenger placeHolder = root.data;
                root.data = root.left.data;
                root.left.data = placeHolder;
            }
        }
        if (root.right != null) {
            if (root.right.data.getPriority() > root.data.getPriority() && root.right.data.getPriority() > root.left.data.getPriority()) {
                Passenger placeHolder = root.data;
                root.data = root.right.data;
                root.right.data = placeHolder;
            }
        }
    }

    static Node insertLevelOrder(ArrayList<Passenger> passengers, Node root,
            int i) {
        // Base case for recursion 
        if (i < passengers.size()) {

            Node temp = new Node(passengers.get(i));
            root = temp;
            // insert left child 
            root.left = insertLevelOrder(passengers, root.left,
                    2 * i + 1);
            root.parent
                    = // insert right child 
                    root.right = insertLevelOrder(passengers, root.right,
                            2 * i + 2);

        }
        return root;
    }

    public static void printLevelWise(Node root) {
        levels = traverseLevels(root);

        for (List<Node> level : levels) {
            for (Node node : level) {
                System.out.print(node.data.getName() + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Node>> traverseLevels(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Node>> levels = new LinkedList<>();

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            List<Node> level = new ArrayList<>(nodes.size());
            levels.add(level);

            for (Node node : new ArrayList<>(nodes)) {
                level.add(node);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
                nodes.poll();
            }
        }

        return levels;
    }

    public static Passenger getRandomPassenger(ArrayList<Passenger> passengers) {
        Random rand = new Random();
        return passengers.get(rand.nextInt(passengers.size()));
    }
}
