package by.rakovets.interview.content_parser.service;

import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.factory.AbstractElementFactory;
import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.Element;
import by.rakovets.interview.content_parser.model.FileContent;
import by.rakovets.interview.content_parser.model.Tag;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileParseServiceJSEHelperXpath implements FileParseService {

    private final AbstractElementFactory elementFactory;

    public FileParseServiceJSEHelperXpath(AbstractElementFactory elementFactory) {
        this.elementFactory = elementFactory;
    }

    @Override
    public FileContent parse(String content, String xPathExpressionQuestions) throws ContentParserException {
        Map<AbstractElement, List<Element>> questionsAndAnswers = new LinkedHashMap<>();
        AbstractElement titleElement;
        final String xpathExpressionForTitle = ".//h1";
        try {
            TagNode tagNode = new HtmlCleaner().clean(content);
            Document document = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath = factory.newXPath();
            XPathExpression pathExpressionTitle = xPath.compile(xpathExpressionForTitle);
            Node titleNode = (Node) pathExpressionTitle.evaluate(document, XPathConstants.NODE);
            String title = titleNode.getTextContent();
            titleElement = elementFactory.createTitle(title);
            XPathExpression pathExpressionQuestions = xPath.compile(xPathExpressionQuestions);
            Node start = (Node) pathExpressionQuestions.evaluate(document, XPathConstants.NODE);
            NodeList questions = start.getChildNodes();
            AbstractElement question = elementFactory.createQuestion("");
            List<Element> answers = new ArrayList<>();
            for (int i = 0; i < questions.getLength(); i++) {
                Node currentNode = questions.item(i);
                if (currentNode != null) {
                    if (currentNode.getNodeName().equals(Tag.H3.getTag())) {
                        question = elementFactory.createQuestion(currentNode.getTextContent());
                    } else if (currentNode.getNodeName().equals(Tag.DIV.getTag())) {
                        NodeList childNodes = questions.item(i).getChildNodes();
                        for (int j = 0; j < childNodes.getLength(); j++) {
                            Node childNode = childNodes.item(j);
                            if (childNode != null) {
                                if (childNode.getNodeName().equals(Tag.H3.getTag())) {
                                        questionsAndAnswers.put(question, answers);
                                        question = elementFactory.createQuestion(childNode.getTextContent());
                                        answers = new ArrayList<>();
                                } else if (childNode.getNodeName().equals(Tag.SPAN.getTag())) {
                                    answers.add(elementFactory.createText(childNodes.item(j).getTextContent()));
                                } else if (childNode.getNodeName().equals(Tag.UL.getTag())) {
                                    answers.add(elementFactory.createList(parseOneLevelList(childNodes.item(j))));
                                } else if (childNode.getNodeName().equals(Tag.DIV.getTag())) {
                                    NodeList subChildNodes = childNode.getChildNodes();
                                    for (int k = 0; k < subChildNodes.getLength(); k++) {
                                        Node subChildNode = subChildNodes.item(k);
                                        if (subChildNode != null) {
                                            if (subChildNode.getNodeName().equals(Tag.H3.getTag())) {
                                                questionsAndAnswers.put(question, answers);
                                                question = elementFactory.createQuestion(subChildNode.getTextContent());
                                                answers = new ArrayList<>();
                                            } else if (subChildNode.getNodeName().equals(Tag.SPAN.getTag())) {
                                                answers.add(elementFactory.createText(subChildNode.getTextContent()));
                                            } else if (subChildNode.getNodeName().equals(Tag.UL.getTag())) {
                                                answers.add(elementFactory.createList(parseOneLevelList(subChildNode)));
                                            } else if (subChildNode.getNodeName().equals(Tag.A.getTag())) {
                                                answers.add(elementFactory.createImage(parseImage(subChildNode)));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }

        } catch (XPathExpressionException |
                ParserConfigurationException e) {
            throw new ContentParserException("Wrong XPath, try again.", e);
        }
        return new FileContent(questionsAndAnswers, titleElement);
    }

    private List<String> parseOneLevelList(Node oneLevelListNode) {
        List<String> list = new ArrayList<>();
        NodeList liNodes = oneLevelListNode.getChildNodes();
        for (int i = 0; i < liNodes.getLength(); i++) {
            if (liNodes.item(i).getTextContent().equals("\n")) {
                continue;
            }
            list.add(liNodes.item(i).getTextContent());
        }
        return list;
    }

    private String parseImage(Node imageNode) {
        return imageNode.getAttributes().getNamedItem("href").getTextContent();
    }
}
