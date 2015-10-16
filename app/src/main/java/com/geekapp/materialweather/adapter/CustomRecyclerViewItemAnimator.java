package com.geekapp.materialweather.adapter;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.geekapp.materialweather.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * when you add item or delete item ,the animation will run
 * but not tem recycler view animation
 */
public class CustomRecyclerViewItemAnimator extends RecyclerView.ItemAnimator {
    List<RecyclerView.ViewHolder> addAnimator = new ArrayList<>();
    List<RecyclerView.ViewHolder> deleteAnimator = new ArrayList<>();

    @Override
    public void runPendingAnimations() {
        if (!addAnimator.isEmpty()) {
            AnimatorSet animatorSet;
            View target;
            for (final RecyclerView.ViewHolder holder : addAnimator) {
                LogUtil.d("item animation");
                target = holder.itemView;
                animatorSet = new AnimatorSet();
                animatorSet.playTogether(
                        ObjectAnimator.ofFloat(target, "translationX", -target.getMeasuredWidth(), 0.0f)//,
                        //ObjectAnimator.ofFloat(target,"alpha",target.getAlpha(),1.0f)
                );
                animatorSet.setTarget(target);
                animatorSet.setDuration(5000);
                animatorSet.setInterpolator(new DecelerateInterpolator());
                animatorSet.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        addAnimator.remove(holder);
                        if (!isRunning()) {
                            dispatchAnimationsFinished();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }

                });
            }
        } else if (!deleteAnimator.isEmpty()) {

        }
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        return deleteAnimator.remove(holder);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        return addAnimator.add(holder);
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return !(addAnimator.isEmpty() && deleteAnimator.isEmpty());
    }
}
