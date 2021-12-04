package com.sidney.buscacep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sidney.buscacep.R;
import com.sidney.buscacep.model.Address;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private List<Address> addressList;
    private Context context;

    public AddressAdapter(Context context) {
        this.context = context;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public AddressAdapter.AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.bind(address);
    }

    @Override
    public int getItemCount() {
        if (addressList == null || addressList.size() == 0)
            return 0;
        return addressList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        final private TextView rua;
        final private TextView bairro;
        final private TextView cidade;
        final private TextView uf;

        public AddressViewHolder(@NonNull View view) {
            super(view);
            rua = view.findViewById(R.id.rua);
            bairro = view.findViewById(R.id.bairro);
            cidade = view.findViewById(R.id.cidade);
            uf = view.findViewById(R.id.sigla_estado);
        }

        public void bind(@NonNull Address address) {
            rua.setText(address.getLogradouro());
            bairro.setText(address.getBairro());
            cidade.setText(address.getLocalidade());
            uf.setText(address.getUf());
        }
    }
}
