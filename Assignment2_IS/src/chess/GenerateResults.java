/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maric
 */
public class GenerateResults {

    public static void main(String[] args) {
        int size = 15;
        int count = 0;
        //double prob = 0.5;
        int i;
        int nSol;
        boolean noSol[] = new boolean[180];
        double cost;
        double costSD;
        double aCost[] = new double[180];
        double length;
        double lengthSD;
        double aLength[] = new double[180];
        double genN;
        double genNSD;
        double aGenN[] = new double[180];
        double expaN;
        double expaNSD;
        double aExpaN[] = new double[180];
        double exploN;
        double exploNSD;
        double aExploN[] = new double[180];
        double startTime;
        double stopTime;
        double Ttime;
        double time;
        double timeSD;
        double aTime[] = new double[180];
        ArrayList<Action> solution;
        State state;
        Search search;
//        FileWriter fw[] = new FileWriter[6];
//        PrintWriter pw[] = new PrintWriter[6];
        FileWriter fw1[] = new FileWriter[6];
        PrintWriter pw1[] = new PrintWriter[6];
        String methods[] = {"breadth-first", "depth-first", "uniform-cost", "iterative-deepening", "best-first", "a-star"};
        String m;
        try {
            for (int k = 0; k < 6; k++) {
                m = methods[k];
                fw1[k] = new FileWriter(new File("C:\\Users\\maric\\Documents\\1erCuatri\\Inteligentes\\practices\\Results\\" + m + ".txt"), true);
                pw1[k] = new PrintWriter(fw1[k]);
            }
            for (double dens = 0; dens < 1.01; dens=dens+0.01) {
                for (int k = 0; k < 6; k++) {
                    m = methods[k];
//                fw[k] = new FileWriter(new File("C:\\Users\\maric\\Documents\\1erCuatri\\Inteligentes\\practices\\"+m+"Results.txt"), true);
//                pw[k] = new PrintWriter(fw[k]);

                    //pw[k].println("n_run\tpiece\tseed\tgenN\texpaN\texploN\tlength\tcost\ttime");
                    //pw1[k].println("piece\tnSol/30\tgenN\tSD_GenN\texpaN\tSD_ExpaN\texploN\tSD_ExploN\tlength\tSD_Length\tcost\tSD_Cost\ttime\tSD_Time");
                    i = 0;
                    cost = 0;
                    length = 0;
                    genN = 0;
                    expaN = 0;
                    exploN = 0;
                    Ttime = 0;
                    costSD = 0;
                    lengthSD = 0;
                    genNSD = 0;
                    expaNSD = 0;
                    exploNSD = 0;
                    timeSD = 0;
                    nSol = 0;
                    for (int piece = 0; piece < 6; piece++) {

                        for (int seed1 = 1; seed1 <= 30; seed1++) {
                            state = Utils.getProblemInstance(size, dens, seed1, piece);
                            switch (m) {
                                case "breadth-first":
                                    search = new BreadthFirst(state);
                                    break;
                                case "depth-first":
                                    search = new DepthFirst(state);
                                    break;
                                case "uniform-cost":
                                    search = new UniformCost(state);
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
                                    search = new BreadthFirst(state);
                            }
                            startTime = System.nanoTime();
                            solution = search.doSearch();
                            stopTime = System.nanoTime();
                            count++;
                            System.out.println(count+"\t    density: "+dens);
                            time = (stopTime - startTime);
                            aTime[i] = time;
                            Ttime += time;
                            if (solution != null) {
                                cost += search.current.cost;
                                aCost[i] = search.current.cost;
                                length += solution.size();
                                aLength[i] = solution.size();
                                noSol[i] = true;
                                genN += search.N_NODES;
                                aGenN[i] = search.N_NODES;
                                expaN += search.N_EXPAND;
                                aExpaN[i] = search.N_EXPAND;
                                exploN += search.N_EXPLO;
                                aExploN[i] = search.N_EXPLO;
                                nSol++;
                            } else {

                                noSol[i] = false;
                                aLength[i] = -1;
                                aCost[i] = -1;
                            }
                            //pw[k].println(i + "\t" + piece + "\t" + seed1 + "\t" + search.N_NODES + "\t" + search.N_EXPAND + "\t" + search.N_EXPLO + "\t" + aLength[i] + "\t" + aCost[i] + "\t" + time);
                            i++;
                        }

                        //pw1[k].println(SIZE + piece + "\t"+ nSol+"\t"+ genN + "\t" + genNSD + "\t" + expaN + "\t" + expaNSD + "\t" + exploN + "\t" + exploNSD + "\t" + length + "\t" + lengthSD + "\t" + cost + "\t" + costSD + "\t" + Ttime + "\t" + timeSD);
                    }
                    Ttime /= nSol;
                    cost /= nSol;
                    length /= nSol;
                    genN /= nSol;
                    expaN /= nSol;
                    exploN /= nSol;
                    for (int j = 0; j < i; j++) {
                        if (noSol[j]) {
                            timeSD += Math.pow(Math.abs(aTime[j] - Ttime), 2);
                            costSD += Math.pow(Math.abs(aCost[j] - cost), 2);
                            lengthSD += Math.pow(Math.abs(aLength[j] - length), 2);
                            genNSD += Math.pow(Math.abs(aGenN[j] - genN), 2);
                            expaNSD += Math.pow(Math.abs(aExpaN[j] - expaN), 2);
                            exploNSD += Math.pow(Math.abs(aExploN[j] - exploN), 2);
                        }
                    }
                    timeSD = Math.sqrt(timeSD / (nSol - 1));
                    costSD = Math.sqrt(costSD / (nSol - 1));
                    lengthSD = Math.sqrt(lengthSD / (nSol - 1));
                    genNSD = Math.sqrt(genNSD / (nSol - 1));
                    expaNSD = Math.sqrt(expaNSD / (nSol - 1));
                    exploNSD = Math.sqrt(exploNSD / (nSol - 1));
                    pw1[k].format("%f\t%d\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f", dens, nSol, genN, genNSD, expaN, expaNSD, exploN);
                    pw1[k].format("\t%.0f\t%.1f\t%.1f\t%.1f\t%.1f\t%.0f\t%.0f\n", exploNSD, length, lengthSD, cost, costSD, Ttime, timeSD);
//                fw[k].close();
//                pw[k].close();
//                fw1[k].close();
//                pw1[k].close();
                }
            }
            for (int k = 0; k < 6; k++) {
                fw1[k].close();
                pw1[k].close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateResults.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerateResults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
