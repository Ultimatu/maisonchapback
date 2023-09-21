package com.tonde.maisonchapback.services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RessourceLoaderTest {

    @Mock
    private UrlResource urlResource;

    @InjectMocks
    private RessourceLoader ressourceLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SneakyThrows
    @Test
    void testLoadFileAsResource(){
        String fileName = "1695212730715.jpg";
        String uploadPath = "src/main/resources/static/uploads";
        Path filePath = Paths.get(uploadPath).resolve(fileName).normalize();
        when(urlResource.exists()).thenReturn(true);
        when(urlResource.getFilename()).thenReturn(fileName);
        when(urlResource.getFile()).thenReturn(filePath.toFile());
        when(urlResource.contentLength()).thenReturn(1024L);
        when(urlResource.lastModified()).thenReturn(123456789L);
        when(ressourceLoader.loadFileAsResource(fileName)).thenReturn(urlResource);

        Resource resource = ressourceLoader.loadFileAsResource(fileName);

        Assertions.assertNotNull(resource);
        Assertions.assertEquals(fileName, resource.getFilename());
        Assertions.assertEquals(1024L, resource.contentLength());
        Assertions.assertEquals(123456789L, resource.lastModified());
    }
}