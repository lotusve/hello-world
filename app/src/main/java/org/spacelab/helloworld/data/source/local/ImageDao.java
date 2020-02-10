package org.spacelab.helloworld.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.spacelab.helloworld.data.entiry.Image;

import java.util.List;

/**
 * Data Access Object
 */
@Dao
public interface ImageDao {

    @Query("SELECT * FROM images")
    List<Image> getImages();

    @Query("SELECT * FROM images ORDER By image_id DESC LIMIT 1")
    Image getImage();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(Image image);

    @Update
    int updateImage(Image image);

    @Query("DELETE FROM images")
    void deleteImages();

}
