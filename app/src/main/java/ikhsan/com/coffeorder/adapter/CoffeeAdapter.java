package ikhsan.com.coffeorder.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ikhsan.com.coffeorder.R;
import ikhsan.com.coffeorder.model.DatabaseModel;
import ikhsan.com.coffeorder.user.MainActivity;

import static ikhsan.com.coffeorder.R.id.etTotOrder;

/**
 * Created by ikhsan on 01/07/17.
 */

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    static List<DatabaseModel> dbList;

    static Context context;

    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    public CoffeeAdapter(Context context, List<DatabaseModel> dbList, ListItemClickListener listener){
        this.dbList = new ArrayList<DatabaseModel>();
        this.context = context;
        this.dbList = dbList;
        mOnClickListener = listener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvMenuList;
        public TextView tvPriceList;
        public TextView tvTotOrder;
        public EditText etTotOrder;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMenuList = (TextView) itemView.findViewById(R.id.tv_list_menu);
            tvPriceList = (TextView) itemView.findViewById(R.id.tv_list_price);
            tvTotOrder = (TextView) itemView.findViewById(R.id.tv_tot_order);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
            dialogBox();
        }

        public void dialogBox(){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_dialog);
            Button cancel = (Button) dialog.findViewById(R.id.bCancel);
            Button save = (Button) dialog.findViewById(R.id.bOk);
            etTotOrder = (EditText) dialog.findViewById(R.id.etTotOrder);
            etTotOrder.setHint("jumlah pesanan");
            if(etTotOrder!=null){
                etTotOrder.setText(tvTotOrder.getText().toString());
            }

            dialog.show();

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("AA", "Click");
                    String strTotOrder = etTotOrder.getText().toString();
                    tvTotOrder.setText(strTotOrder);
                    dialog.dismiss();
                }
            });
        }

    }

    @Override
    public CoffeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.menu_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CoffeeAdapter.ViewHolder holder, int position) {
        holder.tvMenuList.setText(dbList.get(position).getMenu());
        holder.tvPriceList.setText(dbList.get(position).getPrice());
        holder.tvTotOrder.setText(dbList.get(position).getTotOrder());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }
}
