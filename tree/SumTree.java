/*
Do a traversal of the given tree. In the traversal, store the old value of the current node, recursively call for left and right subtrees and change the value of current node as sum of the values returned by the recursive calls. Finally return the sum of new value and value (which is sum of values in the subtree rooted with this node).


		  10
	   /      \
	-2         6
   /   \      /  \ 
 8     -4    7    5
should be changed to:
		 20(4-2+12+6)
	   /      \
	4(8-4)      12(7+5)
   /   \      /  \ 
 0      0    0    0
*/
public class SumTree {
    public static void main(String[] args) {
		Node root = new Node(10);
        root.left = new Node(-2);
        root.right = new Node(6);
        root.left.left = new Node(8);
        root.left.right = new Node(-4);
        root.right.left = new Node(7);
        root.right.right = new Node(5);
        
		System.out.println("========Actual tree: =============");
		Tree.levelOrderTraversal(root);
		System.out.println("Given tree is a SumTree: "+ ifSumTree(root));
		System.out.println("========Converted SumTree: =============");
		SumTree.convertToSumTree(root);
		Tree.levelOrderTraversal(root);
		
		Node sumTreeNode = new Node(26);
        sumTreeNode.left = new Node(10);
        sumTreeNode.right = new Node(3);
        sumTreeNode.left.left = new Node(4);
        sumTreeNode.left.right = new Node(6);
        sumTreeNode.right.right = new Node(3);
		System.out.println("Given tree is a SumTree: "+ ifSumTree(sumTreeNode));
	}
	
	public static int convertToSumTree(Node root) {
		if(root==null) return 0;
		int oldValue = root.data;
		int newValueLeft = convertToSumTree(root.left);
		int newValueRight = convertToSumTree(root.right);
		root.data=newValueLeft+newValueRight;
		return oldValue+root.data;
	}
	/*
		Check SumTree:
			 26
			/   \
		  10     3
		/    \     \
	  4      6      3
  */
	public static boolean ifSumTree(Node root) {
		if(root==null) return true;
		if(root.left==null && root.right==null){
			return true;
		}		
		int leftSum = sumofSubTrees(root.left);
		int rightSum = sumofSubTrees(root.right);
		
		boolean isLeftSumTree = ifSumTree(root.left);
		boolean isRightSumTree = ifSumTree(root.right);

		return (root.data==leftSum+rightSum && isLeftSumTree && isRightSumTree);
	}
	
	public static int sumofSubTrees(Node root) {
		if(root==null) return 0;
		if(root.left==null && root.right==null){
			return root.data;
		}
		return root.data+sumofSubTrees(root.left)+sumofSubTrees(root.right);
	}
}