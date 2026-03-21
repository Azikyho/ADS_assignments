package Assignment_1;

import java.util.Scanner;

public class Task2 {

    public static int sum(int[] arr, int n) {
        if (n == 0) return 0;
        return arr[n - 1] + sum(arr, n - 1);
    }

    public static double average(int[] arr, int n) {
        return (double) sum(arr, n) / n;
    }

    public static void fill(int[] arr, int n, Scanner sc) {
        if (n == 0) return;
        fill(arr, n - 1, sc);
        arr[n - 1] = sc.nextInt();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        fill(arr, n, sc);
        System.out.println(average(arr, n));
    }
}