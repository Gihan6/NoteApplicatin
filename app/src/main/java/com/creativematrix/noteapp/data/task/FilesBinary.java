package com.creativematrix.noteapp.data.task;

public class FilesBinary {
    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileExt() {
        return FileExt;
    }

    public void setFileExt(String fileExt) {
        FileExt = fileExt;
    }

    public String getFileContent() {
        return FileContent;
    }

    public void setFileContent(String fileContent) {
        FileContent = fileContent;
    }

    private String FileName,FileExt,FileContent;

    public FilesBinary(String fileName, String fileExt, String fileContent) {
        FileName = fileName;
        FileExt = fileExt;
        FileContent = fileContent;
    }
}
