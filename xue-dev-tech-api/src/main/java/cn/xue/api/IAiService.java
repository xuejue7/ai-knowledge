package cn.xue.api;

import org.springframework.ai.chat.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * AI 服务接口（api 层只放接口定义，实现放在 trigger/app 层）
 *
 * @author xue
 */
public interface IAiService {

    /**
     * 普通对话（阻塞式）
     *
     * @param model   模型名称，如 deepseek-r1:1.5b
     * @param message 用户消息
     */
    ChatResponse generate(String model, String message);

    /**
     * 流式对话，返回 Flux 由 WebFlux / SSE 推送给前端
     */
    Flux<ChatResponse> generateStream(String model, String message);

    /**
     * 带知识库（RAG）标签的流式对话
     *
     * @param ragTag 知识库标签
     */
    Flux<ChatResponse> generateStreamRag(String model, String ragTag, String message);

}
