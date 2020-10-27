package com.crm.cn.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.AxiosStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

@Component
@PropertySource(value = {"classpath:upload.properties"})
public class UploadService {

    @Value("${endpoint}")
    private String endpoint;
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    @Value("${bucketName}")
    private String bucketName;
    @Value("${baseUrl}")
    private String baseUrl;
    @Value("${ext}")
    private String ext;
    @Value("${size}")
    private int size;

    public AxiosResult uploadService(InputStream in, String fileName, Long size) throws IOException {

        String[] split = ext.split(",");
        byte[] buffer = new byte[in.available()];
        in.read(buffer);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        System.out.println(buffer.length);
        BufferedImage read = ImageIO.read(inputStream);

        if(Objects.isNull(read)){
            return AxiosResult.error(AxiosStatus.NOT_IMAGE);
        }

        if(!Arrays.asList(split).contains(StringUtils.getFilenameExtension(fileName))){
            return AxiosResult.error(AxiosStatus.EXT_ERROR);
        }

        if (size / 1024 / 1024 > this.size) {
            return AxiosResult.error(AxiosStatus.FILE_TOOLONG);
        }


        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(buffer);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传网络流。
        ossClient.putObject(bucketName, fileName, inputStream1);
        // 关闭OSSClient。
        ossClient.shutdown();
        return AxiosResult.success(baseUrl+fileName);
    }

}
