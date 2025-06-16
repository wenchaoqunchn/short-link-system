package com.wenchaoqun.shortlink;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        printWelcomeMessage();
        printLoopIterations();
    }

    private static void printWelcomeMessage() {
        System.out.print("Hello and welcome!");
    }

    private static void printLoopIterations() {
        for (int counter = 1; counter <= 5; counter++) {
            System.out.println("counter = " + counter);
        }
    }
}