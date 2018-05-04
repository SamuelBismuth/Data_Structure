package hw3;

import java.util.Random;

/**
 * This class represents a node of the Binary tree.
 */
class BTNode { 

	int data;
	BTNode left, right;

	BTNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public String toString() { return "" + data; }
}

/**
 * This class represents the Binary tree.
 */
public class BinaryTree {

	private Random generator = new Random(19580427);
	private BTNode root;
	
	public BTNode getRoot() {
		return root;
	}

	// constructors
	public BinaryTree() { root = null; }

	// add a new node
	public void add(int data) {
		if (root==null) root = new BTNode(data);
		else {
			BTNode n = root;
			while (n != null) {
				double select = generator.nextDouble();
				if (select < 0.5) {
					if (n.left != null) n = n.left;
					else {
						n.left = new BTNode(data);
						n = null;
					}
				}
				else { // select >= 0.5
					if (n.right != null) n = n.right;
					else {
						n.right = new BTNode(data);
						n = null;
					}
				}
			}
		}
	}

	public boolean contains(int data) {	return contains(data, root); }

	public boolean contains(int data, BTNode node) {
		boolean ans = false;
		if (node==null) ans = false;
		else ans = node.data == (data) || contains(data, node.left) || contains(data, node.right);
		return ans;
	} 

	public boolean isEmpty() { return root == null; }

	// print all tree nodes
	// PreOrder
	public void printPreOrder() {
		printPreOrder(root);
		System.out.println();
	}

	void printPreOrder(BTNode node) { //PreOrder
		if (node != null) {
			System.out.print(node.data+", ");
			printPreOrder(node.left);
			printPreOrder(node.right);
		}
	}

	public void printPreorderPlus(){ printPreorderPlus("", root); }

	public void printPreorderPlus(String Path, BTNode node) {
		if (node != null) {
			System.out.println(node.data + ": " + Path);
			printPreorderPlus(Path+"L", node.left);
			printPreorderPlus(Path+"R", node.right);
		}
	}
}
