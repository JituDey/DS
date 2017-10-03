import java.util.*;
import java.util.Map.Entry;
class TraversalConversion{
	//static Node root;
	
	public static void main(String[] args) {
		Tree t = new Tree();
		t.createTree(new int[]{10,8,15,1,9,11,17});
		
		System.out.println("-------convertToPostOrder-----------");
		int[] in = {1,8,9,10,11,15,17};
		int[] pre = {10,8,1,9,15,11,17};
		t.convertToPostOrder(in, pre);
	}
	
	public static void convertToPostOrder(int[] in, int[] pre){
		http://www.geeksforgeeks.org/print-postorder-from-given-inorder-and-preorder-traversals/
	}
}