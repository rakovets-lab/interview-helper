package by.rakovets.interview.content_parser.controller.front_controller;

public interface FrontController {

    void start();

    default void printError(String message) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(message);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    default void printDialog(String phrase) {
        System.out.println("************************");
        System.out.println(phrase);
        System.out.println("************************");
    }
}
