package com.example.nitika.main_project_design;

import android.widget.ImageView;

/**
 * Created by NITIKA on 15-Mar-15.
 */
public class Product {
    private int _id;
    private int _status;
    private String _product_name;
    private String _product_discription;
    private String _product_brand;
    private String _product_price;
    private String _product_in_stock;
    private String _product_discount;
    private ImageView _product_image;


    public int getID()
    {     return this._id;   }

    public void setID(int id)
    { this._id=id ;}
    public  int get_status()
    { return  this._status;}

    public void set_status(int status)
    {this._status=status;}

    public  String get_product_name()
    { return this._product_name ;}

    public  void set_product_name(String name)
    {this._product_name=name;}

    public  String get_product_discription()
    { return this._product_discription ;}

    public  void set_product_discription(String name)
    {this._product_discription=name;}

    public  String get_product_brand()
    { return this._product_brand ;}

    public  void set_product_brand(String name)
    {this._product_brand=name;}

    public  String get_product_price()
    { return this._product_price ;}

    public  void set_product_price(String name)
    {this._product_price=name;}

    public  String get_product_in_stock()
    { return this._product_in_stock ;}

    public  void set_product_in_stock(String name)
    {this._product_in_stock=name;}

    public  String get_product_discount()
    { return this._product_discount ;}

    public  void set_product_discount(String name)
    {this._product_discount=name;}

    public  ImageView get_product_image()
    { return this._product_image ;}

    public  void set_product_name(ImageView name)
    {this._product_image=name;}








}
