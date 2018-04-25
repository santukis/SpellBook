package com.santukis.spellbook.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.santukis.spellbook.data.model.AvatarEntity;

import java.util.List;

@Dao
public interface AvatarDao {

    @Query("SELECT * FROM avatars")
    List<AvatarEntity> getAvatars();

    @Insert
    void insert(AvatarEntity character);

    @Delete
    void delete(AvatarEntity character);
}
