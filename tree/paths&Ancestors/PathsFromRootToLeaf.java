/*
	   45
     /    \
    35    60
   / \    /
  20  38 50
*/
import java.util.*;

public class PathsFromRootToLeaf{
	public static void main(String[] args){
		 Node root1 = new Node(45);
		 root1.left = new Node(35);
		 root1.left.left = new Node(20);
		 root1.left.right = new Node(38);
		 root1.right = new Node(60);
		 root1.right.left = new Node(50);
		 System.out.println("printAllPathsUsingList: ");
		 printAllPathsUsingList(root1);
		 System.out.println("printAllPathsUsingMap: ");
		 printAllPathsUsingMap(root1);
		 System.out.println("printAllPathsUsingMapPreOrder: ");
		 printAllPathsUsingMapPreOrder(root1);
	}
	
	public static void printAllPathsUsingList(Node node){
		if(node==null) return;
		LinkedList<Integer> paths = new LinkedList<Integer>();
		printAllPathsUsingListUtil(node, paths);
	}
	
	public static void printAllPathsUsingListUtil(Node node, LinkedList<Integer> list){
		if(node==null) return;
		list.add(node.data);
		if(node.left==null && node.right==null){
			System.out.println(list);
		}
		printAllPathsUsingListUtil(node.left, list);
		printAllPathsUsingListUtil(node.right, list);
		list.pollLast();
	}
	
	public static void printAllPathsUsingMap(Node node){
		if(node==null) return;
		Map<Node, Node> map = new HashMap<Node, Node>();
		map.put(node, null);
		printAllPathsUsingMapUtil(node, map);
	}
	
	public static void printAllPathsUsingMapUtil(Node node, Map<Node, Node> map){
		if(node==null) return;
		if(node.left==null && node.right==null){
			printPathFromMap(node, map);
		}
		if(node.left!=null) map.put(node.left, node);
		if(node.right!=null) map.put(node.right, node);
		printAllPathsUsingMapUtil(node.left, map);
		printAllPathsUsingMapUtil(node.right, map);
	}
	
	public static void printPathFromMap(Node node, Map<Node, Node> map){
		Stack<Node> nodes = new Stack<Node>();
		while(node!=null){
			nodes.push(node);
			node=map.get(node);
		}
		while(!nodes.isEmpty()){
            System.out.print(nodes.pop().data+"\t");
        }
        System.out.println();
	}
	
	//Use pre-order iterative, map nodes with their parent
    //when you reach leaf print path using map
    private static void printAllPathsUsingMapPreOrder(Node root) {
        if(root == null) return;
        Stack<Node> stack = new Stack<>();
        Map<Node, Node> map = new HashMap<>();
        map.put(root, null);
        stack.push(root);
        // 1st traversal will  map node to their parent
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if(node.right != null) {
                map.put(node.right,node);
                stack.push(node.right);
            }
            if(node.left != null) {
                map.put(node.left, node);
                stack.push(node.left);
            }
        }

        // 2nd Traversal will be used to display path
        stack.push(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if(node.left == null && node.right == null) {
                printPathFromMap(node, map);
            }
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
    }
}