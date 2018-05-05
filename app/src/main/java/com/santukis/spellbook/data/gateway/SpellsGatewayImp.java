package com.santukis.spellbook.data.gateway;

import android.content.Context;

import com.santukis.spellbook.data.filter.Criteria;
import com.santukis.spellbook.data.filter.ProfessionCriteria;
import com.santukis.spellbook.data.filter.SchoolCriteria;
import com.santukis.spellbook.data.filter.SpellFilter;
import com.santukis.spellbook.data.local.Settings;
import com.santukis.spellbook.data.local.SpellsDatabase;
import com.santukis.spellbook.data.mapper.SpellMapper;
import com.santukis.spellbook.data.model.SpellEntity;
import com.santukis.spellbook.domain.boundary.SpellsGateway;
import com.santukis.spellbook.domain.model.Spell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SpellsGatewayImp implements SpellsGateway {

    private static SpellsGatewayImp INSTANCE = null;

    private Spell cachedSpell = Spell.EMPTY_SPELL;

    private Settings settings;
    private SpellsDatabase database;

    private SpellsGatewayImp(Context context) {
        database = SpellsDatabase.getDatabase(context);
        settings = Settings.getInstance(context);
    }

    public static SpellsGatewayImp getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SpellsGatewayImp(context);
        }
        return INSTANCE;
    }

    @Override
    public List<Spell> loadSpells(InputStream stream) {
        List<Spell> spells = new ArrayList<>();
        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));

            String csv;

            while ((csv = reader.readLine()) != null) {
                Spell spell = SpellMapper.map(csv);
                spells.add(spell);
            }

            spells = filterSpells(spells);

            return spells;

        } catch (IOException e) {
            e.printStackTrace();
            return spells;

        } finally {
            try {
                reader.close();

            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Spell loadSpell() {
        return cachedSpell;
    }

    @Override
    public List<Spell> loadAvatarSpells(String avatarName) {
        List<SpellEntity> entities = database.spellsDao().getSpellsFrom(avatarName);
        return SpellMapper.map(entities);
    }

    @Override
    public boolean saveSpell(Spell spell, List<String> avatarsNames) {
        if (spell != Spell.EMPTY_SPELL) cachedSpell = spell;

        if (avatarsNames == null || avatarsNames.isEmpty()) return false;

        for (String name : avatarsNames) {
            long insertedSpell = database.spellsDao().insert(SpellMapper.map(cachedSpell, name));
            if (insertedSpell == 0) return false;
        }

        return true;
    }

    @Override
    public boolean deleteSpell(Spell spell) {
        database.spellsDao().delete(SpellMapper.map(spell, ""));
        return true;
    }

    private List<Spell> filterSpells(List<Spell> unfilteredList) {
        Criteria<Spell> schoolCriteria = new SchoolCriteria(settings.getSchools());
        Criteria<Spell> professionCriteria = new ProfessionCriteria(settings.getProfessions());

        return new SpellFilter(unfilteredList)
                .by(schoolCriteria)
                .and(professionCriteria)
                .filter();
    }
}
