package com.sidney.buscacep.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.sidney.buscacep.R;
import com.sidney.buscacep.model.Address;
import com.sidney.buscacep.persistence.AddressRoomDatabase;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private final List<Address> addressList;
    private final Context context;

    public AddressAdapter(List<Address> addressList, Context context) {
        this.addressList = addressList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddressAdapter.AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AddressViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Address address = addressList.get(position);
        holder.bind(address);

        holder.itemRemove.setOnClickListener(v -> {
            final View view = v;
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Confirmação")
                    .setMessage("Tem certeza que deseja excluir este endereço?")
                    .setPositiveButton("Excluir", (dialog, which) -> {
                        Address addressRemove = addressList.get(holder.getAdapterPosition());
                        AddressRoomDatabase db = AddressRoomDatabase.getDatabase(context.getApplicationContext());

                        new Thread(() -> {
                            int success = db.addressDAO().remove(addressRemove);
                            Log.i("remove", String.valueOf(success));
                            if (success == 1) {
                                addressList.remove(address);
                                Snackbar.make(view, "Excluído", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            } else {
                                Snackbar.make(view, "Erro ao excluir!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            }
                        }).start();
                        notifyItemRemoved(position);
                    }).setNegativeButton("Cancelar", null)
                    .create()
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        if (addressList == null || addressList.size() == 0)
            return 0;
        return addressList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AddressViewHolder extends RecyclerView.ViewHolder {
        final private TextView rua;
        final private TextView bairro;
        final private TextView cidade;
        final private TextView uf;
        final private ImageView itemRemove;

        public AddressViewHolder(@NonNull View view) {
            super(view);
            rua = view.findViewById(R.id.rua);
            bairro = view.findViewById(R.id.bairro);
            cidade = view.findViewById(R.id.cidade);
            uf = view.findViewById(R.id.sigla_estado);
            itemRemove = view.findViewById(R.id.btn_remove_address);
        }

        static AddressViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
            return new AddressViewHolder(view);
        }

        public void bind(@NonNull Address address) {
            rua.setText(address.getLogradouro());
            bairro.setText(address.getBairro());
            cidade.setText(address.getLocalidade());
            uf.setText(address.getUf());
        }
    }
}
