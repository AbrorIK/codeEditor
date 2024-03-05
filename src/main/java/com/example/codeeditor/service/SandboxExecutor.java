package com.example.codeeditor.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.codeeditor.service.CodeCompiler.TEMP_DIRECTORY;

@Service
public class SandboxExecutor {

    public String execute(String className) throws IOException, InterruptedException {
        // Set up the command to run the compiled Java code
        String command = "java -classpath " + TEMP_DIRECTORY + " " + className;

        // Execute the compiled Java code
        Process executeProcess = Runtime.getRuntime().exec(command);

        // Read and capture the output
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(executeProcess.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        // Check if the process exited normally
        int exitCode = executeProcess.waitFor();
        if (exitCode != 0) {
            output.append("Execution failed with exit code: ").append(exitCode);
        }

        return output.toString();
    }
}
