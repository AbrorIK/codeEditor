package com.example.codeeditor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class CodeCompiler {
    private final SandboxExecutor sandboxExecutor;

    @Autowired
    public CodeCompiler(SandboxExecutor sandboxExecutor) {
        this.sandboxExecutor = sandboxExecutor;
    }

    // Directory for storing temporary files
    protected static final String TEMP_DIRECTORY = "src/main/java/com/example/codeeditor/temp";

    // Compile Java code
    public String compile(String code) throws IOException, InterruptedException {
        // Create a temporary directory
        File tempDir = new File(TEMP_DIRECTORY);
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

        // Write code to a temporary Java source file
        File javaFile = new File(TEMP_DIRECTORY + "/Main.java");
        try (FileWriter writer = new FileWriter(javaFile)) {
            writer.write(code);
        }

        // Compile the Java code
        Process compileProcess = Runtime.getRuntime().exec("javac Main.java", null, tempDir);
        compileProcess.waitFor();

        // Check if compilation was successful
        if (compileProcess.exitValue() == 0) {
            return "Compilation successful";
        } else {
            javaFile.delete();
            return "Compilation failed!";
        }
    }
}
