package com.example.wowtancorik.gameofthronesuniverse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.wowtancorik.gameofthronesuniverse.Data.Cache;
import com.example.wowtancorik.gameofthronesuniverse.Data.NameAndNumber;

import java.util.List;

import static com.example.wowtancorik.gameofthronesuniverse.Data.Cache.mFillListFlag;

/**
 * класс фрагмента содеражащего RecyclerView со списком героев
 *
 *  @author Alexandr Karpachev
 *         Created on 08.06.18
 */

public class MyListFragment extends Fragment {

    public static final String TAG = "list_fragment";

    private MyAdapter mAdapter;
    private ProgressBar mProgressBar;
    private MyAdapter.IListener mListener;

    public static MyListFragment newInstance() {
        return new MyListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.progress_bar);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new MyAdapter(mListener);
        recyclerView.setAdapter(mAdapter);

        if (savedInstanceState != null && mFillListFlag) {
            onDataLoaded(Cache.getInstance().getList());
        }
    }

    /**
     * когда данные загруже recyclerview обновляется с новыми данными
     *
     * @param list
     */
    public void onDataLoaded(List<NameAndNumber> list) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mAdapter.addDate(list);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * получаем ссылку для слушателя из активити для передачи в адаптер
     *
     * @param listener
     */
    public void setListener(MyAdapter.IListener listener) {
        mListener = listener;
    }
}
