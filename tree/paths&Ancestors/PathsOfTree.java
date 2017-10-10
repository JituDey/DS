/*
	   45
     /    \
    35    60
   / \    /
  20  38 50
*/
import java.util.*;
public class PathsOfTree {
	static Node targetLeaf = null;
	public static void main(String[] args) {
		 Node root1 = new Node(45);
		 root1.left = new Node(35);
		 root1.left.left = new Node(20);
		 root1.left.right = new Node(38);
		 root1.right = new Node(60);
		 root1.right.left = new Node(50);
		 /*NOT BST
		 Node root1 = new Node(3);
		 root1.left = new Node(2);
		 root1.left.left = new Node(1);
		 root1.left.right = new Node(4);
		 root1.right = new Node(5);*/
		 System.out.println("------All Possible Paths: --------");
		 allPathsOfTree(root1);
		 System.out.println("------Max sum of all paths: --------");
		 findMaxSumOfPaths(root1);
		 System.out.println("------Path with max sum: --------");
		 findPathWithMaxSum(root1);
		 
		 System.out.println("\n------Diameter of the tree: --------");
		 Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.left.right.left = new Node(6);
		root.left.right.left.right = new Node(7);
		root.left.left.left = new Node(8);
		 System.out.println(findDiameterOfTree(root));
		 System.out.println("This tree is a BST: "+isBSTEfficient(root1));
	}
	
	public static void allPathsOfTree(Node root) {
		if(root==null) return;
		
		int[] path = new int[1000];
		findPath(path, 0, root);
	}
	/*
	Algorithm:
	Use a path array path[] to store current root to leaf path. Traverse from root to all leaves 
	in top-down fashion. While traversing, store data of all nodes in current path in array path[]. 
	When we reach a leaf node, print the path array.
	*/
	public static void findPath(int[] path, int len, Node root) {
		if(root==null)	return;
		path[len] = root.data;
		len++;
		if(root.left==null && root.right==null){
			printPath(path, len);
		}
		findPath(path, len, root.left);
		findPath(path, len, root.right);
	}
	
	public static void printPath(int[] path, int len) {
		for(int i = 0; i<len; i++){
			System.out.print(path[i]+" ");
		}
		System.out.println();
	}
	
	public static void findMaxSumOfPaths(Node root) {
		if(root==null)	return;
		int maxSum=0;
		int sum = calculateMaxSum(0, maxSum, root);
		if(sum>maxSum){
			maxSum=sum;
		}
		System.out.println(maxSum);
	}
	
	public static int calculateMaxSum(int sum, int maxSum, Node root) {
		if(root==null)	return 0;
		sum+=root.data;
		
		if(root.left==null && root.right==null){
			return sum;
		} else{
			return Math.max(calculateMaxSum(sum,maxSum, root.left), calculateMaxSum(sum,maxSum, root.right));
		}
	}
	
	public static void findPathWithMaxSum(Node root) {
		if(root==null)	return;
		findTargetLeaf(0, 0, root);
		System.out.println(targetLeaf.data);
		printPath(root);
	}
	
	private static boolean printPath(Node root) {
		if(root==null) return false;
		if(root == targetLeaf || printPath(root.left) || printPath(root.right)){
			System.out.print(root.data + " ");
			return true;
		}
		return false;
	}

	public static void findTargetLeaf(int sum, int maxSum, Node root) {
		if(root==null)	return;
		sum+=root.data;
		
		if(root.left==null && root.right==null){
			if(sum>maxSum){
				maxSum=sum;
				targetLeaf=root;
			}
		} else{
			findTargetLeaf(sum,maxSum, root.left);
			findTargetLeaf(sum,maxSum, root.right);
		}
	}
	
	/*
	Maximum(Diameter of left subtree, Diameter of right subtree, Longest path between two nodes which passes through the root.)
	Longest path between two nodes which passes through the root = 1 + height of left sub­tree + height of right subtree.
	*/
	public static int findDiameterOfTree(Node root) {
		if(root==null)	return 0;
		
		int leftDiameter = findDiameterOfTree(root.left);
		int rightDiameter = findDiameterOfTree(root.right);
		
		int leftHeight = heightOfTree(root.left);
		int rightHeight = heightOfTree(root.right);
		
		return Math.max(Math.max(leftDiameter, rightDiameter), leftHeight+rightHeight+1);
	}
	
	public static int heightOfTree(Node root) {
		if(root==null) return 0;
		return (1+Math.max(heightOfTree(root.left), heightOfTree(root.right)));
	}
	/*
	(Correct but not efficient)
	For each node, check if max value in left subtree is smaller than the node and min value in right subtree greater than the node
	*/
	public static boolean isBST(Node root) {
		if(root==null)	return true;
		
		if(root.left!=null && maxValue(root.left)>root.data){
			return false;
		}
		if(root.right!=null && minValue(root.right)<root.data){
			return false;
		}
		if(!isBST(root.left) || !isBST(root.right)){
			return false;
		}
		return true;
	}
	
	public static int maxValue(Node root) {
		if(root==null) return Integer.MIN_VALUE;
		return (Math.max(Math.max(root.data, maxValue(root.left)), maxValue(root.right)));
	}
	
	public static int minValue(Node node) {
		if(node==null) return Integer.MAX_VALUE;
		return (Math.min(Math.min(node.data, minValue(node.left)), minValue(node.right)));
	}
	
	/*
	above method runs slowly since it traverses over some parts of the tree many times. A better solution looks at each node only once. The trick is to write a utility helper function isBSTUtil(struct node* node, int min, int max) that traverses down the tree keeping track of the narrowing min and max allowed values as it goes, looking at each node only once. The initial values for min and max should be INT_MIN and INT_MAX — they narrow from there.
	*/
	public static boolean isBSTEfficient(Node root) {
		return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static boolean isBSTUtil(Node root, int min, int max){
		if(root==null) return true;
		if(root.data<min || root.data> max){
			return false;
		}
		return (isBSTUtil(root.left, min, root.data-1) && isBSTUtil(root.right, root.data+1, max));
	}
}