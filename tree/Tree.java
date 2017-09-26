import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

class Tree{
	static Node root;
	
	public void add(int d){
		Node n = new Node(d);
		if(root==null){
			root=n;
			return;
		}
		attachNode(root, n);
	}
	
	public void attachNode(Node node, Node newNode){
		if(node.data > newNode.data){
			if(node.left==null){
				node.left=newNode;
			} else{
				attachNode(node.left, newNode);
			}
		} else {
			if(node.right==null){
				node.right=newNode;
			} else{
				attachNode(node.right, newNode);
			}
		}
	}
	
	public void createTree(int[] arr){
		if(arr==null)
			return;
		Tree t = new Tree();
		for(int i=0;i<arr.length;i++){
			t.add(arr[i]);
		}
	}
	
	public void inorderTraversal(Node root){
		if(root==null)
			return;
		inorderTraversal(root.left);
		System.out.println(root.data);
		inorderTraversal(root.right);
	}
	
	public void preorderTraversal(Node root){
		if(root==null)
			return;
		System.out.println(root.data);
		preorderTraversal(root.left);
		preorderTraversal(root.right);
	}
	
	public void postorderTraversal(Node root){
		if(root==null)
			return;
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		System.out.println(root.data);
	}
	
	public void levelOrderTraversal(Node root){
		if(root==null)
			return;
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		
		while(!q.isEmpty()){
			Node n = q.remove();
			System.out.print(n.data + " ");
			if(n.left!=null){
				q.add(n.left);
			}
			if(n.right!=null){
				q.add(n.right);
			}
		}
		System.out.println();
	}
	
	public void levelOrderTraversalLineByLine(Node root){
		if(root==null)
			return;
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		q.add(null);
		while(!q.isEmpty()){
			Node n = q.remove();
			if(n==null){
				System.out.println("");
				if(!q.isEmpty())
					q.add(null);
				continue;
			}
			System.out.print(n.data + " ");
			if(n.left!=null){
				q.add(n.left);
			}
			if(n.right!=null){
				q.add(n.right);
			}
		}
	}
	
	public void levelOrderTraversalLineByLine2Q(Node root){
		if(root==null)
			return;
		Queue<Node> q1 = new LinkedList<Node>();
		Queue<Node> q2 = new LinkedList<Node>();
		q1.add(root);
		while(!q1.isEmpty() || !q2.isEmpty()){
			System.out.println("");
			while(!q1.isEmpty()){
				Node n = q1.remove();
				System.out.print(n.data + " ");
				if(n.left!=null){
					q2.add(n.left);
				}
				if(n.right!=null){
					q2.add(n.right);
				}
			}
			System.out.println("");
			while(!q2.isEmpty()){
				Node n = q2.remove();
				System.out.print(n.data + " ");
				if(n.left!=null){
					q1.add(n.left);
				}
				if(n.right!=null){
					q1.add(n.right);
				}
			}
		}
	}
	
	public void levelOrderTraversalReverse(Node root){
		if(root==null)
			return;
		Queue<Node> q = new LinkedList<Node>();
		Stack<Node> s = new Stack<Node>();
		q.add(root);
		
		while(!q.isEmpty()){
			Node n = q.remove();
			s.push(n);
			if(n.right!=null){
				q.add(n.right);
			}
			if(n.left!=null){
				q.add(n.left);
			}
		}
		while(!s.isEmpty()){
			System.out.print(s.pop().data + " ");
		}
		System.out.println();
	}
	
	public void zigzagTraversal(Node root){
		if(root==null)
			return;
		Stack<Node> currentLvl = new Stack<Node>();
		Stack<Node> nextLvl = new Stack<Node>();
		currentLvl.push(root);
		while(!currentLvl.isEmpty() || !nextLvl.isEmpty()){
			while(!currentLvl.isEmpty()){
				Node n = currentLvl.pop();
				System.out.print(n.data + " ");
				if(n.left!=null){
					nextLvl.push(n.left);
				}
				if(n.right!=null){
					nextLvl.push(n.right);
				}
			}
			while(!nextLvl.isEmpty()){
				Node n = nextLvl.pop();
				System.out.print(n.data + " ");
				if(n.right!=null){
					currentLvl.push(n.right);
				}
				if(n.left!=null){
					currentLvl.push(n.left);
				}
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Tree t = new Tree();
		t.createTree(new int[]{10,8,15,1,9,11,17});
		t.inorderTraversal(root);
		System.out.println("-------preorderTraversal-----------");
		t.preorderTraversal(root);
		System.out.println("--------postorderTraversal----------");
		t.postorderTraversal(root);
		System.out.println("-------levelOrderTraversal-----------");
		t.levelOrderTraversal(root);
		System.out.println("--------levelOrderTraversalReverse----------");
		t.levelOrderTraversalReverse(root);
		System.out.println("--------levelOrderTraversalLineByLine----------");
		t.levelOrderTraversalLineByLine(root);
		System.out.println("--------levelOrderTraversalLineByLine2Q----------");
		t.levelOrderTraversalLineByLine2Q(root);
		System.out.println("--------zigzagTraversal----------");
		t.zigzagTraversal(root);
	}
}