package com.ding;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by dingli on 2015-7-28.
 */
public class ComponentActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                new String[]{"拖动交换图标", "拖动图标"}));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent;
        if(position==0){
            intent=new Intent(this,DragActivity.class);
        }else{
            intent=new Intent(this,MoveActivity.class);
        }
        startActivity(intent);



    }
}
