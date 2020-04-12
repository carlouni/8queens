package com.example.java;

import com.example.java.model.Pair;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Main class solving "The Eight Queens" problem.
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<ArrayList<Pair>> solutions = new ArrayList<>();
        traverse(getEmptyBoard(), (short) 0, new ArrayList<Pair>(), solutions);
        short counter = 0;
        for (ArrayList<Pair> solution: solutions) {
            System.out.println("Solution " + (short) (counter + 1));
            System.out.println("---------------------------------");
            Pair item;
            for (int i = 0; i < 8; i++) {
                item = solution.get(i);
                for (int j = 0; j < 8; j++) {

                    if ( item.y == j ) {
                        System.out.print("| * ");
                    } else {
                        System.out.print("|   ");
                    }
                }
                System.out.println("|");
                System.out.println("---------------------------------");
            }
            counter = (short) (counter + 1);
        }
    }

    /**
     * Traverse through search tree to find solutions
     * @param list positions available for placing the queen
     * @param row row on the chessboard
     * @param possibleSolution list of pairs of potential solution
     * @param solutions list of valid solutions
     * @return Pair
     */
    public static Pair traverse(ArrayList<Pair> list, short row, ArrayList<Pair> possibleSolution, ArrayList<ArrayList<Pair>> solutions) {
        ArrayList<Pair> newList = list
                .stream()
                .filter((Pair pair) -> pair.x == row)
                .collect(Collectors.toCollection(ArrayList<Pair>::new));

        if (newList.size() == 0 || row == 8) {
            if (possibleSolution.size() == 8) {
                solutions.add(possibleSolution);
            }
            return null;
        } else {
            for (short i = 0; i < newList.size(); i++) {
                Pair item = newList.get(i);
                ArrayList<Pair> newPossibleSolution = (ArrayList<Pair>) possibleSolution.clone();
                newPossibleSolution.add(item);
                short nextRow = (short) (row + 1);
                traverse(pickPair(list, item.x, item.y), nextRow, newPossibleSolution, solutions);
            }
        }
        return null;
    }

    /**
     * Returns positions available for queens to be placed after taking one position for the queen
     * @param list available positions
     * @param x row on the chessboard
     * @param y column on the chessboard
     * @return ArrayList<Pair>
     */
    public static ArrayList<Pair> pickPair(ArrayList<Pair> list, short x, short y) {
        ArrayList<Pair> newList = new ArrayList<>();

        // clear positions where other queens can't be placed
        newList = list
                .stream()
                .filter((Pair pair) -> (pair.x != x && pair.y != y && Math.abs(pair.y - y) != Math.abs(pair.x - x )))
                .collect(Collectors.toCollection(ArrayList<Pair>::new));
        return newList;
    }

    /**
     * Generate an empty chessboard.
     * @return ArrayList<Pair>
     */
    public static ArrayList<Pair> getEmptyBoard() {
        ArrayList<Pair> list = new ArrayList<>();
        for (short i = 0; i < 8; i++) {
            for (short j = 0; j < 8; j++) {
                list.add(new Pair(i, j));
            }
        }
        return list;
    }
}
