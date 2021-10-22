package com.example.tcc_estoque_20_09.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tcc_estoque_20_09.BancoDeDados.BancoDados;
import com.example.tcc_estoque_20_09.Produto.Produtos;
import com.example.tcc_estoque_20_09.R;
import com.example.tcc_estoque_20_09.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private Produtos produtos;
    private Produtos produtoMaior;
    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        //Referencias

        //Criação do banco de dados
        BancoDados bd = new BancoDados(getActivity(),1);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}