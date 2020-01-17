package org.spacelab.helloworld.data.entiry;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "images")
public final class Image {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "image_id")
    private final String mImageId;

    @NonNull
    @ColumnInfo(name = "request_id")
    private final String mRequestId;

    @NonNull
    @ColumnInfo(name = "time_used")
    private final int mTimeUsed;

    @ColumnInfo(name = "error_message")
    private final String mErrorMessage;

    public Image(@NonNull String mImageId, @NonNull String mRequestId, int mTimeUsed, String mErrorMessage) {
        this.mImageId = mImageId;
        this.mRequestId = mRequestId;
        this.mTimeUsed = mTimeUsed;
        this.mErrorMessage = mErrorMessage;
    }

    @NonNull
    public String getImageId() {
        return mImageId;
    }

    @NonNull
    public String getRequestId() {
        return mRequestId;
    }

    public int getTimeUsed() {
        return mTimeUsed;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return mTimeUsed == image.mTimeUsed &&
                mImageId.equals(image.mImageId) &&
                mRequestId.equals(image.mRequestId) &&
                Objects.equals(mErrorMessage, image.mErrorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mImageId, mRequestId, mTimeUsed, mErrorMessage);
    }

    @Override
    public String toString() {
        return "Image{" +
                "mImageId='" + mImageId + '\'' +
                ", mRequestId='" + mRequestId + '\'' +
                ", mTimeUsed=" + mTimeUsed +
                ", mErrorMessage='" + mErrorMessage + '\'' +
                '}';
    }
}
