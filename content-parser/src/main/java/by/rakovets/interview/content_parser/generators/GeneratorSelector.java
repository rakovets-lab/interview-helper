package by.rakovets.interview.content_parser.generators;

public class GeneratorSelector {

    public DocumentationGenerator selectFactory(String fileName) {
        if(fileName.contains(".adoc")) {
            return new DocumentationGeneratorAscii();
        } else  {
            return new DocumentationGeneratorCsv();
        }
    }
}
