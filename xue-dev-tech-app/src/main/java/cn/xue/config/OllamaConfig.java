package cn.xue.config;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ollama / Spring AI 相关的手工装配。
 *
 * 背景说明（Spring AI 0.8.1）：
 * 1. EmbeddingClient 由 starter 自动配置提供。项目同时引入了
 *    spring-ai-ollama-spring-boot-starter 和 spring-ai-openai-spring-boot-starter，
 *    两者都会注册 EmbeddingClient（ollamaEmbeddingClient / openAiEmbeddingClient），
 *    而 PgVectorStoreAutoConfiguration#vectorStore 需要一个唯一的 EmbeddingClient，
 *    于是启动直接报 “required a single bean, but 2 were found”。
 *    解决办法是在 application-{dev,prod}.yml 里
 *    spring.ai.openai.embedding.enabled=false，只保留 Ollama 的 nomic-embed-text。
 * 2. TokenTextSplitter、SimpleVectorStore 没有对应的自动配置类，必须手工声明，
 *    否则 RAGController 里的 @Resource 注入会报 NoSuchBeanDefinitionException。
 */
@Configuration
public class OllamaConfig {

    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingClient embeddingClient) {
        return new SimpleVectorStore(embeddingClient);
    }

}
