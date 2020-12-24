/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.MainClasses;

import chess.Action;
import chess.State;
import chess.search.UniformCost;
import chess.search.Search;
import chess.search.DepthLimited;
import chess.search.BestFirst;
import chess.search.DepthFirst;
import chess.search.AStar;
import chess.search.BreadthFirst;
import chess.search.IterativeDeepening;
import chess.Utils;
import java.util.ArrayList;

/**
 *
 * @author maric
 */
public class SearchChess {

    public static void main(String[] args) {
        String method;
        int size;
        double probability;
        int agent;
        int seed;
        int limit = 0;
        double cost = 0.0;
        ArrayList<Action> solution;
        Search search;
        if (args.length < 0 || args.length > 6) {
            System.out.println("\n**Sorry, correct usage require 5 or 6 params:");
            System.out.println("1.- Method: options are breadth-first, depth-first, uniform-cost, depth-limited, iterative-deepening, best-first y a-star");
            System.out.println("2.- Board size: int.");
            System.out.println("3.- Probability: (0.1,1]. Probability for each piece to be included.");
            System.out.println("4.- Agent: {0,1,2,3,4,5} standing for white pawn, rook, bishop, knight, queen or king.");
            System.out.println("5.- Seed: int.  To initialize the problem instance random number generator (for reproducibility)");
            System.out.println("6.- Depth limit (optional: for the depth limited search algorithm. Should be a positive integer.");
            System.exit(0);
        }
        method = args[0];
        size = Integer.parseInt(args[1]);
        probability = Double.parseDouble(args[2]);
        agent = Integer.parseInt(args[3]);
        seed = Integer.parseInt(args[4]);
        if (size < 4) {
            System.out.println("\nSorry: board to small, modified to 4");
            size = 4;
        }
        if ((probability < 0.1) || (probability > 1)) {
            System.out.println("\nSorry: bad probability value, modified to 0.25");
            probability = 0.25;
        }
        if ((probability * 32) > (size * size)) {
            System.out.println("\nSorry: too much pieces for the board size, modifying density to 0.25");
            probability = 0.25;
        }
        if ((agent < 0) || (agent > 11)) {
            System.out.println("\nSorry: bad selected agent, modified to 1 (white rook)");
            agent = Utils.wRook;
        }
        if (args.length == 6) {
            if (method.equals("depth-limited")) {
                limit = Integer.parseInt(args[5]);
                if (limit < 0) {
                    System.out.println("\nSorry: bad depth limit, modified to 4.");
                    limit=4;
                }
            } else {
                System.out.println("Parameter 6 is only needed for depth-limited algorithm, it will not be used for the one selected.");
            }
        }
        // getting the initial state
        State state = Utils.getProblemInstance(size, probability, seed, agent);
        Utils.printBoard(state);
        switch (method) {
            case "breadth-first":
                search = new BreadthFirst(state);
                break;
            case "depth-first":
                search = new DepthFirst(state);
                break;
            case "uniform-cost":
                search = new UniformCost(state);
                break;
            case "depth-limited":
                search = new DepthLimited(state, limit);
                break;
            case "iterative-deepening":
                search = new IterativeDeepening(state);
                break;
            case "best-first":
                search = new BestFirst(state);
                break;
            case "a-star":
                search = new AStar(state);
                break;
            default:
                System.out.println("Sorry: wrong method, modifying it to breadth-first");
                search = new BreadthFirst(state);

        }
        solution = search.doSearch();
        System.out.println("Number of generated nodes: " + search.N_NODES);
        System.out.println("Number of expanded nodes: " + search.N_EXPAND);
        if (solution == null) {
            System.out.println("\nSorry, no solution found ....");
        } else {
            System.out.println("Solution length: " + solution.size());
            for (Action a : solution) {
                cost = cost + a.getCost();
            }
            System.out.println("Solution cost:   " + cost);

            System.out.println("Solution:\n");
            for (int i = solution.size() - 1; i >= 0; i--) {
                System.out.println((solution.size() - i) + ": " + solution.get(i));
            }

            Utils.printBoard(search.current.s);
        }
    }
}
