class ConnectedTree{
	public static void main(String[] args){
		ConnectedNode node = new ConnectedNode(10);
		node.nextElement=null;
		node.left=new ConnectedNode(8);
		node.right=new ConnectedNode(15);
		node.left.nextElement= node.right;
		
		node.left.left=new ConnectedNode(1);
		node.left.right=new ConnectedNode(9);
		node.left.left.nextElement= node.left.right;
		
		node.right.left=new ConnectedNode(11);
		node.right.right=new ConnectedNode(17);
		node.left.right.nextElement=node.right.left;
		node.right.left.nextElement=node.right.right;
		
		ConnectedTree.printSiblings(node.left.left, node, 1);
	}
	
	public static void printSiblings(ConnectedNode node, ConnectedNode root, int count){
		if(node==null || root==null) return;
		ConnectedNode n = searchInTree(node, root);
		if(n!=null){
			while(n.nextElement!=null){
				n=n.nextElement;
				count++;
			}
		} 
		System.out.println(count);
	}
	
	public static ConnectedNode searchInTree(ConnectedNode node, ConnectedNode root){
		ConnectedNode n = null;
		if(root.data!=node.data){
			if(node.data>root.data && root.right!=null){
				n=searchInTree(node, root.right);
			} else if(node.data<root.data && root.left!=null){
				n=searchInTree(node, root.left);
			}
		} else{
			return root;
		}
		return n;
	}
}