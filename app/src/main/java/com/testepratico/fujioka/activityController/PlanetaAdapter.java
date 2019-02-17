package com.testepratico.fujioka.activityController;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.testepratico.fujioka.R;
import com.testepratico.fujioka.model.Planeta;

import java.util.ArrayList;

public class PlanetaAdapter extends BaseAdapter {

    private final ArrayList<Planeta> planetas;
    private final Activity activity;

    public PlanetaAdapter(ArrayList<Planeta> planetas, Activity activity) {
        this.planetas = planetas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return planetas.size();
    }

    @Override
    public Object getItem(int position) {
        return planetas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = activity.getLayoutInflater()
                .inflate(R.layout.item_planeta, parent, false);

        Planeta planeta = planetas.get(position);
        // TODO adionar atributo imagem
        TextView nome = (TextView) rowView.findViewById(R.id.id_nome_planeta);
        TextView rotacao = (TextView) rowView.findViewById(R.id.id_rotacao);

        nome.setText(planeta.getNome());
        rotacao.setText(planeta.getRotacao());

        return rowView;
    }
}
