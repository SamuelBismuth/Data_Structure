package hw3;

//import java.util.Arrays;

/**
 * This class represents a node of the AVL tree.
 */
class AVLNode {

	int key; // the key
	int balance; // the balance factor
	int height; // height of the node
	int num; // number of input
	int desc; // number of children
	AVLNode left, right, parent;

	// the constructor
	AVLNode(int key, AVLNode parent) {
		this.key = key;
		this.parent = parent;
		left = right = null;
		height = 0;
		this.num = 1;
		this.desc = 1;
	}

	public String toString(){ return "key: "+key + ", (height="+height+", balance="+balance+")"; }
}

/**
 * This class represents the AVL tree.
 */
public class AVLtree {

	// the root
	private AVLNode root;

	// the constructor
	public AVLtree() { root = null; }

	// The function to insert key in subtree rooted
	// with node and returns true if this is a new key, otherwise  returns false 
	public boolean insert(int key) {
		/* Perform the normal BST insertion */
		if (root == null) root = new AVLNode(key, null);
		else {
			AVLNode n = root;
			AVLNode parent;
			boolean flag = true;
			while (flag) {
				if (n.key == key){
					n.num++;
					while (n.parent != null) {
						n.desc++;
						n = n.parent;
					}
					n.desc++;
					return true;
				}
				parent = n;
				boolean goLeft = n.key > key;
				if (goLeft) n = n.left;
				else n = n.right;
				//n = goLeft ? n.left : n.right;
				if (n == null) {
					if (goLeft) {
						parent.left = new AVLNode(key, parent);
						n = parent.left;
						while (n.parent != null) {
							n = n.parent;
							n.desc++;
						}
					} 
					else{
						parent.right = new AVLNode(key, parent);
						n = parent.right;
						while (n.parent != null) {
							n = n.parent;
							n.desc++;
						}
					}
					/*Update height and balance of the parent node */
					rebalance(parent);
					flag = false;
				}
			}
		}
		return true;
	}

	public int searchByPlace(int place) {
		AVLNode n = root;
		if(root == null) {
			return error();
		}
		if (place <= 0 || place > n.desc) return error();	
		while (n.right != null || n.left != null) {
			if (n.left == null) {
				if (n.num >= place) return n.key;
				else return n.right.key;
			}
			else if (n.right == null) {
				if (n.left.desc < place) return n.key;
				else return n.left.key;
			}
			else {
				if (n.left.desc + 1 <= place && place <= n.left.desc + n.num) return n.key;
				else if (n.left.desc + n.num > place) n = n.left;
				else {
					place = place - (n.left.desc + n.num);
					n = n.right;
				}
			}
		}
		return n.key;
	}

	private int error() {
		System.out.println("You've made a mistake in your input !");
		return -1;
	}

	// recursively update the balance of the nodes
	private void rebalance(AVLNode n) {
		// set the balance factor of the node 
		setBalance(n);
		if (n.balance == -2) {
			if (height(n.left.left) >= height(n.left.right)) n = rotateRight(n);
			else n = rotateLeftThenRight(n);
		} 
		else if (n.balance == 2) {
			if (height(n.right.right) >= height(n.right.left)) n = rotateLeft(n);
			else n = rotateRightThenLeft(n);
		}
		if (n.parent != null) rebalance(n.parent);
		else root = n;
	}

	// A function to left rotation of subtree rooted with a
	private AVLNode rotateLeft(AVLNode a) {
		AVLNode b = a.right;
		b.parent = a.parent;
		a.right = b.left;
		if (a.right != null) a.right.parent = a;
		b.left = a;
		a.parent = b;
		if (b.parent != null) {
			if (b.parent.right == a) b.parent.right = b;
			else b.parent.left = b;
		}
		if (a.right == null && a.left == null) a.desc = a.num;
		else if (a.right == null && a.left != null) a.desc = a.num + a.left.desc;
		else if (a.right != null && a.left == null) a.desc = a.num + a.right.desc;
		else a.desc = a.num + a.left.desc + a.right.desc;
		if (b.right == null && b.left != null) b.desc = b.num + b.left.desc;
		else if (b.right != null && b.left == null) b.desc = b.num + b.right.desc;
		else b.desc = b.num+b.right.desc+b.left.desc;
		setBalance(a, b);
		return b;
	}

	// A function to right rotation of subtree rooted with a
	private AVLNode rotateRight(AVLNode a) {
		AVLNode b = a.left;
		b.parent = a.parent;
		a.left = b.right;
		if (a.left != null) a.left.parent = a;
		b.right = a;
		a.parent = b;
		if (b.parent != null) {
			if (b.parent.right == a) b.parent.right = b;
			else b.parent.left = b;
		}
		if (a.right == null && a.left == null) a.desc = a.num;
		else if (a.right == null && a.left != null) a.desc = a.num + a.left.desc;
		else if (a.right != null && a.left == null) a.desc = a.num + a.right.desc;
		else  a.desc = a.num + a.left.desc + a.right.desc;
		if (b.right == null && b.left != null) b.desc = b.num + b.left.desc;
		else if (b.right != null && b.left == null) b.desc = b.num + b.right.desc;
		else b.desc = b.num + b.right.desc + b.left.desc;
		setBalance(a, b);
		return b;
	}

	// A function performs left then right rotations
	private AVLNode rotateLeftThenRight(AVLNode n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}

	// A function performs right then left rotations
	private AVLNode rotateRightThenLeft(AVLNode n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}

	// updates the height of the node
	public int height(AVLNode p) {
		if (p == null) return -1;
		return p.height;
	}

	//  set balance factor of the nodes
	private void setBalance(AVLNode... nodes) {
		for (AVLNode n : nodes) {
			int hLeft = height(n.left);
			int hRight = height(n.right);
			n.balance = hRight - hLeft;
			if (hLeft > hRight) n.height = hLeft + 1;
			else n.height = hRight + 1;
			//n.height = (hLeft > hRight ?  hLeft : hRight) + 1;
		}
	}

	// print the AVL Tree
	public void printTree() {
		if (root != null) {
			printTree(root.right, true, "");
			System.out.println(root.key + "  (" + root.num + " " + root.desc + ")");
			printTree(root.left, false, "");
		}
	}

	private void printTree(AVLNode node, boolean isRight, String indent) {
		if(node != null) {
			printTree(node.right, true, indent + (isRight ? "        " : " | "));
			System.out.print(indent);
			if (isRight) System.out.print(" /");
			else System.out.print(" \\");
			System.out.print("----- ");
			System.out.println(node.key + "  (" + node.num + " " + node.desc + ")");
			printTree(node.left, false, indent + (isRight ? " |      " : "  "));
		}
	}

	public void test() {
		AVLtree avlt = new AVLtree();
		int[] a = new int[100];
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 10) + 1;
		}
//		System.out.println(Arrays.toString(a));
		for (int i = 0; i < a.length; i++) {
			avlt.insert(a[i]);
		}
		avlt.printTree();
		System.out.println("The data of the place 3 is : " + avlt.searchByPlace(3));
//		int[] b = new int[a.length];
//		for (int i = 0; i < a.length; i++) {
//		b[i] = avlt.searchByPlace(i + 1);
//			}
//		Arrays.sort(a);
//		System.out.println(Arrays.toString(a));
//		System.out.println(Arrays.toString(b));
//		System.out.println(verification(a, b));
	}

	public boolean verification(int[] a, int[] b) {
		for (int i = 0; i < b.length; i++) if (a[i] != b[i]) return false; 
		return true;
	}

	public static void main(String[] args) {
		AVLtree AVLt = new AVLtree();
		AVLt.test();
	}
}