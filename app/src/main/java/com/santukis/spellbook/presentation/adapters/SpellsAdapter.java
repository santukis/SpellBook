package com.santukis.spellbook.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santukis.spellbook.R;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.components.click.OnSpellClick;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SpellsAdapter extends RecyclerView.Adapter<SpellsAdapter.ViewHolder> implements Observer {

    private Context context;
    private List<Spell> filteredSpells = new ArrayList<>();
    private List<Spell> unfilteredSpells = new ArrayList<>();

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
        final Spell spell = filteredSpells.get(position);

        holder.nameView.setText(spell.getName());
        holder.schoolView.setText(spell.getSchool().getName());
        holder.schoolView.setTextColor(context.getResources().getColor(spell.getSchool().getColor()));
        String level = String.valueOf(spell.getLevel());
        holder.levelView.setText(level.equals("0") ? context.getString(R.string.cantrip) : context.getString(R.string.level) + ": " + level);
        addProfessions(spell, holder.professionsView);

        holder.itemView.setOnClickListener(v -> {
            onSpellClick.onClick(spell);
        });
    }

    private void addProfessions(Spell spell, TextView professionView) {
        String professions = "";
        for (int i = 0; i < spell.getProfessions().size(); i++) {
            professions = professions.concat(
                    context.getString(spell.getProfessions().get(i).getName()).substring(0, 2));

            if (i < spell.getProfessions().size() - 1) {
                professions = professions.concat(", ");
            }
        }

        professionView.setText(professions);
    }

    @Override
    public int getItemCount() {
        return filteredSpells.size();
    }

    public Spell getSpell(int position) {
        return filteredSpells.get(position);
    }

    public List<Spell> getFilteredSpells() {
        return filteredSpells;
    }

    public void updateSpells(List<Spell> spells) {
        this.filteredSpells.clear();
        this.filteredSpells.addAll(spells);

        this.unfilteredSpells.clear();
        this.unfilteredSpells.addAll(spells);

        notifyDataSetChanged();
    }

    public void removeSpell(int position) {
        filteredSpells.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreSpell(int position, Spell spell) {
        filteredSpells.add(position, spell);
        notifyItemInserted(position);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String word = ((String) arg).toLowerCase();

            filteredSpells.clear();

            if (word.isEmpty()) {
                filteredSpells.addAll(unfilteredSpells);

            } else {
                for (Spell spell : unfilteredSpells) {
                    if (spell.getName().toLowerCase().contains(word)) {
                        filteredSpells.add(spell);
                    }
                }
            }

            notifyDataSetChanged();
        }
    }

    static class ViewHolder extends BaseViewHolder {

        private TextView professionsView;
        private TextView nameView;
        private TextView schoolView;
        private TextView levelView;

        ViewHolder(View itemView) {
            super(itemView);

            professionsView = itemView.findViewById(R.id.tv_profession);
            nameView = itemView.findViewById(R.id.tv_name);
            schoolView = itemView.findViewById(R.id.tv_school);
            levelView = itemView.findViewById(R.id.tv_level);
        }
    }
}
