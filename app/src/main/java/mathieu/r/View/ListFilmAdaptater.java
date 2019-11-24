package mathieu.r.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mathieu.r.Model.Film;
import mathieu.r.R;

public class ListFilmAdaptater extends RecyclerView.Adapter<ListFilmAdaptater.ViewHolder> {

    private ArrayList<Film> dataset;                                                                //List d'object Film

    public ListFilmAdaptater() {
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Film film = dataset.get(position);                                                          // Recuperation d'un film en fonction de son numero
        holder.titreView.setText(film.getTitle());                                                  // Initialisation du titre dans l'objet Film

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titreView;

        public ViewHolder( View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            titreView = (TextView) itemView.findViewById(R.id.titreView);


        }
    }
}
