/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017. Shendy Aditya Syamsudin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package id.ac.akakom.pkm.simpeldawis.utils.avatarview;

import android.support.annotation.NonNull;

import id.ac.akakom.pkm.simpeldawis.utils.avatarview.views.AvatarView;


public abstract class ImageLoaderBase implements IImageLoader {

    private String defaultPlaceholderString;

    public ImageLoaderBase() {
        this.defaultPlaceholderString = AvatarPlaceholder.DEFAULT_PLACEHOLDER_STRING;
    }

    public ImageLoaderBase(String defaultPlaceholderString) {
        this.defaultPlaceholderString = defaultPlaceholderString;
    }

    @Override
    public void loadImage(@NonNull AvatarView avatarView, String avatarUrl, String name) {
        loadImage(avatarView, new AvatarPlaceholder(name, defaultPlaceholderString), avatarUrl);
    }

    @Override
    public void loadImage(@NonNull AvatarView avatarView, String avatarUrl, String name, int textSizePercentage) {
        loadImage(avatarView, new AvatarPlaceholder(name, textSizePercentage, defaultPlaceholderString), avatarUrl);
    }
}
