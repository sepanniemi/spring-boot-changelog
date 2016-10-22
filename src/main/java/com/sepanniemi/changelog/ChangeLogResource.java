package com.sepanniemi.changelog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/changelog")
public class ChangeLogResource {

    private static final String DEFAULT_GIT_LOG_TYPE = "html";
    private ChangeLogLoader changeLogLoader;

    @Autowired
    public ChangeLogResource(ChangeLogLoader changeLogLoader) {
        this.changeLogLoader = changeLogLoader;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String get(@RequestParam(value = "raw", required = false ) boolean raw) {
        return raw ? changeLogLoader.getChangeLog() : changeLogLoader.getChangeLogHtml();
    }


    @RequestMapping(value = "/git", method = RequestMethod.GET)
    @ResponseBody
    public String getGit(@RequestParam(value = "type", required = false ) String type ) {
        return changeLogLoader.getGitChangelog(type != null ? type : DEFAULT_GIT_LOG_TYPE );
    }
}
