/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;

/**
 *
 * @author maric
 *
 */
public class Node {

    public State s;
    public Node parent = null;
    public int depth;
    public Action action;

    public double cost;
    public double heuristic;
    public double fN;

    public Node(State s) {
        this.s = s;
	cost = 0.0;
        depth=0;
        heuristic =  s.m_boardSize - this.s.m_agentPos.row;
        fN = cost + heuristic;
    }

    public Node(State s, Node parent, Action action) {
        this.s = s;
        this.parent = parent;
        this.depth = parent.depth+1;
        this.action = action;
        this.cost = this.parent.cost + this.action.getCost();
        this.heuristic = s.m_boardSize - this.s.m_agentPos.row;
        this.fN = this.cost + this.heuristic;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public ArrayList<Action> recoverPath() {
        Node aux = this;
        ArrayList<Action> sol = new ArrayList<>();
        while (!aux.isRoot()) {
            sol.add(aux.action);
            aux = aux.parent;
        }
        return sol;
    }
}
