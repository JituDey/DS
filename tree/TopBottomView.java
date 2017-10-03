import java.util.*;
import java.util.Map.Entry;

class TopBottomView{
	public static void main(String[] args) {
		Tree t = new Tree();
		t.createTree(new int[]{10,8,15,1,9,11,17});
		
		System.out.println("==============bottomView==============");
		TopBottomView.bottomView(Tree.root);
		System.out.println("\n==============topView==============");
		TopBottomView.topView(Tree.root);
	}
	public static void topView(Node node){
		node.hd=0;
		Queue<Node> q = new LinkedList<Node>();
		q.add(node);
		Map<Integer, Integer> map = new TreeMap<>();
		while(!q.isEmpty()){
			Node n = q.remove();
			if(map.get(n.hd)==null){
				map.put(n.hd, n.data);
			}
			if(n.left!=null){
				n.left.hd=n.hd-1;
				q.add(n.left);
			}
			if(n.right!=null){
				n.right.hd=n.hd+1;
				q.add(n.right);
			}
		}
		
		Set<Entry<Integer, Integer>> set = map.entrySet();
		for(Entry ele : set){
			System.out.print(ele.getValue()+" ");
		}
	}
	
	public static void bottomView(Node node){
		node.hd=0;
		Queue<Node> q = new LinkedList<Node>();
		q.add(node);
		Map<Integer, Integer> map = new TreeMap<>();
		while(!q.isEmpty()){
			Node n = q.remove();
			map.put(n.hd, n.data);
			if(n.left!=null){
				n.left.hd=n.hd-1;
				q.add(n.left);
			}
			if(n.right!=null){
				n.right.hd=n.hd+1;
				q.add(n.right);
			}
		}
		
		Set<Entry<Integer, Integer>> set = map.entrySet();
		for(Entry ele : set){
			System.out.print(ele.getValue()+" ");
		}
	}
}