/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ztstech.vgmate.matisse.internal.utils;

import android.content.Context;
import android.graphics.Point;


import com.ztstech.vgmate.matisse.MimeType;
import com.ztstech.vgmate.matisse.filter.Filter;
import com.ztstech.vgmate.matisse.internal.entity.IncapableCause;
import com.ztstech.vgmate.matisse.internal.entity.Item;

import java.util.HashSet;
import java.util.Set;

public class GifSizeFilter extends Filter {

    private int mMaxSize;
    //高宽比
    float mScale;

    public GifSizeFilter(int maxSizeInBytes,float mScale) {
        this.mMaxSize = maxSizeInBytes;
        this.mScale = mScale;
    }

    @Override
    public Set<MimeType> constraintTypes() {
        return new HashSet<MimeType>() {{
            add(MimeType.GIF);
        }};
    }

    @Override
    public IncapableCause filter(Context context, Item item) {
        Point size = PhotoMetadataUtils.getBitmapBound(context.getContentResolver(), item.getContentUri());
        if (item.size > mMaxSize) {
            return new IncapableCause(IncapableCause.DIALOG,String.valueOf(PhotoMetadataUtils.getSizeInMB(mMaxSize)));
        }
        if((size.x*1.0/size.y)>=mScale){
            return new IncapableCause(IncapableCause.DIALOG, "宽高比不能大于4");
        }
        return null;
    }

}
