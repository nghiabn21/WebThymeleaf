package com.webthymeleaf.service;

import com.webthymeleaf.entity.Slides;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISlidesService {
    public List<Slides> GetDataSlide();
}
