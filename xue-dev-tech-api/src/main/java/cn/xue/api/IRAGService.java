package cn.xue.api;

import cn.xue.api.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * RAG 知识库服务接口
 *
 * @author xue
 */
public interface IRAGService {

    /**
     * 查询知识库标签列表
     */
    Response<List<String>> queryRagTagList();

    /**
     * 上传文件到知识库
     *
     * @param ragTag 知识库标签
     * @param files  上传的文件
     */
    Response<String> uploadFile(String ragTag, List<MultipartFile> files);

    /**
     * 解析 Git 仓库并写入知识库
     */
    Response<String> analyzeGitRepository(String repoUrl, String userName, String token) throws Exception;

}
