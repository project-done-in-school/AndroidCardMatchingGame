package com.example.a108820027_final;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFrag newInstance(String param1, String param2) {
        GameFrag fragment = new GameFrag();
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
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);

        Button homeButton = getView().findViewById(R.id.homeButton);
        Button revealButton = getView().findViewById(R.id.revealButton);

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_container, MainFrag.class, null);
                ft.commit();
            }
        });

        revealButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_container, ResultFrag.class, null);
                ft.commit();
            }
        });
    }


    public void flipCard(Context context,View visibleView,View inVisibleView) {

        visibleView.setVisibility(View.VISIBLE);

        AnimatorSet flipOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip_out);
        flipOutAnimatorSet.setTarget(inVisibleView);

        AnimatorSet flipInAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip_in);
        flipInAnimatorSet.setTarget(visibleView);

        flipOutAnimatorSet.start();
        flipInAnimatorSet.start();
        flipInAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                inVisibleView.setVisibility(View.GONE);
            }
        });
    }
}