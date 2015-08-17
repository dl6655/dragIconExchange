package com.ding;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.*;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dingli on 2015-7-23.
 * 自定义视图页面
 */
public class VDHLayout extends LinearLayout implements View.OnLongClickListener, View.OnTouchListener {
    private ViewDragHelper mDragger;
    private Point mDownPos = new Point();
    private Point mUpPos = new Point();
    private Point mMovePos = new Point();
    private ArrayList<View> views = new ArrayList<View>();

    public VDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        setOnLongClickListener(this);
    }

    int downX = 0, downY = 0, moveX = 0, moveY = 0, upX = 0, upY = 0, dx = 0, dy = 0;
    boolean isSwap = false;


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int viewCount = getChildCount();
        for (int i = 0; i < viewCount; i++) {
            views.add(getChildAt(i));
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    /**
     * downView 按下时落在的视图区
     * upView   抬起时落在的视图区
     * moveView 移动时落在的视图区
     */
    private View downView, upView, moveView;

    private RotateAnimation createRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(-2.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setDuration(60);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        return rotateAnimation;
    }

    private View findViewUpByxy(int pox, int poy) {
        for (int i = 0; i < views.size(); i++) {
            View dv = views.get(i);
            if (downView != null && dv.equals(downView)) {
                continue;
            }
            if (inView(dv, pox, poy)) {
                return dv;
            }
        }
        return null;
    }

    private View findViewDownByxy(int pox, int poy) {
        for (int i = 0; i < views.size(); i++) {
            View dv = views.get(i);
            if (inView(dv, pox, poy)) {
                return dv;
            }
        }
        return null;
    }

    private boolean inView(View dv, int pox, int poy) {
        int dx = dv.getLeft();
        int dy = dv.getTop();
        int dWidth = dv.getWidth();
        int dHeight = dv.getHeight();
        if (pox >= dx && pox < (dx + dWidth) &&
                poy >= dy && poy < (dy + dHeight)) {
            return true;
        }
        return false;
    }

    private void swapView(int index1, int index2) {
        Collections.swap(views, index1, index2);

    }

    private View getViewByIndex(int index) {
        return views.get(index);

    }



    private void cancelScaleAnimation(){
        if(downView!=null){
            downView.clearAnimation();
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                touchUp(event);
                break;
        }
        return false;
    }
    private boolean movingView;
    private void touchDown(MotionEvent event) {
        downX = (int) event.getX();
        downY = (int) event.getY();
        downView = findViewDownByxy(downX, downY);
        if (downView != null) {
            mDownPos.x = downView.getLeft();
            mDownPos.y = downView.getTop();
        }

    }

    private void touchMove(MotionEvent event) {
        if(movingView){
            moveX = (int) event.getX();
            moveY = (int) event.getY();

            int delx = moveX - downX;
            int dely = moveY - downY;

            moveView = findViewUpByxy(moveX, moveY);

            if (downView != null) {
                int l = delx + downView.getLeft();
                int t = dely + downView.getTop();
                int r = delx + downView.getRight();
                int b = dely + downView.getBottom();
                downView.layout(l, t, r, b);
            }
            if (moveView != null && downView != null) {
                mMovePos.x = moveView.getLeft();
                mMovePos.y = moveView.getTop();
//                animationMoveNewPosition(moveView, mMovePos, mDownPos);
                moveView.layout(mDownPos.x, mDownPos.y, mDownPos.x + downView.getWidth(), mDownPos.y + downView.getHeight());
                isSwap = true;
                mDownPos.x = mMovePos.x;
                mDownPos.y = mMovePos.y;
            }
            downX = (int) event.getX();
            downY = (int) event.getY();

        }

    }

    private void touchUp(MotionEvent event) {
        if (downView != null) {
            if (isSwap) {
                downView.layout(mMovePos.x, mMovePos.y, mMovePos.x + downView.getWidth(), mMovePos.y + downView.getHeight());
                isSwap = false;
            } else {
                downView.layout(mDownPos.x, mDownPos.y, mDownPos.x + downView.getWidth(), mDownPos.y + downView.getHeight());
            }
        }
        movingView=false;
        cancleRotateAllItem();

    }

    private View mView;
    private ScaleAnimation mScaleAnimation;

    @Override
    public boolean onLongClick(View view) {
        if (downView != null) {
            movingView=true;
            rotateAllitem();
            scaleAnimation();
            return true;
        }

        return false;
    }

    /**
     * 旋转抖动，缩放动画，位移动画
     */
    private void rotateAllitem() {
        RotateAnimation rotateAnimation = createRotateAnimation();
        for (int i = 0; i < views.size(); i++) {
            View view = views.get(i);
            if (view != downView) {
                view.startAnimation(rotateAnimation);
            }

        }
    }
    private void cancleRotateAllItem() {
        for (int i = 0; i < views.size(); i++) {
            View view = views.get(i);
            view.clearAnimation();
        }
    }
    private void scaleAnimation(){
        if(downView!=null){
            ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,1.4f,1.0f,1.4f,downView.getWidth()/2,downView.getHeight()/2);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setFillEnabled(true);
            downView.clearAnimation();
            downView.startAnimation(scaleAnimation);
        }

    }

    private TranslateAnimation createTranslateAnimation(Point oldOffset, Point newOffset) {
        TranslateAnimation translate = new TranslateAnimation(
                Animation.ABSOLUTE, oldOffset.x,
                Animation.ABSOLUTE, newOffset.x,
                Animation.ABSOLUTE, oldOffset.y,
                Animation.ABSOLUTE, newOffset.y);
        translate.setDuration(250);
        translate.setFillEnabled(true);
        translate.setFillAfter(true);
        translate.setInterpolator(new AccelerateDecelerateInterpolator());
        return translate;
    }
    private void animationMoveNewPosition(View targetView,Point oldOffset,Point newOffset){
        AnimationSet animationSet=new AnimationSet(true);

        RotateAnimation rotateAnimation=createRotateAnimation();
        TranslateAnimation translateAnimation=createTranslateAnimation(oldOffset,newOffset);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);

        targetView.clearAnimation();
        targetView.startAnimation(animationSet);


    }
}
