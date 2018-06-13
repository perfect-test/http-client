package com.perfecttest.http;

import org.apache.http.entity.ContentType;

import java.io.File;

/**
 * User: Sidelnikov Mikhail
 * Date: 28.06.16
 * Class for binary multipart
 */
public class BinaryMultiPart {
    private String name;
    private File file;
    private ContentType contentType = ContentType.APPLICATION_OCTET_STREAM;
    private String fileName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BinaryMultiPart withName(final String name) {
        this.name = name;
        return this;
    }

    public BinaryMultiPart withFile(final File file) {
        this.file = file;
        return this;
    }

    public BinaryMultiPart withContentType(final ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public BinaryMultiPart withFileName(final String fileName) {
        this.fileName = fileName;
        return this;
    }


}
