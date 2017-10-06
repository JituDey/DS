class CheckSubTreeOfATree{
	public static void main(String args[]) 
    {
        /*Tree1
              26
             /   \
            10     3
           /    \     \
          4      6      3
           \
            30  */
           
        Node root1 = new Node(26);
        root1.right = new Node(3);
        root1.right.right = new Node(3);
        root1.left = new Node(10);
        root1.left.left = new Node(4);
        root1.left.left.right = new Node(30);
        root1.left.right = new Node(6);
  
        /*TREE 2
           10
         /    \
         4      6
          \
          30  */
           
        Node root2 = new Node(10);
        root2.right = new Node(6);
        root2.left = new Node(4);
        root2.left.right = new Node(30);
  
        if (isSubTree(root1, root2))
            System.out.println("Tree 2 is subtree of Tree 1 ");
        else
            System.out.println("Tree 2 is not a subtree of Tree 1");
    }
	
	public static boolean isSubTree(Node root1, Node root2){
		if(root1==null && root2==null) return true;
		if(root1==null && root2!=null) return false;
		if(root1!=null && root2==null) return false;
		
		while(root1!=null && root1.data!=root2.data){
			if(root2.data > root1.data){
				root1=root1.right;
			} else{
				root1=root1.left;
			}
		}
		
		if(root1!=null && root2!=null && root1.data==root2.data){
			System.out.println("now equal for : "+ root1.data +" = "+ root2.data);
			return isIdenticalTree(root1, root2);
		}
		return false;
	}

	public static boolean isIdenticalTree(Node root1, Node root2){
		if(root1==null && root2==null) return true;
		if(root1==null && root2!=null) return false;
		if(root1!=null && root2==null) return false;
		return (root1.data == root2.data && isIdenticalTree(root1.left, root2.left) && isIdenticalTree(root1.right, root2.right));
	}
}