package com.webthymeleaf.serviceimpl;

import com.webthymeleaf.entity.Slides;
import com.webthymeleaf.repository.SlidesRepo;
import com.webthymeleaf.service.ISlidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlidesSerImpl implements ISlidesService {

    @Autowired
    private SlidesRepo slidesRepo ;

    @Override
    public List<Slides> GetDataSlide() {
        return slidesRepo.findAll();
    }
}
