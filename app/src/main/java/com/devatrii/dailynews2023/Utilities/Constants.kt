package com.devatrii.dailynews.utils

const val POST_PER_PAGE = 7 // More posts more loading time
const val WEBSITE_URL = "https://startuppakistan.com.pk" // Replace Url Here
const val BASE_URL = "$WEBSITE_URL/wp-json/wp/v2"
const val POSTS_URL = "$BASE_URL/posts?_embed&per_page=$POST_PER_PAGE&"
const val CATEGORIES_URL = "$BASE_URL/categories/"
const val DEFAULT_USER_PIC = "https://secure.gravatar.com/avatar/5bad2ef5b930ef3257f7ad2d43ccadab?s=96&d=mm&r=g"