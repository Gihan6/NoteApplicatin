package com.creativematrix.noteapp.services;

import android.graphics.Color;
import android.view.Gravity;

import com.bsk.floatingbubblelib.FloatingBubbleConfig;
import com.bsk.floatingbubblelib.FloatingBubbleService;
import com.creativematrix.noteapp.R;

public class FloatingService extends FloatingBubbleService {

    @Override
    protected FloatingBubbleConfig getConfig() {
        return new FloatingBubbleConfig.Builder()
                // Set the drawable for the bubble
               // .bubbleIcon(bubbleDrawable)

                // Set the drawable for the remove bubble
               // .removeBubbleIcon(removeIconDrawable)

                // Set the size of the bubble in dp
                .bubbleIconDp(64)

                // Set the size of the remove bubble in dp
                .removeBubbleIconDp(64)

                // Set the padding of the view from the boundary
                .paddingDp(4)

                // Set the radius of the border of the expandable view
                .borderRadiusDp(4)

                // Does the bubble attract towards the walls
                .physicsEnabled(true)

                // The color of background of the layout
                .expandableColor(Color.WHITE)

                // The color of the triangular layout
                .triangleColor(Color.WHITE)

                // Horizontal gravity of the bubble when expanded
                .gravity(Gravity.END)

                // The view which is visible in the expanded view
               .expandableView(getInflater().inflate(R.layout.content_note_home, null))

                // Set the alpha value for the remove bubble icon
                .removeBubbleAlpha(0.75f)

                // Building
                .build();
    }
}