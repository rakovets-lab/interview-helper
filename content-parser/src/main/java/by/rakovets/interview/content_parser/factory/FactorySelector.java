package by.rakovets.interview.content_parser.factory;

public class FactorySelector {

    public AbstractElementFactory selectFactory(String fileName) {
        if(fileName.contains(".adoc")) {
            return new AciiDocElementFactory();
        } else  {
            return new CsvElementFactory();
        }
    }
}
