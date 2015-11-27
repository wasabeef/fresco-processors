package jp.wasabeef.fresco.processors;

/**
 * Copyright (C) 2015 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;

public class BlurPostprocessor extends BasePostprocessor {

  private static int MAX_RADIUS = 25;
  private static int DEFAULT_DOWN_SAMPLING = 1;

  private Context context;
  private int radius;
  private int sampling;

  public BlurPostprocessor(Context context) {
    this(context, MAX_RADIUS, DEFAULT_DOWN_SAMPLING);
  }

  public BlurPostprocessor(Context context, int radius) {
    this(context, radius, DEFAULT_DOWN_SAMPLING);
  }

  public BlurPostprocessor(Context context, int radius, int sampling) {
    this.context = context.getApplicationContext();
    this.radius = radius;
    this.sampling = sampling;
  }

  @Override public void process(Bitmap dest, Bitmap source) {

    int width = source.getWidth();
    int height = source.getHeight();
    int scaledWidth = width / sampling;
    int scaledHeight = height / sampling;

    Bitmap blurredBitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);

    Canvas canvas = new Canvas(blurredBitmap);
    canvas.scale(1 / (float) sampling, 1 / (float) sampling);
    Paint paint = new Paint();
    paint.setFlags(Paint.FILTER_BITMAP_FLAG);
    canvas.drawBitmap(source, 0, 0, paint);

    RenderScript rs = RenderScript.create(context);
    Allocation input =
        Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_NONE,
            Allocation.USAGE_SCRIPT);
    Allocation output = Allocation.createTyped(rs, input.getType());
    ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

    blur.setInput(input);
    blur.setRadius(radius);
    blur.forEach(output);
    output.copyTo(blurredBitmap);

    Bitmap scaledBitmap =
        Bitmap.createScaledBitmap(blurredBitmap, dest.getWidth(), dest.getHeight(), true);
    blurredBitmap.recycle();

    rs.destroy();

    super.process(dest, scaledBitmap);
  }

  @Override public String getName() {
    return getClass().getSimpleName();
  }

  @Override public CacheKey getPostprocessorCacheKey() {
    return new SimpleCacheKey("radius=" + radius + ",sampling=" + sampling);
  }
}
