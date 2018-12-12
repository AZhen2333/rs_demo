package qzlife.net.zhrs.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @program: zhrs
 * @description: 结果类
 * @author: Czz
 * @create: 2018-12-09 11:21
 **/
public class JSONResultPojo {

    /**
     * 定义一个jsckson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private Object data;

    private String ok;

    public static JSONResultPojo build(Integer status, String msg, Object data) {
        return new JSONResultPojo(status, msg, data);
    }

    public static JSONResultPojo ok(Object data) {
        return new JSONResultPojo(200, "操作成功", data);
    }

    public static JSONResultPojo ok() {
        return new JSONResultPojo(200, "操作成功");
    }

    public static JSONResultPojo errorMsg(String msg) {
        return new JSONResultPojo(500, msg, null);
    }

    public static JSONResultPojo errorMap(Object data) {
        return new JSONResultPojo(501, "error", data);
    }

    public static JSONResultPojo errorTokenMsg(String msg) {
        return new JSONResultPojo(502, msg, null);
    }

    public static JSONResultPojo errorException(String msg) {
        return new JSONResultPojo(555, msg, null);
    }

    public JSONResultPojo() {

    }

    public JSONResultPojo(Integer status, String msg, Object data) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public JSONResultPojo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public JSONResultPojo(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为LeeJSONResult对象需要转换的对象是一个类
     *
     * @param jsonData
     * @param clazz
     * @return
     */
    public static JSONResultPojo formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, JSONResultPojo.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = MAPPER.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = MAPPER.readValue(data.asText(), clazz);
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转换
     *
     * @param json
     * @return
     */
    public static JSONResultPojo format(String json) {
        try {
            return MAPPER.readValue(json, JSONResultPojo.class);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * json转为List对象
     *
     * @param jsonData
     * @param clazz
     * @return
     */
    public static JSONResultPojo formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
        }
        return null;
    }

}
