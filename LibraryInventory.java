/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ice2;

/**
 *
 * @author luke
 */


import java.util.Arrays;

import java.util.Random;

/*
 * Library Inventory Manager - ICE Task 2
 *
 * Scenario:
 * A Library & Multimedia Centre needs a small console program
 * to store and inspect inventory across sectors and branches. This program
 * shows the algorithmic building blocks needed:
 *  - A clear summary (this block comment)
 *  - Use of multi-dimensional arrays (2D and 3D)
 *  - Initialization of arrays
 *  - Sorting arrays (per-row sorting and full flatten/sort)
 *  - Displaying arrays
 *  - Each operation is implemented in its own method and arrays are passed
 *    as parameters (good programming practice)
 *
 */

public class LibraryInventory {

    public static void main(String[] args) {
        // Configurable sizes
        final int BRANCHES = 2;            // for 3D example
        final int SECTIONS = 3;            // rows in 2D
        final int BOOKS_PER_SECTION = 5;   // columns in 2D

        System.out.println("=== PROG6112-ICE2: Library Inventory Demo ===");

        // ---------- 2D integer array: counts per section ----------
        int[][] counts = create2DIntArray(SECTIONS, BOOKS_PER_SECTION);
        init2DIntArrayRandom(counts, 0, 50);
        System.out.println("\n-- Original counts (per section) --");
        display2DIntArray(counts);

        sortRowsAscending(counts);
        System.out.println("\n-- After sorting each section (row) ascending --");
        display2DIntArray(counts);

        int[] flattenedSorted = flattenAndSort(counts);
        System.out.println("\n-- Flattened and sorted counts (all values) --");
        display1DIntArray(flattenedSorted);

        // ---------- 3D String array: titles across branches/sections/books ----------
        String[][][] titles = create3DStringArray(BRANCHES, SECTIONS, BOOKS_PER_SECTION);
        init3DStringArrayExample(titles);
        System.out.println("\n-- Titles before sorting (per section) for each branch --");
        display3DStringArray(titles);

        sortTitlesPerSection(titles);
        System.out.println("\n-- Titles after sorting (per section) for each branch --");
        display3DStringArray(titles);

        System.out.println("\n=== Demo finished ===");
    }

    // ----------------- 2D int array helpers -----------------
    public static int[][] create2DIntArray(int rows, int cols) {
        return new int[rows][cols];
    }

    public static void init2DIntArrayRandom(int[][] arr, int min, int max) {
        if (arr == null) {
            return;
        }
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = r.nextInt(max - min + 1) + min;
            }
        }
    }

    public static void display2DIntArray(int[][] arr) {
        if (arr == null) {
            System.out.println("[null]");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("Section %d: ", i + 1);
            display1DIntArray(arr[i]);
        }
    }

    public static void display1DIntArray(int[] arr) {
        if (arr == null) {
            System.out.println("[null]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void sortRowsAscending(int[][] arr) {
        if (arr == null) {
            return;
        }
        for (int[] row : arr) {
            Arrays.sort(row);
        }
    }

    public static int[] flattenAndSort(int[][] arr) {
        if (arr == null) {
            return new int[0];
        }
        int total = 0;
        for (int[] row : arr) {
            total += row.length;
        }
        int[] flat = new int[total];
        int pos = 0;
        for (int[] row : arr) {
            for (int val : row) {
                flat[pos++] = val;
            }
        }
        Arrays.sort(flat);
        return flat;
    }

    // ----------------- 3D String array helpers -----------------
    public static String[][][] create3DStringArray(int branches, int sections, int books) {
        return new String[branches][sections][books];
    }

    public static void init3DStringArrayExample(String[][][] arr) {
        if (arr == null) {
            return;
        }
        for (int b = 0; b < arr.length; b++) {
            for (int s = 0; s < arr[b].length; s++) {
                for (int k = 0; k < arr[b][s].length; k++) {
                    arr[b][s][k] = String.format("B%d-S%d-Book%02d", b + 1, s + 1, k + 1);
                }
            }
        }
    }

    public static void display3DStringArray(String[][][] arr) {
        if (arr == null) {
            System.out.println("[null]");
            return;
        }
        for (int b = 0; b < arr.length; b++) {
            System.out.println("Branch " + (b + 1) + ":");
            for (int s = 0; s < arr[b].length; s++) {
                System.out.print("  Section " + (s + 1) + ": ");
                System.out.print(Arrays.toString(arr[b][s]));
                System.out.println();
            }
        }
    }

    public static void sortTitlesPerSection(String[][][] arr) {
        if (arr == null) {
            return;
        }
        for (int b = 0; b < arr.length; b++) {
            for (int s = 0; s < arr[b].length; s++) {
                Arrays.sort(arr[b][s]);
            }
        }
    }
}
