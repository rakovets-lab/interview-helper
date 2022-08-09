package by.rakovets.interview.content_parser.dao;

import by.rakovets.interview.content_parser.exception.ContentDaoException;
import by.rakovets.interview.content_parser.model.Content;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ContentDaoFileSystem implements ContentDao {
    @Override
    public void save(Content content) throws ContentDaoException {
        createDirectory(content.getPathForSaving());
        File savingFile = new File(content.getPathForSaving() + "/" + content.getFileName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter((savingFile)))) {
            bw.append(content.getContent());
        } catch (IOException e) {
            throw new ContentDaoException("Internal Problem!", e);
        }
    }

    @Override
    public String save(byte[] image, String pictureFileName, String fileName, String pathForAssets) throws ContentDaoException {
        String packageName = pathForAssets + "/" + fileName.substring(0, fileName.indexOf("."));
        createDirectory(packageName);
        String imagePath = packageName + "/" + pictureFileName;
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imagePath))) {
            bos.write(image);
        } catch (FileNotFoundException e) {
            throw new ContentDaoException("File not found!", e);
        } catch (IOException e) {
            throw new ContentDaoException("Internal problem", e);
        }
        return packageName;
    }

    private void createDirectory(String dirPath) throws ContentDaoException {
        Path savingPath = Paths.get(dirPath);
        if (!Files.exists(savingPath)) {
            try {
                Files.createDirectories(savingPath);
            } catch (IOException e) {
                throw new ContentDaoException("Internal problem!", e);
            }
        }
    }
}
