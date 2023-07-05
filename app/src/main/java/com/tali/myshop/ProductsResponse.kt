package com.tali.myshop

data class ProductsResponse(
    var product:List<Products>,
    var total:Int,
    var skip:Int,
    var limit:Int
)
