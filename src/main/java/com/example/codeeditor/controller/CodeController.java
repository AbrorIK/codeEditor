package com.example.codeeditor.controller;

import com.example.codeeditor.service.CodeCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CodeController {

    private final CodeCompiler codeCompiler;

    @Autowired
    public CodeController(CodeCompiler codeCompiler) {
        this.codeCompiler = codeCompiler;
    }

    @PostMapping("/compile")
    public String compileCode(@RequestBody String code) {
        try {
            return codeCompiler.compile(code);
        } catch (Exception e) {
            return "Error occurred during compilation: " + e.getMessage();
        }
    }
}
