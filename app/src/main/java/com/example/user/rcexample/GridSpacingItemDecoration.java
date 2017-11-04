package com.example.user.rcexample;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 10/17/2017.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration
{
    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private boolean includeLeftRightEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge)
    {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, boolean includeLeftRightEdge)
    {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.includeLeftRightEdge = includeLeftRightEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        try
        {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge)
            {
                if (includeLeftRightEdge) //include left/right edge
                {
                    outRect.left = spacing - column * spacing / spanCount;
                    outRect.right = (column + 1) * spacing / spanCount;
                }
                else //without left/right edge
                {
                    outRect.left = column * spacing / spanCount;
                    outRect.right = spacing - (column + 1) * spacing / spanCount;
                }
                if (position < spanCount)
                { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            }
            else
            {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount)
                {
                    outRect.top = spacing; // item top
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

