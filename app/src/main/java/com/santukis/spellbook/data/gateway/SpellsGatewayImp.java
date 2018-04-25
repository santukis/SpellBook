package com.santukis.spellbook.data.gateway;

import com.santukis.spellbook.data.mapper.SpellMapper;
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

    private SpellsGatewayImp() {}

    public static SpellsGatewayImp getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpellsGatewayImp();
        }
        return INSTANCE;
    }

    @Override
    public List<Spell> loadSpells(InputStream stream) {
        List<Spell> spells = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "ISO-8859-1"));

            String csv;

            while((csv = reader.readLine()) != null) {
                Spell spell = SpellMapper.map(csv);
                spells.add(spell);
            }

            stream.close();

            return  spells;

        } catch (IOException e) {
            e.printStackTrace();
            return spells;
        }
    }

    @Override
    public void cacheSpell(Spell spell) {
        cachedSpell = spell;
    }

    @Override
    public Spell loadSpell() {
        return cachedSpell;
    }
}
