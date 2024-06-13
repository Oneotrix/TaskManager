package com.dirion.walltechtodo.view.features.orders.recycler

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.TEXT_ALIGNMENT_CENTER
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dirion.walltechtodo.databinding.ItemTasksListBinding
import com.dirion.walltechtodo.view.features.orders.OrderModel
import com.dirion.walltechtodo.view.features.orders.recycler.gesture.GestureListener
import com.dirion.walltechtodo.view.features.orders.recycler.gesture.OnSwipeTouchListener
import com.dirion.walltechtodo.view.global.SizeExtensions.dp
import com.dirion.walltechtodo.view.global.SizeExtensions.sp


class OrdersVH(
    val binding: ItemTasksListBinding,
    val callbackEditTask : (String) -> Unit
): ViewHolder(binding.root) {

    private var deleteButton: TextView? = null
    private var deleteButtonWidth = 0f
    private var deleteButtonHeight = 0f
    private lateinit var callbackDelete: () -> Unit
    private lateinit var model: OrderModel
    private var flag: Boolean = false

    private val duration = 500L
    private val textSize: Float = 4f
    private val backgroundDeleteButton = Color.parseColor("#FF0000")
    private val textColorDeleteButton = Color.parseColor("#FFFFFF")
    private val textContentDeleteButton = "Удалить"



    fun bind(model: OrderModel, callbackDeleteItem: (String) -> Unit) = with(binding){

        this@OrdersVH.model = model
        if(deleteButton == null) attachDeleteButton()
        tvOrderCustomer.text = model.selectCustomer
        tvOrderProduct.text = model.product_type
        tvOrderDate.text = model.acceptance_date
        callbackDelete = {
            callbackDeleteItem(model.id.orEmpty())
        }

        setupTranslationsX()


        flag = calculateFlag()

        setupListeners(binding.root.context)
    }


    private fun calculateFlag(): Boolean {
        return binding.vgMainContent.translationX == -deleteButtonWidth
    }

    private fun setupTranslationsX() {
        when(model.showDeleteButton) {
            true -> {
                binding.vgMainContent.translationX = -deleteButtonWidth
                deleteButton?.translationX = -deleteButtonWidth
                flag = true
            }

            false -> {
                binding.vgMainContent.translationX = 0f
                deleteButton?.translationX = 0f
                flag = false
            }
        }
    }

    private fun setupListeners(context: Context) {
        binding.root.setOnTouchListener(OnSwipeTouchListener(context, object : GestureListener(
            flag = flag,
            onEditCallback = {
                Log.d("OrdersVH", "${model.id.orEmpty()}")
                callbackEditTask(model.id.orEmpty())
            }
        ) {

            override fun onSwipeToShowDelete() {
                startAnimation {
                    createAnimation(Directions.TO_START)
                }
                model.showDeleteButton = true
            }

            override fun onSwipeHideDelete() {
                startAnimation {
                    createAnimation(Directions.TO_END)
                }
                model.showDeleteButton = false

            }

            override fun onSwipeToDismiss() {
                deleteButton!!.layoutParams.width = binding.vgMainContent.width
                deleteButton!!.requestLayout()
                startAnimation {
                    createAnimation(Directions.TO_DISMISS)
                }
                model.showDeleteButton = false
            }

        }))
    }

    private fun createAnimation(direction: Directions): List<Animator> {
        val animationList = mutableListOf<Animator>()
        var from = 0f
        var to = 0f

        when(direction) {
            Directions.TO_START -> {
                from = 0f
                to = -deleteButtonWidth
            }

            Directions.TO_END -> {
                from = -deleteButtonWidth
                to = 0f
            }

            Directions.TO_DISMISS -> {
                from = -deleteButtonWidth
                to = -binding.vgMainContent.width.toFloat()
            }
        }


        animationList.add(ObjectAnimator.ofFloat(
            binding.vgMainContent,
            "translationX",
            from,
            to
        ).apply {
            duration = this@OrdersVH.duration
        })

        animationList.add(ObjectAnimator.ofFloat(
            deleteButton!!,
            "translationX",
            from,
            to
        ).apply {
            duration = this@OrdersVH.duration

            this.addListener(object: AnimatorListener {
                override fun onAnimationStart(p0: Animator) {

                }

                override fun onAnimationEnd(p0: Animator) {
                    if (direction == Directions.TO_DISMISS) {
                        callbackDelete.invoke()
                        binding.root.removeView(deleteButton)
                    }
                }

                override fun onAnimationCancel(p0: Animator) {
                }

                override fun onAnimationRepeat(p0: Animator) {
                }

            })
        }
        )

        return animationList
    }

    private fun startAnimation(animations: () -> List<Animator>) {
        val animationsList = animations.invoke()
        val animationSet = AnimatorSet()
        animationSet.playTogether(animationsList)
        animationSet.start()
    }

    private fun attachDeleteButton() {
        deleteButton = TextView(binding.root.context).apply {
            layoutParams = LayoutParams(100.dp, LayoutParams.MATCH_PARENT)
            setBackgroundColor(backgroundDeleteButton)
            text = textContentDeleteButton
            textAlignment = TEXT_ALIGNMENT_CENTER
            gravity = Gravity.CENTER_VERTICAL
            setTextColor(textColorDeleteButton)
            textSize = this@OrdersVH.textSize.sp
        }

        saveDeleteButtonDimensions()
        binding.root.addView(deleteButton)
    }

    private fun saveDeleteButtonDimensions() {
        deleteButtonWidth = deleteButton!!.layoutParams.width.toFloat()
        deleteButtonHeight = deleteButton!!.layoutParams.height.toFloat()
    }

    private enum class Directions() {
        TO_START,
        TO_END,
        TO_DISMISS,
    }

}