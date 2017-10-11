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
		Node root1 = new Node(10);
        root1.left = new Node(8);
        root1.right = new Node(2);
        root1.left.left = new Node(3);
        root1.left.right = new Node(5);
		root1.right.right = new Node(9);
		System.out.println("Given tree's children satisfies sum property : "+ ifChildrenSumPropertySafisfied(root1));
		System.out.println("Given nodes are cousin nodes : "+ ifCousin(root1, root1.left.left, root1.right.right));
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
	
	/*Check for Children Sum Property in a Binary Tree
					  10
					/   \
				  8       2
				/    \   / 
			   3      5  2   
	*/
	public static boolean ifChildrenSumPropertySafisfied(Node root) {
		if(root==null) return true;		
		int sum = 0;
		if(root.left==null && root.right==null) return true;
		if(root.left!=null) sum+=root.left.data;
		if(root.right!=null) sum+=root.right.data;
		if(root.data==sum) {
			return ifChildrenSumPropertySafisfied(root.left) && ifChildrenSumPropertySafisfied(root.right);
		}
		System.out.println("Property not satisfied for :"+ root.data +" and sum is:"+ sum);
		return false;
	}
	
	public static int getSumOfChildren(Node root) {
		if(root==null) return 0;
		return getSumOfChildren(root.left) + getSumOfChildren(root.right);
	}
	
	/*
	 Cousin nodes are the nodes who are at the same level in the tree and whose parents are siblings.
		 6
	   /   \
	  3     5
	 / \   / \
	7   8 1   3
	Say two node be 7 and 1, result is TRUE.
	Say two nodes are 3 and 5, result is FALSE.
	Say two nodes are 7 and 5, result is FALSE.
	Check the height of both the nodes, if heights are dif­fer­ent then return false.
	Check if both the nodes has the same parent, if yes then return false.
	else return true.
	*/
	public static boolean ifCousin(Node root, Node n1, Node n2) {
		if(root==null) return true;		
		int hN1=getHeightOfNode(root, n1,1);
		int hN2=getHeightOfNode(root, n2,1);
		if(hN1 != hN2) return false;
		if(isSameParents(root, n1, n2)) return false;
		return true;
	}
	
	public static int getHeightOfNode(Node root, Node n, int height){
		if(root==null) return 0;
		if(root.data == n.data) return height;
		int level = getHeightOfNode(root.left, n, height+1);
		if(level==0) 
			level= getHeightOfNode(root.right, n, height+1);
		return level;
	}
	
	public static boolean isSameParents(Node root, Node n1, Node n2) {
		if(root==null) return false;
		return ((root.left==n1 && root.right==n2) || (root.right==n1 && root.left==n2) ||
				isSameParents(root.left, n1,n2) || isSameParents(root.right, n1,n2));
	}
}