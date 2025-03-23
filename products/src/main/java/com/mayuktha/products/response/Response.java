package com.mayuktha.products.response;


public record Response<T>(int statusCode,String status,String message,T data) {}
