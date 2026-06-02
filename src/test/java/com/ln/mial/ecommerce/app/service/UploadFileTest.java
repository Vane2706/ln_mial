package com.ln.mial.ecommerce.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UploadFileTest {

    @Test
    void upload_WhenFileEmpty_ReturnDefault()
            throws Exception {

        UploadFile uploadFile =
                new UploadFile();

        MockMultipartFile file =
                new MockMultipartFile(
                        "file",
                        new byte[0]
                );

        String result =
                uploadFile.upload(file);

        assertEquals(
                "default.png",
                result
        );
    }

    @Test
    void delete_NotThrowException() {

        UploadFile uploadFile =
                new UploadFile();

        assertDoesNotThrow(() ->
                uploadFile.delete("fake.png"));
    }
}