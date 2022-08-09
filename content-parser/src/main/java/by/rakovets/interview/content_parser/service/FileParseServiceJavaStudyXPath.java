package by.rakovets.interview.content_parser.service;

import by.rakovets.interview.content_parser.exception.ContentParserException;
import by.rakovets.interview.content_parser.factory.elements.common.AbstractElement;
import by.rakovets.interview.content_parser.factory.elements.common.Element;
import by.rakovets.interview.content_parser.model.FileContent;
import by.rakovets.interview.content_parser.model.Tag;
import by.rakovets.interview.content_parser.factory.AbstractElementFactory;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.util.*;

public class FileParseServiceJavaStudyXPath implements FileParseService {
    private final AbstractElementFactory elementFactory;

    public FileParseServiceJavaStudyXPath(AbstractElementFactory elementFactory) {
        this.elementFactory = elementFactory;
    }

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
            NodeList questions = (NodeList) pathExpressionQuestions.evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < questions.getLength(); i++) {
                List<Element> answerElements = new ArrayList<>();
                final String P = Tag.P.getTag();
                final String H4 = Tag.H4.getTag();
                AbstractElement question = elementFactory.createQuestion(questions.item(i).getTextContent());
                Node node = questions.item(i).getNextSibling();
                boolean isRun = true;
                while (isRun) {
                    if (node != null) {
                        if (node.getNodeName().equals(P)) {
                            if (node.hasChildNodes() && node.getFirstChild().getNodeName().equals(Tag.A.getTag())) {
                                Node imageNode = node.getFirstChild();
                                answerElements.add(elementFactory.createImage(parseImage(imageNode)));
                            } else {
                                answerElements.add(elementFactory.createText(node.getTextContent()));
                            }
                            node = node.getNextSibling();
                        } else if (node.getNodeName().equals(Tag.DIV.getTag())) {
                            Node codeNode = node;
                            answerElements.add(elementFactory.createCode(parseCodeContent(codeNode)));
                            node = node.getNextSibling();
                        } else if (node.getNodeName().equals(Tag.UL.getTag())) {
                            Node listNode = node;
                            answerElements.add(elementFactory.createList(parseOneLevelList(listNode)));
                            node = node.getNextSibling();
                        } else if (node.getNodeName().equals(Tag.TABLE.getTag())) {
                            Node tableNode = node;
                            TableComponents tc = parseTable(tableNode);
                            answerElements.add(elementFactory.createTable(tc.table, tc.caption));
                            node = node.getNextSibling();
                        } else if (node.getNodeName().equals(H4)) {
                            questionsAndAnswers.put(question, answerElements);
                            isRun = false;
                        } else {
                            node = node.getNextSibling();
                        }
                    } else {
                        questionsAndAnswers.put(question, answerElements);
                        break;
                    }
                }
            }
        } catch (XPathExpressionException |
                ParserConfigurationException e) {
            throw new ContentParserException("Wrong XPath, try again.", e);
        }
        return new FileContent(questionsAndAnswers, titleElement);
    }

    private TableComponents parseTable(Node tableNode) {
        NodeList childNodes = tableNode.getChildNodes();
        List<List<String>> table = new ArrayList<>();
        String caption = "";
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeName().equals(Tag.CAPTION.getTag())) {
                caption = childNodes.item(i).getTextContent();
                continue;
            }
            NodeList trNodes = childNode.getChildNodes();
            for (int j = 0; j < trNodes.getLength(); j++) {
                Node trNode = trNodes.item(j);
                NodeList tdNodes = trNode.getChildNodes();
                List<String> tr = new ArrayList<>();
                for (int x = 0; x < tdNodes.getLength(); x++) {
                    Node tdNode = tdNodes.item(x);
                    if (!tdNode.getTextContent().equals("")) {
                        if (!tdNode.getTextContent().equals("\n")) {
                            tr.add(tdNode.getTextContent());
                        }
                    }
                }
                if (tr.size() != 0) {
                    table.add(tr);
                }
            }
        }
        return new TableComponents(caption, table);
    }

    private String parseImage(Node imageNode) {
        return imageNode.getAttributes().getNamedItem("href").getTextContent();
    }

    private String parseCodeContent(Node codeNode) throws XPathExpressionException {
        NodeList childNodes = codeNode.getChildNodes();
        String content = "";
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getTextContent().equals("\n")) {
                continue;
            }
            NodeList subChildNodes = childNodes.item(i).getChildNodes();
            for (int j = 0; j < subChildNodes.getLength(); j++) {
                if (subChildNodes.item(j).getTextContent().equals("\n")) {
                    continue;
                } else if (subChildNodes.item(j).getNodeName().equals("textarea")) {
                    String tempContent = subChildNodes.item(j).getTextContent();
                    if (!tempContent.equals("\n")) {
                        content = tempContent;
                    }
                    break;
                }
            }
        }
        return content;
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

    static class TableComponents {
        private final String caption;
        private final List<List<String>> table;

        public TableComponents(String caption, List<List<String>> table) {
            this.caption = caption;
            this.table = table;
        }
    }
}


