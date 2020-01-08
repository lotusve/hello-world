package org.spacelab.helloworld.data.source.remote.http.gallery;

import java.io.File;

public class RequestBean {

    private String api_key;

    private String api_secret;

    private File image_file;

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

    public File getImage_file() {
        return image_file;
    }

    public void setImage_file(File image_file) {
        this.image_file = image_file;
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
                ", image_file=" + image_file +
                ", return_attributes='" + return_attributes + '\'' +
                '}';
    }
}
