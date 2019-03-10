/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymaxheapdemo;


/**
 *
 * @author Hallur
 */
class Node{
    Passenger data;
    Node left, right, parent;

    public Node(Passenger data) {
        this.data = data;
        this.left = null;
        this.right = null;

    } 
        public Node(Passenger data, Node parent) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = parent;

    }
}
