package com.example.project_webapp.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_webapp.DetailActivity;
import com.example.project_webapp.LoginActivity;
import com.example.project_webapp.PembayaranActivity;
import com.example.project_webapp.ProgresPemesananActivity;
import com.example.project_webapp.R;
import com.example.project_webapp.RiwayatPemesananActivity;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.UserResponse;
import com.example.project_webapp.Service.SharedPreference.Preferences;
import com.example.project_webapp.TersimpanActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RelativeLayout logoutBtn, SimpanCluster, Progresbtn, pembayaran, riwayatpemesanan;
    private TextView nama, email;
    private View rootview;
    private SharedPreferences preferences;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootview = inflater.inflate(R.layout.fragment_setting, container, false);

         nama = rootview.findViewById(R.id.namaText);
         email = rootview.findViewById(R.id.emailText);
         SimpanCluster = rootview.findViewById(R.id.simpancluster);
         logoutBtn = rootview.findViewById(R.id.logoutbutton);
         Progresbtn = rootview.findViewById(R.id.progresbtn);
         pembayaran = rootview.findViewById(R.id.pembayaran);
         riwayatpemesanan = rootview.findViewById(R.id.riwayatpemesanan);

        // Inisialisasi SharedPreferences
        preferences = PreferenceManager.getDefaultSharedPreferences(rootview.getContext());


        getUser();

        SimpanCluster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan id_user secara otomatis (misalnya dari SharedPreferences atau dari sumber data lainnya)
                String idUser = Preferences.getLoggedInToken(getActivity());

                // Intent ke Activity lain
                Intent intent = new Intent(getActivity(), TersimpanActivity.class);
                intent.putExtra("id_user", idUser);
                startActivity(intent);
            }
        });

        riwayatpemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan id_user secara otomatis (misalnya dari SharedPreferences atau dari sumber data lainnya)
                String idUser = Preferences.getLoggedInToken(getActivity());

                // Intent ke Activity lain
                Intent intent = new Intent(getActivity(), RiwayatPemesananActivity.class);
                intent.putExtra("id_user", idUser);
                startActivity(intent);
            }
        });

        Progresbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan id_user secara otomatis (misalnya dari SharedPreferences atau dari sumber data lainnya)
                String idUser = Preferences.getLoggedInToken(getActivity());

                // Intent ke Activity lain
                Intent intent = new Intent(getActivity(), ProgresPemesananActivity.class);
                intent.putExtra("id_user", idUser);
                startActivity(intent);
            }
        });

        pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan id_user secara otomatis (misalnya dari SharedPreferences atau dari sumber data lainnya)
                String idUser = Preferences.getLoggedInToken(getActivity());

                // Intent ke Activity lain
                Intent intent = new Intent(getActivity(), PembayaranActivity.class);
                intent.putExtra("id_user", idUser);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(rootview.getContext());
                builder.setTitle("Konfirmasi Logout");
                builder.setMessage("Apakah Anda yakin ingin logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kode untuk logout
                        // ...

                        // Kembali ke halaman login
                        Preferences.clearLoggedInUser(rootview.getContext());
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return rootview;
    }
    private View getUser(){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().userDetail(Preferences.getLoggedInToken(rootview.getContext()));
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()){
                    UserResponse userResponse = response.body();
                    email.setText(userResponse.getEmail());
                    nama.setText(userResponse.getName());
                } else {
                    Toast.makeText(rootview.getContext(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
        return rootview;
    }
}
