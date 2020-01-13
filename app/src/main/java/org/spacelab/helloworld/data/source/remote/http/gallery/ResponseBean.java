package org.spacelab.helloworld.data.source.remote.http.gallery;

import java.util.List;

/**
 * {
 * "request_id": "1578487921,dcadbd18-edca-4033-bc66-48e8fc1c5829",
 * "time_used": 196,
 * "faces": [{
 * "face_token": "b951d81d19de12a510c476a9c600647e",
 * "face_rectangle": {
 * "top": 132,
 * "left": 183,
 * "width": 174,
 * "height": 174
 * },
 * "attributes": {
 * "gender": {
 * "value": "Male"
 * },
 * "age": {
 * "value": 50
 * }
 * }* 	}],
 * "image_id": "TNEy+WtIbEms2rwis9HOgA==",
 * "face_num": 1
 * }
 */
public class ResponseBean {

    /**
     * 用于区分每一次请求的唯一的字符串。
     */
    private String request_id;

    /**
     * 被检测出的人脸数组，具体包含内容见下文。
     * <p>
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

    public class Face {

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

    public class Attributes {

        /**
         * 性别分析结果, 男 Male, 女 Female
         */
        private Gender gender;

        /**
         * 年龄分析结果。返回值为一个非负整数。
         */
        private Age age;

        /**
         * 笑容分析结果。返回值包含以下属性：
         * <p>
         * value：值为一个 [0,100] 的浮点数，小数点后3位有效数字。数值越大表示笑程度高。
         * threshold：代表笑容的阈值，超过该阈值认为有笑容。
         */
        private Smile smile;

        /**
         * 情绪识别结果。返回值包含以下字段。每个字段的值都是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。每个字段的返回值越大，则该字段代表的状态的置信度越高。字段值的总和等于 100。
         * <p>
         * anger：愤怒
         * disgust：厌恶
         * fear：恐惧
         * happiness：高兴
         * neutral：平静
         * sadness：伤心
         * surprise：惊讶
         */
        private Emotion emotion;

        /**
         * 颜值识别结果。返回值包含以下两个字段。每个字段的值是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。
         * <p>
         * male_score：男性认为的此人脸颜值分数。值越大，颜值越高。
         * female_score：女性认为的此人脸颜值分数。值越大，颜值越高。
         */
        private Beauty beauty;

        /**
         * 面部特征识别结果，包括以下字段。每个字段的值都是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。每个字段的返回值越大，则该字段代表的状态的置信度越高。
         * <p>
         * health：健康
         * stain：色斑
         * acne：青春痘
         * dark_circle：黑眼圈
         */
        private Skinstatus skinstatus;

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

        public Smile getSmile() {
            return smile;
        }

        public void setSmile(Smile smile) {
            this.smile = smile;
        }

        public Emotion getEmotion() {
            return emotion;
        }

        public void setEmotion(Emotion emotion) {
            this.emotion = emotion;
        }

        public Beauty getBeauty() {
            return beauty;
        }

        public void setBeauty(Beauty beauty) {
            this.beauty = beauty;
        }

        public Skinstatus getSkinstatus() {
            return skinstatus;
        }

        public void setSkinstatus(Skinstatus skinstatus) {
            this.skinstatus = skinstatus;
        }

