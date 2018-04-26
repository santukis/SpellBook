package com.santukis.spellbook.presentation.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santukis.spellbook.R;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.components.OnSpellClick;

import java.util.ArrayList;
import java.util.List;

public class SpellsAdapter extends RecyclerView.Adapter<SpellsAdapter.ViewHolder> {

    private Context context;
    private List<Spell> spells = new ArrayList<>();

    private OnSpellClick onSpellClick;

    public SpellsAdapter(Context context) {
        this.context = context;
    }

    public void setOnSpellClick(OnSpellClick onSpellClick) {
        this.onSpellClick = onSpellClick;
    }

    @NonNull
    @Override
    public SpellsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.element_spell, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellsAdapter.ViewHolder holder, int position) {
        final Spell spell = spells.get(position);

        holder.nameView.setText(spell.getName());
        holder.schoolView.setText(spell.getSchool().name());
        String level = String.valueOf(spell.getLevel());
        holder.levelView.setText(level.equals("0") ? "Truco" : "Nivel: " + level);

        holder.itemView.setOnClickListener(v -> {
            onSpellClick.onClick(spell);
        });
    }

    @Override
    public int getItemCount() {
        return spells.size();
    }

    public void updateSpells(List<Spell> spells) {
        this.spells.clear();
        this.spells.addAll(spells);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameView;
        private TextView schoolView;
        private TextView levelView;

        ViewHolder(View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.tv_name);
            schoolView = itemView.findViewById(R.id.tv_school);
            levelView = itemView.findViewById(R.id.tv_level);
        }
    }
}
