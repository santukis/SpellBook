package com.santukis.spellbook.data.gateway;

import android.content.Context;

import com.santukis.spellbook.data.local.SpellsDatabase;
import com.santukis.spellbook.data.mapper.AvatarMapper;
import com.santukis.spellbook.data.model.AvatarEntity;
import com.santukis.spellbook.domain.boundary.AvatarGateway;
import com.santukis.spellbook.domain.model.Avatar;

import java.util.List;

public class AvatarsGatewayImp implements AvatarGateway {

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
    public List<Avatar> loadCharacters() {
        List<AvatarEntity> entities = database.avatarDao().getAvatars();
        return AvatarMapper.map(entities);
    }
}
