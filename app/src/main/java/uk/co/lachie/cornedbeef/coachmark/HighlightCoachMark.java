package uk.co.lachie.cornedbeef.coachmark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import uk.co.lachie.cornedbeef.R;

/**
 * Can be used to highlight part, or all of a given 'anchor' view
 *
 * Consider using TrackedHighlightCoachMark if you'd like to track when the
 * coach mark is shown.
 * 
 * @author lachie
 */
public class HighlightCoachMark extends InternallyAnchoredCoachMark {

    protected HighlightCoachMark(HighlightCoachMarkBuilder builder) {
        super(builder);
    }

    @Override
    protected PopupWindow createNewPopupWindow(View contentView) {
        PopupWindow popup = new PopupWindow(
                contentView,
                LayoutParams.WRAP_CONTENT, 
                LayoutParams.WRAP_CONTENT);
        
        popup.setTouchable(false);
        return popup;
    }
    
    protected View createContentView(View content) {
        return LayoutInflater.from(mContext).inflate(R.layout.highlight_coach_mark, null);
    }

    @Override
    protected CoachMarkDimens<Integer> getPopupDimens(CoachMarkDimens<Integer> anchorDimens) {
        return anchorDimens;
    }

    @Override
    protected void updateView(CoachMarkDimens<Integer> popupDimens,  CoachMarkDimens<Integer> anchorDimens) {
        mPopup.update(popupDimens.x, popupDimens.y, popupDimens.width, popupDimens.height);
    }
    
    public static class HighlightCoachMarkBuilder extends InternallyAnchoredCoachMarkBuilder {
        
        public HighlightCoachMarkBuilder(Context context, View anchor, String message) {
            super(context, anchor, message);
        }

        public HighlightCoachMarkBuilder(Context context, View anchor, View content) {
            super(context, anchor, content);
        }

        public HighlightCoachMarkBuilder(Context context, View anchor, int contentResId) {
            super(context, anchor, contentResId);
        }
        
        @Override
        public CoachMark build() {
            return new HighlightCoachMark(this);
        }
    }
}
