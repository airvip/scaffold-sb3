package wang.diff.user.server.controller.common;

import diff.wang.user.server.controller.ObjectStoreApi;
import diff.wang.user.server.controller.model.ObjectStoreMultipartDTO;
import io.minio.MinioClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import wang.diff.user.server.util.MinioUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Tag(name = "object-store")
public class ObjectStoreController implements ObjectStoreApi {

    @Resource
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucket;

    @Resource
    private MinioUtils minioUtils;

    @Override
    public ResponseEntity<ObjectStoreMultipartDTO> uploadMultipart(List<MultipartFile> files) {
        
        MultipartFile[] fileArray = new MultipartFile[files.size()];
        MultipartFile[] array = files.toArray(fileArray);
        // MultipartFile[] filesArray = null;
        // files.stream().forEach(x->filesArray.);;
        List<String> uploadFileBatch = minioUtils.uploadFileBatch("spingboot", array);
        ObjectStoreMultipartDTO objectStoreMultipartDTO = new ObjectStoreMultipartDTO();
        objectStoreMultipartDTO.setUrls(uploadFileBatch);
        return ResponseEntity.ok(objectStoreMultipartDTO);
    }
}
