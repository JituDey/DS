import java.util.*;
import java.util.Map.Entry;
class TraversalConversion{
	//http://www.geeksforgeeks.org/print-postorder-from-given-inorder-and-preorder-traversals/
	public static void main(String[] args) {
		Tree t = new Tree();
		t.createTree(new int[]{10,8,15,1,9,11,17});
		
		System.out.println("-------convertToPostOrder-----------");
		int[] in = {1,8,9,10,11,15,17};
		int[] pre = {10,8,1,9,15,11,17};
		TraversalConversion.convertToPostOrder(in, pre, in.length);
	}
	/*
	* We can print postorder traversal without constructing the tree. The idea is, root is always the first item in preorder traversal and it must *be the last item in postorder traversal. We first recursively print left subtree, then recursively print right subtree. Finally, print root. To *find boundaries of left and right subtrees in pre[] and in[], we search root in in[], all elements before root in in[] are elements of left *subtree and all elements after root are elements of right subtree. In pre[], all elements after index of root in in[] are elements of right *subtree. And elements before index (including the element at index and excluding the first element) are elements of left subtree.
	*/
	
	public static void convertToPostOrder(int[] in, int[] pre, int n){
		int rootPosition = search(in, pre[0], n);
		// it has left subtree
		if(rootPosition!=0){
			convertToPostOrder(in, pre+1, rootPosition);
		}
		// it has right subtree
		if(rootPosition!=n-1){
			convertToPostOrder(in+rootPosition+1, pre+rootPosition+1, n-rootPosition-1);
		}
		
		System.out.println(pre[0] +" ");
	}
	
	public static int search(int[] in, int x, int n){
		for(int i=0;i<n;i++){
			if(in[i]==x){
				return i;
			}
		}
		return -1;
	}
}