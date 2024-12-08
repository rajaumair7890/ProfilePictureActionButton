package com.codingwithumair.social.ui.sharedComponents

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ImageIconButton(
	imageContent: @Composable (BoxScope.() -> Unit),
	onImageClick: () -> Unit,
	iconContent: @Composable (BoxScope.() -> Unit),
	onIconClick: () -> Unit,
	buttonColor: Color = Color(0xFFD0E0E7),
	buttonHeight: Dp = 50.dp,
	modifier: Modifier = Modifier,
){

	Box(
		modifier = modifier
			.height(buttonHeight)
			.aspectRatio(1.4f)
			.drawBehind {

				val halfHeight = size.height / 2f

				drawCircle(
					color = buttonColor,
					center = Offset(halfHeight, center.y),
					radius = halfHeight
				)

				drawRoundRect(
					topLeft = Offset(size.width / 4f, center.y),
					color = buttonColor,
					cornerRadius = CornerRadius(size.width/1.5f, size.height)
				)

				val path = Path().apply {
					val startX = size.width - ((size.width / 24f) * 8f)
					val startY = size.height - ((size.height / 4f) * 3f)
					val endX = size.width - ((size.width / 16f) * 2f )
					val endY = halfHeight + 0.5f
					val controlX = size.width - ((size.width / 8f) * 2f )
					val controlY = size.height - ((size.height / 8f) * 4f)
					moveTo(startX, startY)
					quadraticTo(
						controlX, controlY,
						endX, endY
					)
					lineTo(startX, endY)
					close()
				}

				drawPath(
					path = path,
					color = buttonColor,
					style = Fill
				)
			}
	){
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.size(buttonHeight)
				.pointerInput(null){
					detectTapGestures(
						onTap = { onImageClick() },
					)
				}
		){
			Box(
				modifier = Modifier
					.size(buttonHeight - 10.dp)
					.clip(CircleShape)
			){
				imageContent()
			}
		}

		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.size(buttonHeight / 2.2f)
				.align(Alignment.BottomEnd)
				.padding(bottom = buttonHeight / 25, end = buttonHeight / 25)
				.pointerInput(null){
					detectTapGestures(
						onTap = { onIconClick() }
					)
				}
		) {
			iconContent()
		}
	}
}

//Usage Example
@Preview
@Composable
fun ImageIconButtonPreview(){
	Box(
		contentAlignment = Alignment.Center,
		modifier = Modifier.fillMaxSize()
	) {
		ImageIconButton(
			imageContent = {
				GlideImage(
					imageModel = { "https://avatars.githubusercontent.com/u/113193169?v=4" }
				)
			},
			iconContent = {
				Icon(
					imageVector = Icons.Default.Menu,
					contentDescription = null,
					tint = Color.Black,
					modifier = Modifier.matchParentSize()
				)
			},
			onImageClick = { Log.d("ImageIconButton", "Image Clicked") },
			onIconClick = { Log.d("ImageIconButton", "Icon Clicked") },
			buttonHeight = 100.dp
		)
	}
}
