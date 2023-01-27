package com.sepanniemi.changelog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/changelog")
@RequiredArgsConstructor
public class ChangeLogResource {

    private static final String DEFAULT_GIT_LOG_TYPE = "html";
    private final ChangeLogLoader changeLogLoader;


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String get(@RequestParam(value = "raw", required = false) boolean raw) {
        return raw ? changeLogLoader.getChangeLog() : changeLogLoader.getChangeLogHtml();
    }


    @ResponseBody
    @RequestMapping(value = "/git", method = RequestMethod.GET)
    public String getGit(@RequestParam(value = "type", required = false) String type) {
        return changeLogLoader.getGitChangelog(type != null ? type : DEFAULT_GIT_LOG_TYPE);
    }
}
