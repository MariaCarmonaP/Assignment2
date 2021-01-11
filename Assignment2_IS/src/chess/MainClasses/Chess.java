package chess.MainClasses;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import chess.adversarialSearch.Adversarial;
import chess.adversarialSearch.Alphabeta;
import chess.adversarialSearch.Minimax;
import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.Scanner;

public class Chess {

    public static void main(String[] args) {
////////////////////////////////////////////////////////////////////////////////
//VARIABLE DECLARATION
////////////////////////////////////////////////////////////////////////////////
        //Parameters
        String method = null;
        boolean initial = true;
        int depth = -1;
        String color = null;
        int maxTurns = -1;
        double p = -1;
        int seed = -1;
        // other-parameters
        String[] args1;

        State state;
        Adversarial machine = null; //La maquina, siempre habrá al menos un jugador que sea maquina
        //Adversarial black; //Piezas negras
////////////////////////////////////////////////////////////////////////////////
//PARAMETER VALIDATION
////////////////////////////////////////////////////////////////////////////////
        if ((args1 = checkUsage(args)) == null) {
            printUsage();
            System.exit(0);
        } else {
            method = args1[0];
            initial = Boolean.parseBoolean(args1[1]);
            depth = Integer.parseInt(args1[2]);
            color = args1[3];
            maxTurns = Integer.parseInt(args1[4]);
            p = 0.5;
            seed = 2020;
            if (args1.length > 5) {
                p = Double.parseDouble(args1[5]);
                if (args1.length > 6) {
                    seed = Integer.parseInt(args1[6]);
                }
            }
        }
////////////////////////////////////////////////////////////////////////////////
//INITIALIZING THE PROBLEM DEPENDING ON PARAMETERS
////////////////////////////////////////////////////////////////////////////////
        if (initial) {
            state = Utils.getChessInstance();
        } else {
            state = Utils.getChessInstancePosition(p, seed);
        }
        switch (method) {
            case "minimax":
                machine = new Minimax(depth, maxTurns);
                break;
            case "alphabeta":
                machine = new Alphabeta(depth, maxTurns);
        }
        switch (color) {
            case "white":
                humanMove(state, machine, 0);
                break;
            case "black":
                machine.initTurns(1);
                humanMove(state, machine, 1);
                break;
            case "both":
                both(state, machine);
                break;
            case "dummy":
                dummy(state, machine);
        }

    }

    public static void dummy(State s, Adversarial a) {
        boolean fin = false;
        Action action;
        //try{
        while (!fin) {
            Utils.printBoard(s);
            action = a.decision(s, 0);
            if (action == null) {
                fin = true;
                break;
            }
            //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nAction: "+action);
            s = s.applyAction(action);
            //Thread.sleep(1000);
        }
//        }catch (InterruptedException e){
//            System.out.println(e);
//        }
    }

    public static void whiteBlack(State s, Adversarial a) {
        boolean fin = false;
        Action action;
        while (!fin) {
            Utils.printBoard(s);
            action = a.decision(s, 0);
            s = s.applyAction(action);
        }
    }

    public static void both(State s, Adversarial a1) {
        int turns = 0;
        boolean fin = false;

        //Adversarial a2;
        Action action;

        System.out.println("///////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////INCIO PARTIDA//////////////////////////");
        System.out.println("///////////////////////////////////////////////////////////////");
        Utils.printBoard(s);

        action = a1.decision(s, 0);
        //a2 = a1.copy();
        if (action == null) {
            System.out.println("Max number of movements has been reached.");
            return;
        }
        s = s.applyAction(action);
        turns++;
        System.out.println("\n\tTURNO DE LAS BLANCAS: " + turns + "\n");
        Utils.printBoard(s);
        action = a1.decision(s, 1);
        if (action == null) {
            System.out.println("Max number of movements has been reached.");
            return;
        }
        s = s.applyAction(action);
        turns++;
        System.out.println("\n\tTURNO DE LAS NEGRAS: " + turns + "\n");
        Utils.printBoard(s);
        while (!fin) {
            action = a1.decision(s, 0);
            if (action == null) {
                break;
            }
            s = s.applyAction(action);
            turns++;
            System.out.println("\n\tTURNO DE LAS BLANCAS: " + turns + "\n");
            Utils.printBoard(s);

            action = a1.decision(s, 1);

            if (action == null) {
                System.out.println("Max number of movements has been reached.");
                return;
            }
            s = s.applyAction(action);
            turns++;
            System.out.println("\n\tTURNO DE LAS NEGRAS: " + turns + "\n");
            Utils.printBoard(s);
        }
    }

