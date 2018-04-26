package com.santukis.spellbook.data.gateway;

import android.content.Context;

import com.santukis.spellbook.data.local.SpellsDatabase;
import com.santukis.spellbook.data.mapper.AvatarMapper;
import com.santukis.spellbook.data.mapper.SpellMapper;
import com.santukis.spellbook.data.model.AvatarEntity;
import com.santukis.spellbook.data.model.SpellEntity;
import com.santukis.spellbook.domain.boundary.AvatarsGateway;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public class AvatarsGatewayImp implements AvatarsGateway {

    private static AvatarsGatewayImp INSTANCE = null;
    private SpellsDatabase database;

    private AvatarsGatewayImp(Context context) {
        database = SpellsDatabase.getDatabase(context);
    }

    public static AvatarsGatewayImp getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AvatarsGatewayImp(context);
        }
        return INSTANCE;
    }

    @Override
    public List<Avatar> loadAvatars() {
        List<AvatarEntity> entities = database.avatarDao().getAvatars();
        return AvatarMapper.map(entities);
    }

    @Override
    public boolean saveAvatar(Avatar avatar) {
        long insertedId = database.avatarDao().insert(AvatarMapper.map(avatar));
        return insertedId != 0;
    }

    @Override
    public List<Spell> loadAvatarSpells(String avatarName) {
        List<SpellEntity> entities = database.spellsDao().getSpellsFrom(avatarName);
        return SpellMapper.map(entities);
    }
}
