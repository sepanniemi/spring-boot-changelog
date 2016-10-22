package com.sepanniemi.changelog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/changelog")
public class ChangeLogResource {

    private ChangeLogLoader changeLogLoader;

    @Autowired
    public ChangeLogResource(ChangeLogLoader changeLogLoader) {
        this.changeLogLoader = changeLogLoader;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String get(){
        return changeLogLoader.getChangeLogHtml();
    }
}
