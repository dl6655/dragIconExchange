package com.ding;

import android.app.ListActivity;

/**
 * Created by dingli on 2015-7-27.
 */
public class WorkSpace extends ListActivity {

//
//    void startDrag(CellLayout.CellInfo cellInfo) {
//        View child = cellInfo.cell;
//        ......
//        mDragInfo = cellInfo;
//        //使图标从桌面上消失，给人一种被“拖到空中”的感觉
//        child.setVisibility(GONE);
//        ......
//        final Canvas canvas = new Canvas();
//
//        // We need to add extra padding to the bitmap to make room for the glow effect
//        final int bitmapPadding = HolographicOutlineHelper.MAX_OUTER_BLUR_RADIUS;
//
//        // The outline is used to visualize where the item will land if dropped
//        //图标的轮廓，在桌面上的对应的位置绘制图标的轮廓，显示当手松开图标时它在桌面上的落点
//        mDragOutline = createDragOutline(child, canvas, bitmapPadding);
//        beginDragShared(child, this);
//    }
//
//    public void beginDragShared(View child, DragSource source) {
//
//        // The drag bitmap follows the touch point around on the screen
//        final Bitmap b = createDragBitmap(child, new Canvas(), bitmapPadding);
//        final int bmpWidth = b.getWidth();
//
//        //我们将在DragLayer中绘制“拖拽后”的图标，通过DragLayer.getLoactionInDragLayer()
//        //获取在DragLayer中的坐标，并存放在mTempXY中。
//        mLauncher.getDragLayer().getLocationInDragLayer(child, mTempXY);
//        final int dragLayerX = (int) mTempXY[0] + (child.getWidth() - bmpWidth) / 2;
//        int dragLayerY = mTempXY[1] - bitmapPadding / 2;
//
//        Point dragVisualizeOffset = null;
//        Rect dragRect = null;
//
//        //无论child是BubbleTextView或者PagedViewIncon或者FolderIcon的实例
//        //定位图标的位置与大小
//        if (child instanceof BubbleTextView || child instanceof PagedViewIcon) {
//            int iconSize = r.getDimensionPixelSize(R.dimen.app_icon_size);
//            int iconPaddingTop = r.getDimensionPixelSize(R.dimen.app_icon_padding_top);
//            int top = child.getPaddingTop();
//            int left = (bmpWidth - iconSize) / 2;
//            int right = left + iconSize;
//            int bottom = top + iconSize;
//            dragLayerY += top;
//            // Note: The drag region is used to calculate drag layer offsets, but the
//            // dragVisualizeOffset in addition to the dragRect (the size) to position the outline.
//            dragVisualizeOffset = new Point(-bitmapPadding / 2, iconPaddingTop - bitmapPadding / 2);
//            dragRect = new Rect(left, top, right, bottom);
//        } else if (child instanceof FolderIcon) {
//            int previewSize = r.getDimensionPixelSize(R.dimen.folder_preview_size);
//            dragRect = new Rect(0, 0, child.getWidth(), previewSize);
//        }
//        ......
//        mDragController.startDrag(b, dragLayerX, dragLayerY, source, child.getTag(),
//                DragController.DRAG_ACTION_MOVE, dragVisualizeOffset, dragRect);
//        b.recycle();
//    }
//
//
//    /**
//     * Starts a drag.
//     *
//     * @param b The bitmap to display as the drag image.  It will be re-scaled to the
//     *          enlarged size.
//     * @param dragLayerX The x position in the DragLayer of the left-top of the bitmap.
//     * @param dragLayerY The y position in the DragLayer of the left-top of the bitmap.
//     * @param source An object representing where the drag originated
//     * @param dragInfo The data associated with the object that is being dragged
//     * @param dragAction The drag action: either {@link #DRAG_ACTION_MOVE} or
//     *        {@link #DRAG_ACTION_COPY}
//     * @param dragRegion Coordinates within the bitmap b for the position of item being dragged.
//     *          Makes dragging feel more precise, e.g. you can clip out a transparent border
//     */
//    public void startDrag(Bitmap b, int dragLayerX, int dragLayerY,
//                          DragSource source, Object dragInfo, int dragAction, Point dragOffset, Rect dragRegion) {
//        ......
//        for (DragListener listener : mListeners) {
//            listener.onDragStart(source, dragInfo, dragAction);
//        }
//
//        final int registrationX = mMotionDownX - dragLayerX;
//        final int registrationY = mMotionDownY - dragLayerY;
//
//        final int dragRegionLeft = dragRegion == null ? 0 : dragRegion.left;
//        final int dragRegionTop = dragRegion == null ? 0 : dragRegion.top;
//
//        //设置mDragging=true，表示拖拽已经开始
//        //在DragLayer的onInterceptTouchEvent()中根据这个值判断是否拦截MotionEvent
//        mDragging = true;
//
//        //实例化DragObject，表示拖拽的对象
//        //封装了拖拽对象的信息
//        mDragObject = new DropTarget.DragObject();
//
//        mDragObject.dragComplete = false;
//        mDragObject.xOffset = mMotionDownX - (dragLayerX + dragRegionLeft);
//        mDragObject.yOffset = mMotionDownY - (dragLayerY + dragRegionTop);
//        mDragObject.dragSource = source;
//        mDragObject.dragInfo = dragInfo;
//        ......
//        final DragView dragView = mDragObject.dragView = new DragView(mLauncher, b, registrationX,
//                registrationY, 0, 0, b.getWidth(), b.getHeight());
//        ......
//        //将拖拽的图标显示在DragLayer中
//        dragView.show(mMotionDownX, mMotionDownY);
//        handleMoveEvent(mMotionDownX, mMotionDownY);
//    }
//
//
//    /**
//     * Create a window containing this view and show it.
//     *
//     * @param touchX the x coordinate the user touched in DragLayer coordinates
//     * @param touchY the y coordinate the user touched in DragLayer coordinates
//     */
//    public void show(int touchX, int touchY) {
//        //将DragView添加到DragLayer中
//        mDragLayer.addView(this);
//
//        //设置位置、尺寸等信息
//        DragLayer.LayoutParams lp = new DragLayer.LayoutParams(0, 0);
//        lp.width = mBitmap.getWidth();
//        lp.height = mBitmap.getHeight();
//        lp.x = touchX - mRegistrationX;
//        lp.y = touchY - mRegistrationY;
//        lp.customPosition = true;
//        setLayoutParams(lp);
//        mLayoutParams = lp;
//        mAnim.start();
//    }
//
//    /**
//     * Call this from a drag source view.
//     */
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        ......
//        final int action = ev.getAction();
//
//        final int[] dragLayerPos = getClampedDragLayerPos(ev.getX(), ev.getY());
//        final int dragLayerX = dragLayerPos[0];
//        final int dragLayerY = dragLayerPos[1];
//
//        switch (action) {
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_DOWN:
//                // Remember location of down touch
//                mMotionDownX = dragLayerX;
//                mMotionDownY = dragLayerY;
//                mLastDropTarget = null;
//                break;
//            case MotionEvent.ACTION_UP:
//                if (mDragging) {
//                    drop(dragLayerX, dragLayerY);
//                }
//                endDrag();
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                cancelDrag();
//                break;
//        }
//
//        return mDragging;
//    }
//
//    /**
//     * Call this from a drag source view.
//     */
//    public boolean onTouchEvent(MotionEvent ev) {
//        ......
//        final int action = ev.getAction();
//        final int[] dragLayerPos = getClampedDragLayerPos(ev.getX(), ev.getY());
//        final int dragLayerX = dragLayerPos[0];
//        final int dragLayerY = dragLayerPos[1];
//
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                // Remember where the motion event started
//                mMotionDownX = dragLayerX;
//                mMotionDownY = dragLayerY;
//
//                //判断当前的触点是否处于屏幕边缘的ScrollZone，当处于这个区域时
//                //状态mScrollState将转变为SCROLL,并且在一定时间的停留之后，屏幕滑动到另一屏。
//                if ((dragLayerX < mScrollZone) || (dragLayerX > mScrollView.getWidth() - mScrollZone)) {
//                    mScrollState = SCROLL_WAITING_IN_ZONE;
//                    mHandler.postDelayed(mScrollRunnable, SCROLL_DELAY);
//                } else {
//                    mScrollState = SCROLL_OUTSIDE_ZONE;
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //调用handleMoveEvent()处理图标移动
//                handleMoveEvent(dragLayerX, dragLayerY);
//                break;
//            case MotionEvent.ACTION_UP:
//                // Ensure that we've processed a move event at the current pointer location.
//                handleMoveEvent(dragLayerX, dragLayerY);
//
//                mHandler.removeCallbacks(mScrollRunnable);
//                if (mDragging) {
//                    <span style="white-space:pre">	</span>//根据目前相对DragLayer的坐标，将图标“降落”到指定的DropTarget上。
//                            drop(dragLayerX, dragLayerY);
//                }
//                endDrag();
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                cancelDrag();
//                break;
//        }
//        return true;
//    }
//
//    private void handleMoveEvent(int x, int y) {
//        //更新在DragLayer中的位置
//        mDragObject.dragView.move(x, y);
//
//        // Drop on someone?
//        final int[] coordinates = mCoordinatesTemp;
//
//        //根据当前的位置寻找DropTarget对象来放置图标
//        DropTarget dropTarget = findDropTarget(x, y, coordinates);
//        mDragObject.x = coordinates[0];
//        mDragObject.y = coordinates[1];
//        if (dropTarget != null) {
//            DropTarget delegate = dropTarget.getDropTargetDelegate(mDragObject);
//            if (delegate != null) {
//                dropTarget = delegate;
//            }
//
//            if (mLastDropTarget != dropTarget) {
//                if (mLastDropTarget != null) {
//                    //从最后一次记录的DropTarget中退出
//                    mLastDropTarget.onDragExit(mDragObject);
//                }
//                //进入到当前寻找到的DropTarget
//                dropTarget.onDragEnter(mDragObject);
//            }
//            dropTarget.onDragOver(mDragObject);
//        } else {
//            if (mLastDropTarget != null) {
//                mLastDropTarget.onDragExit(mDragObject);
//            }
//        }
//        mLastDropTarget = dropTarget;
//
//        // Scroll, maybe, but not if we're in the delete region.
//        boolean inDeleteRegion = false;
//        if (mDeleteRegion != null) {
//            inDeleteRegion = mDeleteRegion.contains(x, y);
//        }
//
//        // After a scroll, the touch point will still be in the scroll region.
//        // Rather than scrolling immediately, require a bit of twiddling to scroll again
//        final int slop = ViewConfiguration.get(mLauncher).getScaledWindowTouchSlop();
//        mDistanceSinceScroll +=
//                Math.sqrt(Math.pow(mLastTouch[0] - x, 2) + Math.pow(mLastTouch[1] - y, 2));
//        mLastTouch[0] = x;
//        mLastTouch[1] = y;
//
//        //判断当前拖拽的图标是否处于ScrollZone即滑动区域。
//        //并且根据在哪个一个ScrollZone来处理屏幕滑动的方向。
//        if (!inDeleteRegion && x < mScrollZone) {
//            if (mScrollState == SCROLL_OUTSIDE_ZONE && mDistanceSinceScroll > slop) {
//                mScrollState = SCROLL_WAITING_IN_ZONE;
//                if (mDragScroller.onEnterScrollArea(x, y, SCROLL_LEFT)) {
//                    mScrollRunnable.setDirection(SCROLL_LEFT);
//                    mHandler.postDelayed(mScrollRunnable, SCROLL_DELAY);
//                }
//            }
//        } else if (!inDeleteRegion && x > mScrollView.getWidth() - mScrollZone) {
//            if (mScrollState == SCROLL_OUTSIDE_ZONE && mDistanceSinceScroll > slop) {
//                mScrollState = SCROLL_WAITING_IN_ZONE;
//                if (mDragScroller.onEnterScrollArea(x, y, SCROLL_RIGHT)) {
//                    mScrollRunnable.setDirection(SCROLL_RIGHT);
//                    mHandler.postDelayed(mScrollRunnable, SCROLL_DELAY);
//                }
//            }
//        } else {
//            if (mScrollState == SCROLL_WAITING_IN_ZONE) {
//                mScrollState = SCROLL_OUTSIDE_ZONE;
//                mScrollRunnable.setDirection(SCROLL_RIGHT);
//                mHandler.removeCallbacks(mScrollRunnable);
//                mDragScroller.onExitScrollArea();
//            }
//        }
//    }
}
