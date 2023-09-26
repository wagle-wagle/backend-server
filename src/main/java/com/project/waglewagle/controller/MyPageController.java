package com.project.waglewagle.controller;


import com.project.waglewagle.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

}
