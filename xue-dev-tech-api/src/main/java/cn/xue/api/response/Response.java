package cn.xue.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回结构
 *
 * @author xue
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 状态码，0000 表示成功 */
    private String code;

    /** 描述信息 */
    private String info;

    /** 业务数据 */
    private T data;

}
