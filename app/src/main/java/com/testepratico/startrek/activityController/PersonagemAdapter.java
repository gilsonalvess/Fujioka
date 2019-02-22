package com.testepratico.startrek.activityController;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.testepratico.startrek.R;
import com.testepratico.startrek.model.Personagem;

import java.util.ArrayList;

public class PersonagemAdapter extends BaseAdapter {

    private final ArrayList<Personagem> personagens;
    private final Activity activity;

    public PersonagemAdapter(ArrayList<Personagem> personagens, Activity activity) {
        this.personagens = personagens;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return personagens.size();
    }

    @Override
    public Object getItem(int position) {
        return personagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = activity.getLayoutInflater()
                .inflate(R.layout.activity_item_personagem, parent, false);

        Personagem personagem = personagens.get(position);

        TextView nome = (TextView) rowView.findViewById(R.id.id_nome_pers);
        TextView funcao = (TextView) rowView.findViewById(R.id.id_funcao);
        TextView frota = (TextView) rowView.findViewById(R.id.id_frota);
        ImageView foto = (ImageView) rowView.findViewById(R.id.id_img_pers);


        if (personagem.getImagem() != null) {
            int idImagem = activity.getResources().getIdentifier(personagem.getImagem(), "drawable", activity.getPackageName());
            foto.setImageResource(idImagem);
        } else {
            foto.setImageResource(R.drawable.foto_pers_default_img);
        }

        nome.setText(personagem.getNome());
        funcao.setText(personagem.getFuncao());
        frota.setText(personagem.getFrota());

        return rowView;
    }
}
