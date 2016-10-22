package com.sepanniemi.changelog;

import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class ChangeLogLoader {

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    private String changeLogHtml;

    private String changeLog;

    private Map<String, String> gitChangeLogs = new HashMap<>();

    @Autowired
    public ChangeLogLoader(@Value("${changelog.path}") Resource changeLogResource, @Value("${git.logs.path}")String gitLogsPath) throws IOException {
        PegDownProcessor pegDownProcessor = new PegDownProcessor();
        changeLog = IOUtils.toString(changeLogResource.getInputStream(), StandardCharsets.UTF_8.name());
        changeLogHtml = pegDownProcessor.markdownToHtml(changeLog);

        //todo: make file names configurable, validate path
        gitChangeLogs.put("html", loadResourceContents(gitLogsPath+ "git-changelog.html"));
        gitChangeLogs.put("json", loadResourceContents(gitLogsPath+ "git-changelog.json"));
        gitChangeLogs.put("html-table",  loadResourceContents(gitLogsPath+ "git-changelog-tableonly.html"));
        gitChangeLogs.put("md", loadResourceContents(gitLogsPath+ "git-changelog.md"));

    }

    public String getGitChangelog(String type){
        return gitChangeLogs.get(type);
    }

    private static String loadResourceContents(String resourcePath ) throws IOException {
        return IOUtils.toString(resourceLoader.getResource(resourcePath).getInputStream());
    }
}
