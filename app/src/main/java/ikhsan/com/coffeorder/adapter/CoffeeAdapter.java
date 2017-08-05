package ikhsan.com.coffeorder.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ikhsan.com.coffeorder.R;
import ikhsan.com.coffeorder.model.DatabaseModel;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    static ArrayList<DatabaseModel> model = new ArrayList<DatabaseModel>();

    static Context context;

    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    public CoffeeAdapter(Context context, ArrayList<DatabaseModel> model, ListItemClickListener listener){
        this.model = new ArrayList<DatabaseModel>();
        this.context = context;
        this.model = model;
        mOnClickListener = listener;

    }

    @Override
    public CoffeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.menu_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view,context,model);
        return viewHolder;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvMenuList,tvPriceList,tvTotOrder;

        ArrayList<DatabaseModel> model = new ArrayList<DatabaseModel>();

        Context context;

        public ViewHolder(View itemView, Context context, ArrayList<DatabaseModel> itemList) {
            super(itemView);

            this.model = itemList;
            this.context = context;
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
            dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("Jumlah Pesanan");
            // set the custom dialog components - text, image and button
            final EditText etTotOrder = (EditText) dialog.findViewById(R.id.etTotOrder);
            if(tvTotOrder!=null){
                etTotOrder.setText(tvTotOrder.getText().toString());
            }
            Button dialogButton = (Button) dialog.findViewById(R.id.btnTotOrder);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strTorOrder = etTotOrder.getText().toString();
                    tvTotOrder.setText(strTorOrder);
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    @Override
    public void onBindViewHolder(CoffeeAdapter.ViewHolder holder, int position) {


        holder.tvMenuList.setText(model.get(position).getMenu());
        holder.tvPriceList.setText(model.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}