import java.util.*;

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
		System.out.println("---cloned tree iteration--");
		Node newRoot2 = TreeCloning.cloneTreeIterative(node);
		Tree.levelOrderTraversal(newRoot2);
		
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
		ConnectedNode newConnectedRoot = cloneSpecialTree(connectedNode);
		System.out.println("---actual special tree--");
		ConnectedTree.printSiblings(connectedNode.left.left, connectedNode, 1);
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
	
	private static ConnectedNode cloneSpecialTree(ConnectedNode root) {
        Map<ConnectedNode,ConnectedNode> map = new HashMap<>();
        copyLeftRight(root, map);
        storeLinks(root,map);
        return map.get(root);
    }
	
	public static void copyLeftRight(ConnectedNode root, Map<ConnectedNode, ConnectedNode> map){
		if(root==null) return;
		ConnectedNode newRoot = new ConnectedNode(root.data);
		map.put(root, newRoot);
		if(root.left!=null)
			copyLeftRight(root.left, map);
		if(root.right!=null)
			copyLeftRight(root.right, map);
	}
	public static void storeLinks(ConnectedNode root, Map<ConnectedNode, ConnectedNode> map){
		if(root==null) return;
		ConnectedNode node = map.get(root);
		if(node!=null){
			node.left = map.get(root.left);
			node.right = map.get(root.right);
			node.nextElement = map.get(root.nextElement);
			storeLinks(root.left, map);
			storeLinks(root.right, map);
		}
	}
	public static Node cloneTreeIterative(Node root){
		if(root==null) return null;
		Queue<Node> q1 = new LinkedList<Node>();
		Queue<Node> q2 = new LinkedList<Node>();
		q1.add(root);
		Node root2= new Node(root.data);
		q2.add(root2);
		
		while(!q1.isEmpty()){
			Node n1 = q1.remove();
			Node n2 = q2.remove();
			if(n1.left!=null){
				q1.add(n1.left);
				n2.left=new Node(n1.left.data);
				q2.add(n2.left);
			}
			if(n1.right!=null){
				q1.add(n1.right);
				n2.right=new Node(n1.right.data);
				q2.add(n2.right);
			}
		}
		return root2;
	}
}