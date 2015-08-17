package com.ding;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by dingli on 2015-7-28.
 * 图标移动跟随
 */
public class MoveActivity extends Activity {

    private int					screenHeight;
    private int					screenWidth;
    private ImageView iv_drag;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate( savedInstanceState );
        this.setContentView(R.layout.move_activity);

        this.screenHeight = this.getWindowManager().getDefaultDisplay()
                .getHeight();
        this.screenWidth = this.getWindowManager().getDefaultDisplay()
                .getWidth();

        this.iv_drag = ( ImageView ) this.findViewById( R.id.imageview_ball );

        this.sp = this.getSharedPreferences( "config" , Context.MODE_PRIVATE );

        int lastx = this.sp.getInt( "lastx" , 0 );
        int lasty = this.sp.getInt( "lasty" , 0 );

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.iv_drag
                .getLayoutParams();
        params.leftMargin = lastx;
        params.topMargin = lasty;
        this.iv_drag.setLayoutParams( params );

        this.iv_drag.setOnTouchListener( new View.OnTouchListener() {
            int	startX;
            int	startY;

            @Override
            public boolean onTouch( View v, MotionEvent event ) {
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:// 手指第一次触摸到屏幕
                        this.startX = ( int ) event.getRawX();
                        this.startY = ( int ) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:// 手指移动
                        int newX = ( int ) event.getRawX();
                        int newY = ( int ) event.getRawY();

                        int dx = newX - this.startX;
                        int dy = newY - this.startY;

                        // 计算出来控件原来的位置
                        int l = MoveActivity.this.iv_drag.getLeft();
                        int r = MoveActivity.this.iv_drag.getRight();
                        int t = MoveActivity.this.iv_drag.getTop();
                        int b = MoveActivity.this.iv_drag.getBottom();

                        int newt = t + dy;
                        int newb = b + dy;
                        int newl = l + dx;
                        int newr = r + dx;

                        if ( ( newl < 0 ) || ( newt < 0 )
                                || ( newr > MoveActivity.this.screenWidth )
                                || ( newb > MoveActivity.this.screenHeight ) ) {
                            break;
                        }

                        // 更新iv在屏幕的位置.
                        MoveActivity.this.iv_drag
                                .layout( newl , newt , newr , newb );
                        this.startX = ( int ) event.getRawX();
                        this.startY = ( int ) event.getRawY();

                        break;
                    case MotionEvent.ACTION_UP: // 手指离开屏幕的一瞬间
                        int lastx = MoveActivity.this.iv_drag.getLeft();
                        int lasty = MoveActivity.this.iv_drag.getTop();
                        SharedPreferences.Editor editor =sp.edit();
                        editor.putInt( "lastx" , lastx );
                        editor.putInt( "lasty" , lasty );
                        editor.commit();
                        break;
                }
                return true;
            }
        } );

    }

}
