package com.sepanniemi.changelog;

import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Getter
public class ChangeLogLoader {

    private String changeLogHtml;

    @Autowired
    public ChangeLogLoader(@Value("${changelog.path}") Resource changeLog) throws IOException {
        PegDownProcessor pegDownProcessor = new PegDownProcessor();
        changeLogHtml = pegDownProcessor.markdownToHtml(IOUtils.toString(changeLog.getInputStream(), StandardCharsets.UTF_8.name()));
    }
}
