package wang.diff.user.server.util;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@Component
public class MinioUtils {


    @Resource
    private MinioClient minioClient;

    /**
     * 判断桶存不存在
     * @param bucketName
     * @return
     */
    @SneakyThrows(Exception.class)
    public boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }


    /**
     * 创建桶
     * @param bucketName
     */
    @SneakyThrows(Exception.class)
    public void createBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }


    /**
     * 根据桶名获取桶信息
     * @param bucketName
     * @return
     */
    @SneakyThrows(Exception.class)
    public Optional<Bucket> getBucket(String bucketName) {
        return minioClient.listBuckets().stream().filter(x -> x.name().equals(bucketName)).findFirst();
    }


    /**
     * 获取文件流
     * @param bucketName
     * @param objectName 
     * @return 二进制流
     */
    @SneakyThrows(Exception.class)
    public InputStream getObject(String bucketName, String objectName) {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }


    /**
     * 通过路径上传文件
     * @param bucketName
     * @param objectName
     * @param fileName
     * @return
     */
    @SneakyThrows(Exception.class)
    public ObjectWriteResponse putObject(String bucketName, String objectName, String fileName) {
        createBucket(bucketName);
        ObjectWriteResponse uploadObject = minioClient.uploadObject(
                UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(fileName).build());
        return uploadObject;
    }


    /**
     * 通过流上传文件
     * @param bucketName
     * @param objectName
     * @param inputStream
     * @return
     */
    @SneakyThrows(Exception.class)
    public ObjectWriteResponse putObject(String bucketName, String objectName, InputStream inputStream) {
        createBucket(bucketName);
        return minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName)
                .stream(inputStream, inputStream.available(), -1).build());
    }


    /**
     * 获取外链地址
     * @param bucketName
     * @param objectName
     * @param expires 
     * @return
     */
    @SneakyThrows(Exception.class)
    public String getUploadObjectUrl(String bucketName, String objectName, Integer expires) {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET)
                .bucket(bucketName).object(objectName).expiry(expires).build());
    }



    /**
     * 单文件上传
     * @param bucketName
     * @param multipartFile
     * @return
     */
    // @SneakyThrows(Exception.class)
    public String uploadFileSingle(String bucketName, MultipartFile multipartFile) {
        createBucket(bucketName);
        String originalFilename = multipartFile.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        if (split.length > 1) {
            originalFilename = split[0] + "_" + System.currentTimeMillis() + "." + split[1];
        } else {
            originalFilename += System.currentTimeMillis();
        }
        InputStream in = null;
        try {
          in = multipartFile.getInputStream();
          minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(originalFilename).stream(in, in.available(), -1).contentType(multipartFile.getContentType()).build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return getUploadObjectUrl(bucketName, originalFilename, 7 * 24 * 60 * 60);
    }


    /**
     * 批量文件上传
     * @param bucketName
     * @param multipartFiles
     * @return
     */
    public List<String> uploadFileBatch(String bucketName, MultipartFile[] multipartFiles) {
        List<String> nameList = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            try {
                String uploadFileSingle = uploadFileSingle(bucketName, file);
                nameList.add(uploadFileSingle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nameList;
    }


    /**
     * 文件下载
     * @param bucketName
     * @param fileName
     * @param httpServletResponse
     */
    @SneakyThrows(Exception.class)
    public void download(String bucketName, String fileName, HttpServletResponse httpServletResponse) {
        // 获取对象元数据
        StatObjectResponse statObject = minioClient
                .statObject(StatObjectArgs.builder().bucket(bucketName).object(fileName).build());
        httpServletResponse.setContentType(statObject.contentType());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Disposton",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream is = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        IOUtils.copy(is, httpServletResponse.getOutputStream());
        is.close();
    }

    
}
