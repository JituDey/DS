class TreeCloning{
	public static void main(String[] args){
		Node node = new Node(10);
		node.left=new Node(8);
		node.right=new Node(15);
		
		node.left.left=new Node(1);
		node.left.right=new Node(9);
		
		node.right.left=new Node(11);
		node.right.right=new Node(17);
		System.out.println("---actual tree--");
		Tree.levelOrderTraversal(node);
		Node newRoot = TreeCloning.cloneTree(node);
		System.out.println("---cloned tree--");
		Tree.levelOrderTraversal(newRoot);
		
		ConnectedNode connectedNode = new ConnectedNode(10);
		connectedNode.nextElement=null;
		connectedNode.left=new ConnectedNode(8);
		connectedNode.right=new ConnectedNode(15);
		connectedNode.left.nextElement= connectedNode.right;
		
		connectedNode.left.left=new ConnectedNode(1);
		connectedNode.left.right=new ConnectedNode(9);
		connectedNode.left.left.nextElement= connectedNode.left.right;
		
		connectedNode.right.left=new ConnectedNode(11);
		connectedNode.right.right=new ConnectedNode(17);
		connectedNode.left.right.nextElement=connectedNode.right.left;
		connectedNode.right.left.nextElement=connectedNode.right.right;
		ConnectedNode newConnectedRoot = TreeCloning.cloneSpecialTree(connectedNode);
		System.out.println("---cloned special tree--");
		ConnectedTree.printSiblings(newConnectedRoot.left.left, newConnectedRoot, 1);
	}
	
	public static Node cloneTree(Node root){
		if(root==null) return null;
		Node newRoot = new Node(root.data);
		if(root.left!=null)
			newRoot.left = cloneTree(root.left);
		if(root.right!=null)
			newRoot.right = cloneTree(root.right);
		return newRoot;
	}
	
	public static ConnectedNode cloneSpecialTree(ConnectedNode root){
		if(root==null) return null;
		ConnectedNode newRoot = new ConnectedNode(root.data);
		if(root.left!=null)
			newRoot.left = cloneSpecialTree(root.left);
		if(root.right!=null)
			newRoot.right = cloneSpecialTree(root.right);
		if(root.nextElement!=null)
			newRoot.nextElement = cloneSpecialTree(root.nextElement);
		return newRoot;
	}
}