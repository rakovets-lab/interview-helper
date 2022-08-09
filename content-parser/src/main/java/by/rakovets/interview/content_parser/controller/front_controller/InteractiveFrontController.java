package by.rakovets.interview.content_parser.controller.front_controller;

import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.dto.SiteContentDto;
import by.rakovets.interview.content_parser.service.ContentService;

import java.util.Scanner;

public class InteractiveFrontController implements FrontController {
    private final Scanner scanner;
    private final ContentService contentService;
    private String startMessage;

    public InteractiveFrontController(ContentService contentService) {
        this.contentService = contentService;
        scanner = new Scanner(System.in);
    }

    public InteractiveFrontController(ContentService contentService, String startMessage) {
        this.contentService = contentService;
        scanner = new Scanner(System.in);
        this.startMessage = startMessage;
    }

    public void start() {
        if (startMessage != null) {
            printDialog(startMessage);
            printDialog("Do you like to continue in manual mode? (y/n):");
        }
        boolean isRun;
        String pathForSaving = "";
        String continueRun = scanner.nextLine();
        isRun = "y".equals(continueRun.trim());
        if (isRun) {
            printDialog("Application running in manual mode. Follow the instructions:");
        }
        while (isRun) {
            printDialog("Input URI to hijack");
            String uri = scanner.nextLine();
            printDialog("Input XPath to find questions");
            String xPathForQuestions = scanner.nextLine();
            printDialog("Input directory to save files");
            pathForSaving = scanner.nextLine();
            printDialog("Input fileName for saving");
            String fileName = scanner.nextLine();
            printDialog("Input path for saving pictures if present");
            String pathForAssets = scanner.nextLine();
            try {
                contentService.createContent(new SiteContentDto(uri, xPathForQuestions), fileName, pathForAssets, pathForSaving);
                printDialog("File was successfully saved");
            } catch (ContentParserException e) {
                printError(e.getMessage());
            }
            printDialog("Would you like to continue? (y/n):");
            continueRun = scanner.nextLine();
            isRun = "y".equals(continueRun.trim());
        }
        printDialog("Good buy");
    }
}
