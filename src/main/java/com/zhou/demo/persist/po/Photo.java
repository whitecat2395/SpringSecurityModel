package com.zhou.demo.persist.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

import javax.xml.xpath.XPath;

/**
 * @ClassName Photo
 * @Author
 * @Date 2023/3/29 16:00
 * @Version
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    private String id;
    private String path;
}
