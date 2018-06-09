package smartanalytics.diksha.com.smartanalytics.network;

import com.google.gson.annotations.SerializedName;

import smartanalytics.diksha.com.smartanalytics.data.StatusModelData;

/**
 * Created by B0089798 on 11/21/16.
 */
public class Response<T> {

    @SerializedName("code")
    private String code;
    @SerializedName("description")
    private String description;
    @SerializedName("body")
    private T body;
    @SerializedName("headers")
    String headers;

    private StatusModelData status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public StatusModelData getStatus() {
        return status;
    }

    public void setStatus(StatusModelData status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", body=" + body +
                ", status=" + status +
                '}';
    }
}
