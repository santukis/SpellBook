package com.santukis.spellbook.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.santukis.spellbook.data.model.SpellEntity;

import java.util.List;

@Dao
public interface SpellsDao {

    @Query("SELECT * FROM spells WHERE avatar_name == :name ORDER BY name")
    List<SpellEntity> getSpellsFrom(String name);

    @Insert
    long insert(SpellEntity spell);

    @Delete
    void delete(SpellEntity spell);
}
