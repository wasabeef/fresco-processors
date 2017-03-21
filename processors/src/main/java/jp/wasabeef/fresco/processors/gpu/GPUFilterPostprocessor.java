package jp.wasabeef.fresco.processors.gpu;

/**
 * Copyright (C) 2017 Wasabeef
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
import com.facebook.imagepipeline.request.BasePostprocessor;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

public abstract class GPUFilterPostprocessor extends BasePostprocessor {

  private Context context;
  private GPUImageFilter filter;

  public GPUFilterPostprocessor(Context context, GPUImageFilter filter) {
    this.context = context.getApplicationContext();
    this.filter = filter;
  }

  @Override public void process(Bitmap dest, Bitmap source) {
    GPUImage gpuImage = new GPUImage(context);
    gpuImage.setImage(source);
    gpuImage.setFilter(filter);
    Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();

    super.process(dest, bitmap);
  }

  @Override public String getName() {
    return getClass().getSimpleName();
  }

  @SuppressWarnings("unchecked") public <T> T getFilter() {
    return (T) filter;
  }
}