        @Override
        public String toString() {
            return "Attributes{" +
                    "gender=" + gender +
                    ", age=" + age +
                    ", smile=" + smile +
                    ", emotion=" + emotion +
                    ", beauty=" + beauty +
                    ", skinstatus=" + skinstatus +
                    '}';
        }
    }

    /**
     * 性别分析结果, 男 Male, 女 Female
     */
    public class Gender {
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

    public class Age {
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

    /**
     * 笑容分析结果。返回值包含以下属性：
     * <p>
     * value：值为一个 [0,100] 的浮点数，小数点后3位有效数字。数值越大表示笑程度高。
     * threshold：代表笑容的阈值，超过该阈值认为有笑容。
     */
    public class Smile {

        private float value;

        private int threshold;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public int getThreshold() {
            return threshold;
        }

        public void setThreshold(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public String toString() {
            return "Smile{" +
                    "value=" + value +
                    ", threshold=" + threshold +
                    '}';
        }
    }

    /**
     * 情绪识别结果。返回值包含以下字段。每个字段的值都是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。每个字段的返回值越大，则该字段代表的状态的置信度越高。字段值的总和等于 100。
     * <p>
     * anger：愤怒
     * disgust：厌恶
     * fear：恐惧
     * happiness：高兴
     * neutral：平静
     * sadness：伤心
     * surprise：惊讶
     */
    public class Emotion {

        private float anger;
        private float disgust;
        private float fear;
        private float happiness;
        private float neutral;
        private float sadness;
        private float surprise;

        public float getAnger() {
            return anger;
        }

        public void setAnger(float anger) {
            this.anger = anger;
        }

        public float getDisgust() {
            return disgust;
        }

        public void setDisgust(float disgust) {
            this.disgust = disgust;
        }

        public float getFear() {
            return fear;
        }

        public void setFear(float fear) {
            this.fear = fear;
        }

        public float getHappiness() {
            return happiness;
        }

        public void setHappiness(float happiness) {
            this.happiness = happiness;
        }

        public float getNeutral() {
            return neutral;
        }

        public void setNeutral(float neutral) {
            this.neutral = neutral;
        }

        public float getSadness() {
            return sadness;
        }

        public void setSadness(float sadness) {
            this.sadness = sadness;
        }

        public float getSurprise() {
            return surprise;
        }

        public void setSurprise(float surprise) {
            this.surprise = surprise;
        }

        @Override
        public String toString() {
            return "Emotion{" +
                    "anger=" + anger +
                    ", disgust=" + disgust +
                    ", fear=" + fear +
                    ", happiness=" + happiness +
                    ", neutral=" + neutral +
                    ", sadness=" + sadness +
                    ", surprise=" + surprise +
                    '}';
        }
    }

    /**
     * 颜值识别结果。返回值包含以下两个字段。每个字段的值是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。
     * <p>
     * male_score：男性认为的此人脸颜值分数。值越大，颜值越高。
     * female_score：女性认为的此人脸颜值分数。值越大，颜值越高。
     */
    public class Beauty {
        private float male_score;
        private float female_score;

        public float getMale_score() {
            return male_score;
        }

        public void setMale_score(float male_score) {
            this.male_score = male_score;
        }

        public float getFemale_score() {
            return female_score;
        }

        public void setFemale_score(float female_score) {
            this.female_score = female_score;
        }

        @Override
        public String toString() {
            return "Beauty{" +
                    "male_score=" + male_score +
                    ", female_score=" + female_score +
                    '}';
        }
    }

    /**
     * 面部特征识别结果，包括以下字段。每个字段的值都是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。每个字段的返回值越大，则该字段代表的状态的置信度越高。
     * <p>
     * health：健康
     * stain：色斑
     * acne：青春痘
     * dark_circle：黑眼圈
     */
    public class Skinstatus {
        private float health;
        private float stain;
        private float acne;
        private float dark_circle;

        public float getHealth() {
            return health;
        }

        public void setHealth(float health) {
            this.health = health;
        }

        public float getStain() {
            return stain;
        }

        public void setStain(float stain) {
            this.stain = stain;
        }

        public float getAcne() {
            return acne;
        }

        public void setAcne(float acne) {
            this.acne = acne;
        }

        public float getDark_circle() {
            return dark_circle;
        }

        public void setDark_circle(float dark_circle) {
            this.dark_circle = dark_circle;
        }

        @Override
        public String toString() {
            return "Skinstatus{" +
                    "health=" + health +
                    ", stain=" + stain +
                    ", acne=" + acne +
                    ", dark_circle=" + dark_circle +
                    '}';
        }
    }
}
