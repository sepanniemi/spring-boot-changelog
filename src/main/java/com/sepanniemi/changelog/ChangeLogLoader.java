package com.sepanniemi.changelog;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Component
public class ChangeLogLoader {

    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

    private final String changeLogHtml;

    private final String changeLog;

    private final Map<String, String> gitChangeLogs = new HashMap<>();

    @Autowired
    public ChangeLogLoader(@Value("${changelog.path}") String changelogPath,
                           @Value("${git.logs.path}") String gitLogsPath) throws IOException {
        MutableDataSet options = new MutableDataSet();
        Parser parser = new Parser.Builder(options).build();
        changeLog = IOUtils.toString(
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(changelogPath)),
                StandardCharsets.UTF_8
        );

        HtmlRenderer htmlRenderer = new HtmlRenderer.Builder(options).build();
        Node document = parser.parse(changeLog);
        changeLogHtml = htmlRenderer.render(document);

        //todo: make file names configurable, validate path
        gitChangeLogs.put("html", loadResourceContents(gitLogsPath + "git-changelog.html"));
        gitChangeLogs.put("json", loadResourceContents(gitLogsPath + "git-changelog.json"));
        gitChangeLogs.put("html-table", loadResourceContents(gitLogsPath + "git-changelog-tableonly.html"));
        gitChangeLogs.put("md", loadResourceContents(gitLogsPath + "git-changelog.md"));

    }

    public String getGitChangelog(String type) {
        return gitChangeLogs.get(type);
    }

    private static String loadResourceContents(String resourcePath) throws IOException {
        return IOUtils.toString(
                resourceLoader.getResource(resourcePath).getInputStream(),
                StandardCharsets.UTF_8
        );
    }
}
