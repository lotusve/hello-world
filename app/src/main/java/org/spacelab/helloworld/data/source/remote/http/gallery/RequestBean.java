package org.spacelab.helloworld.data.source.remote.http.gallery;

public class RequestBean {

    private String api_key;

    private String api_secret;

    private String image_base64;


    /**
     * 是否检测并返回根据人脸特征判断出的年龄、性别、情绪等属性
     */
    private String return_attributes;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }

    public String getImage_base64() {
        return image_base64;
    }

    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }

    public String getReturn_attributes() {
        return return_attributes;
    }

    public void setReturn_attributes(String return_attributes) {
        this.return_attributes = return_attributes;
    }

    @Override
    public String toString() {
        return "RequestBean{" +
                "api_key='" + api_key + '\'' +
                ", api_secret='" + api_secret + '\'' +
                ", image_base64='" + image_base64 + '\'' +
                ", return_attributes='" + return_attributes + '\'' +
                '}';
    }
}
