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
//        //ʹͼ�����������ʧ������һ�ֱ����ϵ����С��ĸо�
//        child.setVisibility(GONE);
//        ......
//        final Canvas canvas = new Canvas();
//
//        // We need to add extra padding to the bitmap to make room for the glow effect
//        final int bitmapPadding = HolographicOutlineHelper.MAX_OUTER_BLUR_RADIUS;
//
//        // The outline is used to visualize where the item will land if dropped
//        //ͼ����������������ϵĶ�Ӧ��λ�û���ͼ�����������ʾ�����ɿ�ͼ��ʱ���������ϵ����
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
//        //���ǽ���DragLayer�л��ơ���ק�󡱵�ͼ�꣬ͨ��DragLayer.getLoactionInDragLayer()
//        //��ȡ��DragLayer�е����꣬�������mTempXY�С�
//        mLauncher.getDragLayer().getLocationInDragLayer(child, mTempXY);
//        final int dragLayerX = (int) mTempXY[0] + (child.getWidth() - bmpWidth) / 2;
//        int dragLayerY = mTempXY[1] - bitmapPadding / 2;
//
//        Point dragVisualizeOffset = null;
//        Rect dragRect = null;
//
//        //����child��BubbleTextView����PagedViewIncon����FolderIcon��ʵ��
//        //��λͼ���λ�����С
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
//        //����mDragging=true����ʾ��ק�Ѿ���ʼ
//        //��DragLayer��onInterceptTouchEvent()�и������ֵ�ж��Ƿ�����MotionEvent
//        mDragging = true;
//
//        //ʵ����DragObject����ʾ��ק�Ķ���
//        //��װ����ק�������Ϣ
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
//        //����ק��ͼ����ʾ��DragLayer��
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
//        //��DragView��ӵ�DragLayer��
//        mDragLayer.addView(this);
//
//        //����λ�á��ߴ����Ϣ
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
//                //�жϵ�ǰ�Ĵ����Ƿ�����Ļ��Ե��ScrollZone���������������ʱ
//                //״̬mScrollState��ת��ΪSCROLL,������һ��ʱ���ͣ��֮����Ļ��������һ����
//                if ((dragLayerX < mScrollZone) || (dragLayerX > mScrollView.getWidth() - mScrollZone)) {
//                    mScrollState = SCROLL_WAITING_IN_ZONE;
//                    mHandler.postDelayed(mScrollRunnable, SCROLL_DELAY);
//                } else {
//                    mScrollState = SCROLL_OUTSIDE_ZONE;
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //����handleMoveEvent()����ͼ���ƶ�
//                handleMoveEvent(dragLayerX, dragLayerY);
//                break;
//            case MotionEvent.ACTION_UP:
//                // Ensure that we've processed a move event at the current pointer location.
//                handleMoveEvent(dragLayerX, dragLayerY);
//
//                mHandler.removeCallbacks(mScrollRunnable);
//                if (mDragging) {
//                    <span style="white-space:pre">	</span>//����Ŀǰ���DragLayer�����꣬��ͼ�ꡰ���䡱��ָ����DropTarget�ϡ�
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
//        //������DragLayer�е�λ��
//        mDragObject.dragView.move(x, y);
//
//        // Drop on someone?
//        final int[] coordinates = mCoordinatesTemp;
//
//        //���ݵ�ǰ��λ��Ѱ��DropTarget����������ͼ��
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
//                    //�����һ�μ�¼��DropTarget���˳�
//                    mLastDropTarget.onDragExit(mDragObject);
//                }
//                //���뵽��ǰѰ�ҵ���DropTarget
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
//        //�жϵ�ǰ��ק��ͼ���Ƿ���ScrollZone����������
//        //���Ҹ������ĸ�һ��ScrollZone��������Ļ�����ķ���
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
