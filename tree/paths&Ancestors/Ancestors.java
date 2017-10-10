import java.util.*;
class Ancestors{
	/*
			    20
			  /    \
			 8		22
			/ \
		   4  12
			  /  \
			 10  14
	*/
	static Boolean v1=false;
	static Boolean v2=false;
	static List<Integer> path1 = new ArrayList<>();
	static List<Integer> path2 = new ArrayList<>();
	public static void main(String[] args){
		Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
		System.out.println("Ancestors of given node "+ root.left.right.right.data +" are :");
		Ancestors.printAllAncestorsOfANode(root, root.left.right.right);// ancestors of 14 -> 12,8,20
		System.out.println();
		System.out.println("Lowest Common Ancestor of given nodes "+ root.right.data +" and "+ root.left.right.left.data +" is :"
		+ Ancestors.findLCA2(root, root.right, root.left.right.left).data);
		System.out.println("Lowest Common Ancestor of given nodes "+ root.right.data +" and "+ root.left.right.left.data +" is :"
		+ Ancestors.findLCA1(root, root.right, root.left.right.left));
		System.out.println("Lowest Common Ancestor of given nodes "+ root.left.right.right.data +" and "+ root.left.right.left.data +" is :"
		+ Ancestors.findLCAOfBST(root, root.left.right.right, root.left.right.left));
		System.out.println("Lowest Common Ancestor of given nodes "+ root.left.right.right.data +" and "+ root.left.right.left.data +" is :"
		+ Ancestors.findLCA3(root, root.left.right.right, root.left.right.left).data);
	}
	/*
	We can solve this problem using BST properties. We can recursively traverse the BST from root. The main idea of the solution is, while traversing from top to bottom, the first node n we encounter with value between n1 and n2, i.e., n1 < n < n2 or same as one of the n1 or n2, is LCA of n1 and n2 (assuming that n1 < n2). So just recursively traverse the BST in, if node’s value is greater than both n1 and n2 then our LCA lies in left side of the node, if it’s is smaller than both n1 and n2, then LCA lies on right side. Otherwise root is LCA (assuming that both n1 and n2 are present in BST)
	*/
	public static int findLCAOfBST(Node root, Node n1, Node n2){
		if(root==null) return -1;
		if(root.data > n1.data && root.data > n2.data){
			return findLCAOfBST(root.left, n1, n2);
		}
		if(root.data < n1.data && root.data < n2.data){
			return findLCAOfBST(root.right, n1, n2);
		}
		return root.data;
	}
	
	public static int printAllAncestorsOfANode(Node root, Node node){
		if(root==null || node == null){
			return 0;
		}
		if(root.left==node || root.right==node || printAllAncestorsOfANode(root.left, node)==1 || printAllAncestorsOfANode(root.right, node)==1){
			System.out.print(root.data+"\t");
			return 1;
		}
		return 0;
	}
	
	/*Method 1 (By Storing root to n1 and root to n2 paths):
	Following is simple O(n) algorithm to find LCA of n1 and n2.
	1) Find path from root to n1 and store it in a vector or array.
	2) Find path from root to n2 and store it in another vector or array.
	3) Traverse both paths till the values in arrays are same. Return the common element just before the mismatch.*/
	public static int findLCA1(Node root, Node n1, Node n2){
		if(!findPath(root, n1, path1) || !findPath(root, n2, path2))
			return -1;
		int i;
		for(i=0;i<path1.size() && i<path2.size();i++){
			if(!(path1.get(i).equals(path2.get(i)))){
				break;
			}
		}
		return path1.get(i-1);
	}
	
	public static boolean findPath(Node root, Node node, List<Integer> path){
		if(root==null) return false;
		path.add(root.data);
		if(root.data==node.data)
			return true;
		if(root.left!=null && findPath(root.left, node, path))
			return true;
		if(root.right!=null && findPath(root.right, node, path))
			return true;
		path.remove(path.size()-1);
		return false;
	}
	
	/*Method 2:
	The idea is to traverse the tree starting from root. If any of the given keys (n1 and n2) matches with root, then root is LCA (assuming that both keys are present). If root doesn’t match with any of the keys, we recur for left and right subtree. The node which has one key present in its left subtree and the other key present in right subtree is the LCA. If both keys lie in left subtree, then left subtree has LCA also, otherwise LCA lies in right subtree.*/
	public static Node findLCA2(Node root, Node n1, Node n2){
		v1=v2=false;
		Node lca = findLCAUtil(root, n1, n2);
		if(v1 && v2) return lca;
		return null;
	}
	
	public static Node findLCAUtil(Node root, Node n1, Node n2){
		if(root==null) return null;
		if(root.data==n1.data){
			v1=true;
			return root;
		}
		if(root.data==n2.data){
			v2=true;
			return root;
		}
		
		Node lLCA = findLCAUtil(root.left, n1, n2);
		Node rLCA = findLCAUtil(root.right, n1, n2);
		
		if(lLCA!=null && rLCA!=null)
			return root;
		return (lLCA!=null)? lLCA : rLCA;
	}
	
	/* Method 3
	Create an empty hash table.
	Insert n1 and all of its ancestors in hash table.
	Check if n2 or any of its ancestors exist in hash table, if yes return the first existing ancestor.
	*/
	public static Node findLCA3(Node root, Node n1, Node n2){
		List<Node> ancestorList1 = new ArrayList<Node>();
		getAncestorsOfANode(root, n1, ancestorList1);
		Map<Integer, List<Node>> map1 = new HashMap<>();
		map1.put(n1.data, ancestorList1);
		Node n = checkN2AncestorsExistInMap(root, n1, n2, map1);
		System.out.println(n.data);
		return (n==null ? null : n);
	}
	
	public static int getAncestorsOfANode(Node root, Node node, List<Node> ancestorList){
		if(root==null) return 0;
		if(root.data==node.data || getAncestorsOfANode(root.left, node, ancestorList)==1 || getAncestorsOfANode(root.right, node, ancestorList)==1){
			ancestorList.add(root);
			return 1;
		}
		return 0;
	}
	
	public static Node checkN2AncestorsExistInMap(Node root, Node n1,Node node, Map<Integer, List<Node>> map){
		if(root==null) return null;
		List<Node> list = map.get(n1.data);
		
		if(root.data==node.data || printAllAncestorsOfANode(root.left, node)==1 || printAllAncestorsOfANode(root.right, node)==1){
			if(list.contains(root)){
				System.out.println("found match, returning : "+root.data);
				return root;
			}
		}
		return null;
	}
}