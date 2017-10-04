public class MirrorTree {
    public static void main(String[] args) {
        //Tree 1
        Node root1 = new Node(45);
        root1.left = new Node(35);
        root1.left.left = new Node(20);
        root1.left.right = new Node(38);
        root1.right = new Node(60);
        root1.right.left = new Node(50);

        //Tree 2
        Node root2 = new Node(45);
        root2.right = new Node(35);
        root2.right.right = new Node(20);
        root2.right.left = new Node(38);
        root2.left = new Node(60);
        root2.left.right = new Node(50);
		System.out.println("------Tree1--------");
		Tree.levelOrderTraversal(root1);
		System.out.println("------Tree2--------");
		Tree.levelOrderTraversal(root2);
		
		System.out.println("Given trees are mirror to each other : "+checkMirrorTree(root1,root2));
	}
	
	public static boolean checkMirrorTree(Node root1, Node root2) {
		if(root1==null && root2==null){
			return true;
		} else if((root1!=null && root2==null) || (root1==null && root2!=null)){
			return false;
		} else if(root1.data == root2.data) {
			return checkMirrorTree(root1.left, root2.right) && checkMirrorTree(root1.right, root2.left);
		} else{
			return false;
		}
	}
}