package com.santukis.spellbook.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.santukis.spellbook.data.model.AvatarEntity;
import com.santukis.spellbook.data.model.SpellEntity;

@Database(entities = {AvatarEntity.class, SpellEntity.class}, version = 1)
public abstract class SpellsDatabase extends RoomDatabase {

    private static SpellsDatabase DATABASE = null;

    public abstract AvatarDao avatarDao();

    public abstract SpellsDao spellsDao();

    public static SpellsDatabase getDatabase(Context context) {
        if(DATABASE == null) {
            DATABASE = Room.databaseBuilder(context.getApplicationContext(), SpellsDatabase.class, "spellsDB").build();
        }
        return DATABASE;
    }
}