    public static void humanMove(State s, Adversarial a, int color) {

        Scanner read = new Scanner(System.in);

        Action action = null;

        boolean fin = false;

        boolean check = false;

        int rowSelected = -1, columnSelected = -1;

        Piece pieceSelected = null;

        System.out.println("///////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////INCIO PARTIDA//////////////////////////");
        System.out.println("///////////////////////////////////////////////////////////////");
        Utils.printBoard(s);

        while (!fin) {

            System.out.println("YOUR TURN: ");

            while (!check) {
                System.out.println("Choose the row of the piece you want to move");
                rowSelected = read.nextInt();
                System.out.println("Choose the column of the piece you want to move");
                columnSelected = read.nextInt();
                if (rowSelected > -1 && rowSelected < 8 && columnSelected > -1 && columnSelected < 8 && s.m_board[rowSelected][columnSelected] != -1 && s.m_color == color && s.m_board[rowSelected][columnSelected] < 12) {
                    check = true;
                    pieceSelected = Utils.choosePiece(s.m_board[rowSelected][columnSelected]);
                }
            }

            check = false;

            Position actualPosition = new Position(rowSelected, columnSelected);

            s.m_agent = pieceSelected.m_type;

            s.m_agentPos = actualPosition;

            ArrayList<Action> possibleActions = pieceSelected.getPossibleActions(s);

            while (!check) {
                System.out.println("Choose the row you want to move the piece");
                int rowToMove = read.nextInt();
                System.out.println("Choose the column you want to move the piece");
                int columnToMove = read.nextInt();

                Position newPos = new Position(rowToMove, columnToMove);
                action = new Action(actualPosition, newPos);

                if (possibleActions.contains(action)) {
                    check = true;
                }
            }
            System.out.println(action.toString());
            s = s.applyAction(action);
            Utils.printBoard(s);
            if (s.isFinal()) {
                System.out.println("\nHUMAN WINS\n");
                break;
            }
            System.out.println("///////////////////////////////////////////////////////////////");
            System.out.println("///////////////////////TURNO DEL RIVAL/////////////////////////");
            System.out.println("///////////////////////////////////////////////////////////////");
            action = a.decision(s, 1);
            if (action == null) {
                fin = true;
                break;
            }
            s = s.applyAction(action);
            Utils.printBoard(s);
            if (s.isFinal()) {
                System.out.println("\nMACHINE WINS\n");
                break;
            }
        }

    }

