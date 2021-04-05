package com.app.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
@Setter
@Getter
public class PictureService {

    @Value("${anon.pic}")
    private String anonymousPicture;

    @Value("${pictures.path}")
    private String picturesPath;

    public String saveFileAndGetURL(MultipartFile file) throws IOException {
        File newFile = File.createTempFile(file.getName(), ".jpeg", getPictureFolderDirectory());

        newFile.createNewFile();
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(newFile)) {
            IOUtils.copy(in, out);
        }
        return newFile.getPath();
    }

    private File getPictureFolderDirectory() throws IOException {
        return new DefaultResourceLoader().getResource(picturesPath).getFile();
    }

}
