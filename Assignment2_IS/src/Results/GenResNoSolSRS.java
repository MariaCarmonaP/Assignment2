/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Results;

import chess.SimpleRandomSearch;
import chess.State;
import chess.pieces.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maric
 */
public class GenResNoSolSRS {

    public static void main(String[] args) {
        int size = 15;
        double prob = 0.5;
        int piece = 0;
        int i;
        int n_sol;
        boolean noSol[] = new boolean[900];
        double cost;
        double costSD;
        double aCost[] = new double[900];
        double length;
        double lengthSD;
        double aLength[] = new double[900];
        double genN;
        double genNSD;
        double aGenN[] = new double[900];
        double expaN;
        double expaNSD;
        double aExpaN[] = new double[900];
        double exploN;
        double exploNSD;
        double aExploN[] = new double[900];
        double startTime;
        double stopTime;
        double Ttime;
        double time;
        double timeSD;
        double aTime[] = new double[900];
        State state;
        SimpleRandomSearch srs;
        FileWriter fw;
        PrintWriter pw;
        FileWriter fw1;
        PrintWriter pw1;
        try {
            fw = new FileWriter(new File("C:\\Users\\maric\\Documents\\1erCuatri\\Inteligentes\\practices\\NOSOLSRSResults.txt"), true);
            pw = new PrintWriter(fw);
            fw1 = new FileWriter(new File("C:\\Users\\maric\\Documents\\1erCuatri\\Inteligentes\\practices\\NOSOLSRSFinalResults.txt"), true);
            pw1 = new PrintWriter(fw1);
            pw.println("n_run\tpiece\tseed1\tseed2\tgenN\texpaN\texploN\ttime");
            pw1.println("piece\tn_sol/900\tgenN\tSD_GenN\texpaN\tSD_ExpaN\texploN\tSD_ExploN\ttime\tSD_Time");
            for (; piece < 6; piece++) {
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
                n_sol = 0;
                for (int seed1 = 1; seed1 <= 30; seed1++) {
                    for (int seed2 = 1; seed2 <= 30; seed2++) {

                        state = Utils.getProblemInstance(size, prob, seed1, piece);
                        srs = new SimpleRandomSearch(state, seed2);
                        startTime = System.nanoTime();
                        srs.doSearch();
                        stopTime = System.nanoTime();
                        time = (stopTime - startTime);
                        aTime[i] = time;
                        Ttime += time;
                        if (srs.solutionFound) {
                            noSol[i] = true;
                            cost += srs.m_cost;
                            aCost[i] = srs.m_cost;
                            length += srs.m_solution.size();
                            aLength[i] = srs.m_solution.size();
                            
                        } else {
                            genN += srs.genN;
                            aGenN[i] = srs.genN;
                            expaN += srs.expaN;
                            aExpaN[i] = srs.expaN;
                            exploN += srs.exploN;
                            aExploN[i] = srs.exploN;
                            n_sol++;
                            noSol[i] = false;
                            aLength[i] = -1;
                            aCost[i] = -1;
                            pw.println(i + "\t" + piece + "\t" + seed1 + "\t" + seed2 + "\t" + srs.genN + "\t" + srs.expaN + "\t" + srs.exploN + "\t" + time);
                        
                        }
                        i++;
                    }
                }
                Ttime /= n_sol;
                genN /= n_sol;
                expaN /= n_sol;
                exploN /= n_sol;
                for (int j = 0; j < i; j++) {
                    if (!noSol[j]) {
                        timeSD += Math.pow(Math.abs(aTime[j] - Ttime), 2);
                        genNSD += Math.pow(Math.abs(aGenN[j] - genN), 2);
                        expaNSD += Math.pow(Math.abs(aExpaN[j] - expaN), 2);
                        exploNSD += Math.pow(Math.abs(aExploN[j] - exploN), 2);
                    }
                }
                timeSD = Math.sqrt(timeSD / (n_sol - 1));
                costSD = Math.sqrt(costSD / (n_sol - 1));
                lengthSD = Math.sqrt(lengthSD / (n_sol - 1));
                genNSD = Math.sqrt(genNSD / (n_sol - 1));
                expaNSD = Math.sqrt(expaNSD / (n_sol - 1));
                exploNSD = Math.sqrt(exploNSD / (n_sol - 1));
                pw1.format("%d\t%d\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f\t", piece, n_sol, genN, genNSD, expaN, expaNSD, exploN);
                pw1.format("%.0f\t%.0f\t%.0f\n", exploNSD, Ttime, timeSD);
                //pw1.println(piece + "\t" + n_sol+ "\t" + genN + "\t" + genNSD + "\t" + expaN + "\t" + expaNSD + "\t" + exploN + "\t" + exploNSD + "\t" + length + "\t" + lengthSD + "\t" + cost + "\t" + costSD + "\t" + Ttime + "\t" + timeSD);
            }
            fw.close();
            pw.close();
            fw1.close();
            pw1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateResultsSRS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerateResultsSRS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
