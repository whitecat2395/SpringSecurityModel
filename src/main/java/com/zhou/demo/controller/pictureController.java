package com.zhou.demo.controller;

import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Photo;
import com.zhou.demo.service.PhotoService;
import com.zhou.demo.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName pictureController
 * @Author zz
 * @Date 2023/3/29 15:49
 * @Version 1.0
 * @Description 图像上传与展示模块
 */
@RestController
public class pictureController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PhotoService photoService;


    @PostMapping("/image/uploadFile")
    public CommonResult<Map> addArticle(@RequestParam(value = "file") MultipartFile mfile){
        if(mfile == null) {
            System.out.println("没有上传缩略图!");
            return new CommonResult<>(200,"没有上传缩略图!");
        }
        //获取后缀
        String suffixName = ImageUtil.getImagePath(mfile);
        //拼接新的文件名
        String newFileName = ImageUtil.getNewFileName(suffixName);
        //图片地址
        String newImagePath = ImageUtil.getNewImagePath(newFileName);
        //保存图片
        File file = new File(newImagePath);
        System.out.println(newFileName);
        boolean state = ImageUtil.saveImage(mfile, file);
        if(state) {
            //图片保存成功
            //设置图片到对象
            Photo photo =new Photo();
            photo.setPhotoName(newFileName);
            photo.setPath(newImagePath);
            //保存对象
            int row = photoService.addPhoto(photo);
            System.out.println("图片插入成功:"+row);
            if(row > 0) {
                Map<String,String> map =new HashMap<String,String>();
                map.put("pictureName",newFileName);
                return new CommonResult<>(200,"图片上传成功",map);
            }else {
                //TODO 数据库插入失败，删除本地图片
                return new CommonResult<>(200,"图片上传失败1");
            }
        }
        return new CommonResult<>(200,"图片上传失败2");
    }


    @RequestMapping(path = "/images/{filename}", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
                MediaType.IMAGE_PNG_VALUE })
        public BufferedImage getImage(@PathVariable("filename") String filename) throws IOException, IOException {
            // 首先组合文件对象http://localhost:9090/image/phone3.jpg
            String FilePath = ImageUtil.getNewImagePath(filename);
            byte[] bytes = null;
            bytes = (byte[]) redisTemplate.opsForValue().get(filename);
            if(!ObjectUtils.isEmpty(bytes)){
                System.out.println("从redis缓存读取"+filename+"图片成功");
                BufferedImage image = ByteArrayTobufferedImage(bytes);
                return image;
            }
            System.out.println("FilePath==="+FilePath);
            File file = new File("D:/image/",filename);
            // 判断文件是否存在
            if (file.exists()) {
                // 读取文件流，用文件流创建图片，这里也可以通过ImageIO直接读取文件
                FileInputStream in = new FileInputStream(file);
                BufferedImage image = ImageIO.read(in);
                in.close();
                bytes = bufferedImageToByteArray(image);
                redisTemplate.opsForValue().set(filename,bytes,12,TimeUnit.HOURS);
                return image;
            } else {
                // 如果不存在返回空
                return null;
            }
    }

    @RequestMapping("/addimage")
    public Map<String,Object> addimage (MultipartFile[] files){
        Map<String,Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for(int i = 0;i<files.length;i++) {
            MultipartFile mfile = files[i];
            //获取文件后缀
            String suffixName = ImageUtil.getImagePath(mfile);
            //生成新文件名称
            String newFileName = ImageUtil.getNewFileName(suffixName);
            //保存文件
            File file = new File(ImageUtil.getNewImagePath(newFileName));
            boolean state = ImageUtil.saveImage(mfile, file);
            if(state) {
                list.add(ImageUtil.getNewImagePath(newFileName));
            }
        }
        map.put("imgList", list);
        return map;

    }

    public byte[] bufferedImageToByteArray(BufferedImage image) throws IOException{
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        return os.toByteArray();
    }
    public BufferedImage ByteArrayTobufferedImage(byte[] bytes) throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        BufferedImage read = ImageIO.read(byteArrayInputStream);
        return read;
    }
}
