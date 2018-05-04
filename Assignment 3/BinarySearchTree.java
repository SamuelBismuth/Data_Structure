package hw3;

/**
 * This class represents a node of the Binary Search tree.
 */
class BSTNode {

	public int data;
	public int desc;
	public BSTNode left;
	public BSTNode right;
	public BSTNode parent;

	BSTNode(int newdata, BSTNode parent) {
		desc = 0;
		data = newdata;
		left = null;
		right = null;
		this.parent = parent;
	}

	BSTNode(int data, BSTNode left, BSTNode right, BSTNode parent) {
		this.desc = 0;
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public String toString(){
		return "data : " + data + " ";
	}
}

/**
 * This class represents the Binary Search tree.
 */
public class BinarySearchTree {

	// tree root
	private BSTNode root;

	public BSTNode getRoot() {
		return root;
	}

	public BinarySearchTree(){ root = null; }

	public void insert(int elem) {
		//			BSTNode newNode = new BSTNode(elem);
		if (root == null) root = new BSTNode(elem, null);
		else {
			BSTNode n = root;
			boolean flag = true;
			while (flag) {
				if (elem > n.data) {
					if (n.right != null) n = n.right;
					else {
						n.right = new BSTNode(elem, n);
						while(n != null) {
							n.desc++;
							n = n.parent;
						}
						flag = false;;
					}
				}
				else {
					if (n.left != null) n = n.left;
					else {
						n.left = new BSTNode(elem, n);
						while(n != null) {
							n.desc++;
							n = n.parent;
						}
						flag = false;;
					}
				}
			}
		}
	}

	// search for element elem
	public boolean find(int elem) { return find(root,elem); }

	boolean find(BSTNode tree, int elem) {
		if (tree == null) return false;
		if (elem == tree.data) return true;
		if (elem < tree.data) return find(tree.left, elem);
		else return find(tree.right, elem);
	}

	// print all tree nodes
	public void print() {
		if (root == null) System.out.println("empty tree");
		else {
			print(root);
			System.out.println();
		}
	}

	void print(BSTNode tree) { //Inorder
		if (tree != null) {
			print(tree.left);
			System.out.print(tree.data+", ");
			print(tree.right); 
		}
	}

	///////////////////////////
	public void remove(int elem) { root = remove(root, elem); }

	public static BSTNode remove(BSTNode node, int elem) {
		if(node != null) {
			if(elem > node.data) node.right = remove(node.right,elem);
			else if(elem < node.data) node.left = remove(node.left,elem);
			else{ //the node that should be deleted is found
				BSTNode n = node;
				while (n.parent != null) {
					n = n.parent;
					n.desc--;
				}
				if(node.left == null && node.right == null) node = null;
				else if(node.left != null && node.right == null) node = node.left; //the node has only one child (left)
				else if(node.right != null && node.left == null) node = node.right; //the node has only one child (right)
				else { //node "tree" has two children
					if(node.right.left == null) { // his right node has only one child (right)
						node.right.left = node.left;
						node = node.right;
					}
					else { // remove the smallest element
						BSTNode q, p = node.right;
						while (p.left.left != null) p = p.left;
						q = p.left;
						p.left = q.right;
						node.data = q.data;
					}
				}
			}
		}
		return node;
	}

	public boolean isEmpty() { return this.root == null; }

	public void printPreorderPlus() { printPreorderPlus("", root); }

	public void printPreorderPlus(String Path, BSTNode node) {
		if (node != null) {
			System.out.println(node.data + " : " + Path);
			printPreorderPlus(Path+"L", node.left);
			printPreorderPlus(Path+"R", node.right);
		}
	}
}