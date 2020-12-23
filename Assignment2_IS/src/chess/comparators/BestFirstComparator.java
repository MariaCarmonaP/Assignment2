/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.comparators;

import chess.Node;
import java.util.Comparator;

/**
 *
 * @author Gabriel
 */
public class BestFirstComparator implements Comparator<Node> {
    
    @Override
    public int compare(Node n1, Node n2) {
        if (n1.heuristic > n2.heuristic) {
            return 1;
        } else if (n1.heuristic < n2.heuristic) {
            return -1;
        }
        else return 0;
    } 
    
}
