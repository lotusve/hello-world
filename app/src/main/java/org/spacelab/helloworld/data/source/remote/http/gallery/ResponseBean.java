package org.spacelab.helloworld.data.source.remote.http.gallery;

import java.util.List;

/**
 * {
 * 	"request_id": "1578487921,dcadbd18-edca-4033-bc66-48e8fc1c5829",
 * 	"time_used": 196,
 * 	"faces": [{
 * 		"face_token": "b951d81d19de12a510c476a9c600647e",
 * 		"face_rectangle": {
 * 			"top": 132,
 * 			"left": 183,
 * 			"width": 174,
 * 			"height": 174
 *                },
 * 		"attributes": {
 * 			"gender": {
 * 				"value": "Male"
 *            },
 * 			"age": {
 * 				"value": 50
 *            }
 *        }* 	}],
 * 	"image_id": "TNEy+WtIbEms2rwis9HOgA==",
 * 	"face_num": 1
 * }
 */
public class ResponseBean {

    /**
     * 用于区分每一次请求的唯一的字符串。
     */
    private String request_id;

    /**
     * 被检测出的人脸数组，具体包含内容见下文。
     *
     * 注：如果没有检测出人脸则为空数组
     */
    private List<Face> faces;

    /**
     * 被检测的图片在系统中的标识。
     */
    private String image_id;

    /**
     * 整个请求所花费的时间，单位为毫秒。
     */
    private int time_used;

    /**
     * 当请求失败时才会返回此字符串，具体返回内容见后续错误信息章节。否则此字段不存在。
     */
    private String error_message;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "request_id='" + request_id + '\'' +
                ", faces=" + faces +
                ", image_id='" + image_id + '\'' +
                ", time_used=" + time_used +
                ", error_message='" + error_message + '\'' +
                '}';
    }

    private class Face {

        /**
         * 人脸的标识
         */
        private String face_token;

        /**
         * 人脸属性特征
         */
        private Attributes attributes;

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        @Override
        public String toString() {
            return "Face{" +
                    "face_token='" + face_token + '\'' +
                    ", attributes=" + attributes +
                    '}';
        }
    }

    private class Attributes {

        /**
         * 性别分析结果, 男 Male, 女 Female
         */
        private Gender gender;

        /**
         * 年龄分析结果。返回值为一个非负整数。
         */
        private Age age;

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Age getAge() {
            return age;
        }

        public void setAge(Age age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "gender=" + gender +
                    ", age=" + age +
                    '}';
        }
    }

    private class Gender {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Gender{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

    private class Age {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Age{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

}
