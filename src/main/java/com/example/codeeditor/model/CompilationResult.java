package com.example.codeeditor.model;

public class CompilationResult {

    private boolean success;
    private String output;
    private String errorMessage;

    public CompilationResult() {
        // Default constructor
    }

    public CompilationResult(boolean success, String output, String errorMessage) {
        this.success = success;
        this.output = output;
        this.errorMessage = errorMessage;
    }

    // Getters and setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
