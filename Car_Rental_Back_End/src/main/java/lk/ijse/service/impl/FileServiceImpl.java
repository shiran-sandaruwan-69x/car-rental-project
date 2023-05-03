package lk.ijse.service.impl;

import lk.ijse.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {

    @Value("${app.IMG_DIR}")
    String path;
    HttpServletRequest request;
    private File file;
    private Path root = Paths.get("/uploads");

    @Override
    public void init() {

    }

    @Override
    public void save(MultipartFile img) {
      // String name= img.getOriginalFilename();
        file = new File("D:\\internent technology\\String Boots\\upload");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("Not Created.!");
            }
        }
        Path imgPath = Paths.get(file.getAbsolutePath(), img.getOriginalFilename());
        try {
            Files.write(imgPath, img.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + "");
        }
    }

    @Override
    public void load(String filename, String rootPath) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
