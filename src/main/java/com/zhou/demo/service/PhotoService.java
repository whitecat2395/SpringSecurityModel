package com.zhou.demo.service;

import com.zhou.demo.persist.mapper.PhotoMapper;
import com.zhou.demo.persist.po.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PhotoService
 * @Author
 * @Date 2023/3/29 16:04
 * @Version
 * @Description
 */

@Service
public class PhotoService {

    @Autowired
    private PhotoMapper mapper;
    public int addPhoto(Photo photo) {
        return mapper.addPhoto(photo);
    }
}
