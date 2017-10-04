/*
	   45
     /    \
    35    60
   / \    /
  20  38 50
*/
public class PathsOfTree {
	
	/*
	   45
  /    \
 35    60
/ \    /
20  38 50
*/
	static Node targetLeaf = null;
	public static void main(String[] args) {
		 Node root1 = new Node(45);
		 root1.left = new Node(35);
		 root1.left.left = new Node(20);
		 root1.left.right = new Node(38);
		 root1.right = new Node(60);
		 root1.right.left = new Node(50);
		 System.out.println("------All Possible Paths: --------");
		 allPathsOfTree(root1);
		 System.out.println("------Max sum of all paths: --------");
		 findMaxSumOfPaths(root1);
		 System.out.println("------Path with max sum: --------");
		 findPathWithMaxSum(root1);
	}
	
	public static void allPathsOfTree(Node root) {
		if(root==null) return;
		
		int[] path = new int[1000];
		findPath(path, 0, root);
	}
	/*
	Algorithm:
	Use a path array path[] to store current root to leaf path. Traverse from root to all leaves 
	in top-down fashion. While traversing, store data of all nodes in current path in array path[]. 
	When we reach a leaf node, print the path array.
	*/
	public static void findPath(int[] path, int len, Node root) {
		if(root==null)	return;
		path[len] = root.data;
		len++;
		if(root.left==null && root.right==null){
			printPath(path, len);
		}
		findPath(path, len, root.left);
		findPath(path, len, root.right);
	}
	
	public static void printPath(int[] path, int len) {
		for(int i = 0; i<len; i++){
			System.out.print(path[i]+" ");
		}
		System.out.println();
	}
	
	public static void findMaxSumOfPaths(Node root) {
		if(root==null)	return;
		int maxSum=0;
		int sum = calculateMaxSum(0, maxSum, root);
		if(sum>maxSum){
			maxSum=sum;
		}
		System.out.println(maxSum);
	}
	
	public static int calculateMaxSum(int sum, int maxSum, Node root) {
		if(root==null)	return 0;
		sum+=root.data;
		
		if(root.left==null && root.right==null){
			return sum;
		} else{
			return Math.max(calculateMaxSum(sum,maxSum, root.left), calculateMaxSum(sum,maxSum, root.right));
		}
	}
	
	public static void findPathWithMaxSum(Node root) {
		if(root==null)	return;
		findTargetLeaf(0, 0, root);
		System.out.println(targetLeaf.data);
		printPath(root);
	}
	
	private static boolean printPath(Node root) {
		if(root==null) return false;
		if(root == targetLeaf || printPath(root.left) || printPath(root.right)){
			System.out.print(root.data + " ");
			return true;
		}
		return false;
	}

	public static void findTargetLeaf(int sum, int maxSum, Node root) {
		if(root==null)	return;
		sum+=root.data;
		
		if(root.left==null && root.right==null){
			if(sum>maxSum){
				maxSum=sum;
				targetLeaf=root;
			}
		} else{
			findTargetLeaf(sum,maxSum, root.left);
			findTargetLeaf(sum,maxSum, root.right);
		}
	}
}