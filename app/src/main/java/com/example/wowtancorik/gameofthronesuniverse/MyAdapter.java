package com.example.wowtancorik.gameofthronesuniverse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wowtancorik.gameofthronesuniverse.Data.NameAndNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс адаптер для RecyclerView
 *
 *  @author Alexandr Karpachev
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<NameAndNumber> mNameAndNumber = new ArrayList<>();
    private IListener mListener;


    /**
     * Конструктор
     *
     * @param listener      слушатель
     */
    public MyAdapter(IListener listener) {
        mListener = listener;
    }

    /**
     * Добавить данных
     *
     * @param list      данные
     */
    public void addDate(List<NameAndNumber> list) {
        mNameAndNumber.addAll(list);
    }


    /**
     * создание ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * соединение View и данных
     *
     * @param holder        холдер
     * @param position      позиция RecyclerView
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTextView.setText(mNameAndNumber.get(position).getName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickListener(mNameAndNumber.get(position).getNumber());
            }
        });
    }

    /**
     * вернуть размер RecyclerView
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mNameAndNumber.size();
    }

    /**
     * реализация ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.name_next_view);
        }
    }

    /**
     * интерфейс слушателя нажатия на RecyclerView
     */
    interface IListener {
        void clickListener(int number);
    }
}
