package wang.diff.user.server.controller.common;

import diff.wang.user.server.controller.ObjectStoreApi;
import diff.wang.user.server.controller.model.GetBatchUrlRequest;
import diff.wang.user.server.controller.model.ObjectStoreBatchDTO;
import io.minio.MinioClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
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

    @Resource
    private HttpServletResponse httpServletResponse;

    @Override
    public ResponseEntity<ObjectStoreBatchDTO> getBatchUrl(GetBatchUrlRequest getBatchUrlRequest) {
        List<String> uploadObjectUrls = minioUtils.getUploadObjectUrls(bucket, getBatchUrlRequest.getFilenames());
        ObjectStoreBatchDTO ot =  new ObjectStoreBatchDTO();
        ot.setUrls(uploadObjectUrls);
        return ResponseEntity.ok(ot);
    }

    @Override
    public ResponseEntity<Void> getDownload(String filename) {
        minioUtils.download(bucket,filename, httpServletResponse);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ObjectStoreBatchDTO> uploadMultipart(List<MultipartFile> files) {
        
        MultipartFile[] fileArray = new MultipartFile[files.size()];
        MultipartFile[] array = files.toArray(fileArray);
        // MultipartFile[] filesArray = null;
        // files.stream().forEach(x->filesArray.);;
        List<String> uploadFileBatch = minioUtils.uploadFileBatch(bucket, array);
        ObjectStoreBatchDTO objectStoreMultipartDTO = new ObjectStoreBatchDTO();
        objectStoreMultipartDTO.setUrls(uploadFileBatch);
        return ResponseEntity.ok(objectStoreMultipartDTO);
    }
}