    public static String[] checkUsage(String[] args) {
        boolean wrong = false;
        if (args.length < 5 || args.length > 7) {
            System.out.println("\nError: incorrect number of parameters.\n");
            return null;
        }
        String[] args1 = args;
        switch (args[0]) {
            case "minimax":
                break;
            case "alphabeta":
                break;
            default:
                System.out.println("\nError: incorrect method passed (parameter 1).\n");
                System.out.println("Changing to Alpha Beta Prunning.");
                args[0] = "alphabeta";
                wrong = true;
        }
        switch (args[1]) {
            case "true":
                if (args.length != 5) {
                    System.out.println("\nParameters seed and probability will not be needed.\n");
                }
                break;
            case "false":
                if (args.length != 7) {
                    System.out.println("\nError: incorrect number of parameters.\nStarting the game from a provided position requires aditional parameters probability and seedprovided.\n");
                    return null;
                }
                break;
            default:
                System.out.println("\nError: incorrect initial value passed (parameter 2).\n");
                System.out.println("Changing to 'true' (game starts from the beginning).");
                args[1] = "true";
                wrong = true;
        }
        try {
            if (Integer.parseInt(args[2]) < 1) {
                System.out.println("\nError: incorrect depth limit passed (parameter 3).\n");
                System.out.println("Changing to 10.");
                args1[2] = "10";
                wrong = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: incorrect depth limit passed (parameter 3).\n");
            System.out.println("Changing to 10.");
            args1[2] = "10";
            wrong = true;
        }
        if (!("white".equals(args[3]) || "black".equals(args[3]) || "both".equals(args[3]) || "dummy".equals(args[3]))) {
            System.out.println("\nError: incorrect color passed (parameter 4).\n");
            System.out.println("Changing to both.");
            args[3] = "both";
            wrong = true;
        }
        try {
            if (Integer.parseInt(args[4]) < 0) {
                System.out.println("\nError: incorrect maximum turns passed (parameter 5).\n");
                System.out.println("Changing to 20.");
                args[4] = "20";
                wrong = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nError: incorrect maximum turns passed (parameter 5).\n");
            System.out.println("Changing to 20.");
            args[4] = "20";
            wrong = true;
        }
        if (args.length > 5) {
            try {
                if ((Double.parseDouble(args[5]) < 0) || (Double.parseDouble(args[5]) > 1)) {
                    System.out.println("\nError: incorrect probability passed (parameter 6).\n");
                    System.out.println("Changing to 0.5.");
                    args[5] = "0.5";
                    wrong = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: incorrect probability passed (parameter 6).\n");
                System.out.println("Changing to 0.5.");
                args[5] = "0.5";
                wrong = true;
            }
            if (args1.length > 6) {
                try {
                    if (Integer.parseInt(args[6]) < 0) {
                        System.out.println("\nError: incorrect seed passed (parameter 6).\n");
                        System.out.println("Changing to 2020");
                        args[6] = "2020";
                        wrong = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nError: incorrect seed passed (parameter 6).\n");
                    System.out.println("Changing to 2020");
                    args[6] = "2020";
                    wrong = true;
                }
            }
        }
        if (wrong) {
            System.out.println("\nSome parameters have been changed to default because they were wrong,");
            System.out.println("Do you wish to continue (Y) or exit the program and try again (N)?");
            System.out.println("Usage will be shown.");
            Scanner reader = new Scanner(System.in);
            String aux = reader.nextLine();
            while (wrong) {
                switch (aux) {
                    case "Y":
                    case "y":
                    case "yes":
                    case "Yes":
                    case "YES":
                        wrong = false;
                        break;
                    case "N":
                    case "n":
                    case "no":
                    case "No":
                    case "NO":
                        printUsage();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nPlease write Y or N.");
                }
            }
        }

        return args1;
    }

    public static void printUsage() {
        System.out.println("Correct usage requires 5, 6 or 7 parameters:");
        System.out.println("1.- Method: The algorithm to use.\n\tValid values: 'minimax' or 'alphabeta'.");
        System.out.println("2.- Initial: Indicates if the game starts a new game or a provided position.\n\tValid values: 'true' or 'false'.");
        System.out.println("3.- Depth: Maximum allowed depth for the tree search.\n\tValid values: should be >0.");
        System.out.println("4.- Color: Valid values:\n\twhite/black: indicates the color of the agent.\n\tboth: indicates that both players will be agents.\n\tdummy:  indicates that the opponent will not do anything.");
        System.out.println("5.- Max turns: Game stops when this number of turns is reached, should be >0.");
        System.out.println("6.- Probability: [Optional] Probability of the pieces appearing on the board.\n\tValid values: 0<=p<=1.");
        System.out.println("7.- Seed: [Optional] Seed to generate the initial configuration.\n\tValid values: Should be >0.");

    }
}
