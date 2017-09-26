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
	
	public static void main(String[] args) {
		Tree t = new Tree();
		t.createTree(new int[]{10,8,15,1,9,11,17});
		t.inorderTraversal(root);
		System.out.println("------------------");
		t.preorderTraversal(root);
		System.out.println("------------------");
		t.postorderTraversal(root);
	}
}