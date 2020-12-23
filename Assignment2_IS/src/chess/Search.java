/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author maric
 */
public abstract class Search {

    Node current = null;
    Piece piece = null;
    HashSet<Position> visited = null;
    ArrayList<Node> successors = null;
    int N_NODES = 0;
    int N_EXPAND = 0;
    int N_EXPLO = 0;
    Collection<Node> open;

    Search(State s0) {
        current = new Node(s0);
        N_NODES++;
        switch (s0.m_agent) {
            case Utils.wRook:
                piece = new Rook(0);
                break;
            case Utils.bRook:
                piece = new Rook(1);
                break;
            case Utils.wPawn:
                piece = new Pawn(0);
                break;
            case Utils.bPawn:
                piece = new Pawn(1);
                break;
            case Utils.wBishop:
                piece = new Bishop(0);
                break;
            case Utils.bBishop:
                piece = new Bishop(1);
                break;
            case Utils.wKnight:
                piece = new Knight(0);
                break;
            case Utils.bKnight:
                piece = new Knight(1);
                break;
            case Utils.wQueen:
                piece = new Queen(0);
                break;
            case Utils.bQueen:
                piece = new Queen(1);
                break;
            case Utils.wKing:
                piece = new King(0);
                break;
            case Utils.bKing:
                piece = new King(1);
                break;
        }
    }

    public ArrayList<Action> doSearch(){
        N_EXPLO++;
        if (current.s.isFinal()) {
            return new ArrayList<>(0);
        }
        visited = new HashSet<>();
        visited.add(current.s.m_agentPos);
        successors = generateChildren();
        N_EXPAND++;
        open.addAll(successors);
        while(!open.isEmpty()){
            current=extractNode(open);
            N_EXPLO++;
            if(!visited.contains(current.s.m_agentPos)){
                if(current.s.isFinal()){
                    return current.recoverPath();
                }
                successors=generateChildren();
                N_EXPAND++;
                open.addAll(successors);
                visited.add(current.s.m_agentPos);
            }
        }
        return null;
    }
    
    public Node extractNode(Collection<Node> o){
        if(o instanceof Stack){
            return (Node)((Stack) o).pop();
        } else if(o instanceof LinkedList){
            return (Node)((LinkedList) o).poll();
        }else{
            return (Node)((PriorityQueue) o).poll();
        }
    }
    

    public ArrayList<Node> generateChildren() {
        ArrayList<Node> sol = new ArrayList<>();
        Action a;
        ArrayList<Action> possibleActions = piece.getPossibleActions(current.s);
        for (int i = 0; i < possibleActions.size(); i++) {
            a = possibleActions.get(i);
            sol.add(new Node((current.s.applyAction(a)), current, a));
            N_NODES++;
        }
        return sol;
    }
}
