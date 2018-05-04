package hw3;

public class hw3 {

	/**
	 * Is the tree a Binary Search tree ?
	 */
	private static int max, max2 = Integer.MIN_VALUE;
	private static boolean isBT = true;

	public static boolean isBinarySearchTree(BinaryTree tree) {
		BTNode n = tree.getRoot();
		isBinarySearchTree(n);
		max = Integer.MIN_VALUE;
		max2 = Integer.MIN_VALUE;
		if(isBT == true) return true;
		else {
			isBT = true;
			return false;
		}
	}
	
	public static void isBinarySearchTree(BTNode n) {
		if (n != null) {
			isBinarySearchTree(n.left);
			max = n.data;
			if (max < max2) isBT = false;
			else max2 = max;
			isBinarySearchTree(n.right);
		}
	}

	public void test1() {
		BinaryTree bt = new BinaryTree();
		int nodes[] = new int[20];
		for (int i = 0; i < nodes.length; i++) nodes[i] = (int) (Math.random() * 10);
		for (int i = 0; i < nodes.length; i++) bt.add(nodes[i]);
		System.out.println("Is the tree a binary tree ? " + isBinarySearchTree(bt));
		// Verification
		//inorder(bt.getRoot());
	}

	public void inorder(BTNode n){
		if (n != null){
			inorder(n.left);
			System.out.print(n.data + ", ");
			inorder(n.right);
		}
	}

	/**
	 *  Is the tree a AVL tree ?
	 */
	private static boolean isAvl = true;

	public static int height(BSTNode n) {
		if (n == null) return 0;
		if (n.left != null && n.right == null) return height(n.left) + 1;
		if (n.left == null && n.right != null) return height(n.right) + 1;
		if (n.left != null && n.right != null) return Math.max(height(n.right), height(n.left)) + 1;
		else return 0;
	}

	public static boolean isAVLTree(BinarySearchTree tree) {
		BSTNode n = tree.getRoot();
		isAVLTree(n);
		return isAvl;
	}

	public static void isAVLTree(BSTNode n) {
		if (n != null) {
			if(balance(n) > 1) isAvl = false;
			isAVLTree(n.left);
			isAVLTree(n.right);
		}
	}

	public static int balance(BSTNode n) {
		if(n.left == null) return Math.abs(height(n.right) - (-1));
		else if (n.right == null) return Math.abs((-1) - height(n.left));
		else if (n.right != null && n.left != null) return Math.abs(height(n.right) - height(n.left));
		else return 0;
	}

	public void test2() {
		BinarySearchTree bst = new BinarySearchTree();
		int nodes[]= new int[20];
		for (int i = 0; i < nodes.length; i++) nodes[i] = (int) (Math.random() * 100);
		for (int i = 0; i < nodes.length; i++) bst.insert(nodes[i]);
		//bst.printPreorderPlus();
		System.out.println("Is the tree a AVL tree ? " + isAVLTree(bst));
	}

	public static void main(String[] args) {
		hw3 tree = new hw3();
		tree.test1();
		tree.test2();
	}
}