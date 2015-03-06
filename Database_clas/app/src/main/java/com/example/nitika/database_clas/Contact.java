package com.example.nitika.database_clas;

/**
 * Created by NITIKA on 23-Feb-15.
 */
public class Contact {
    private int _id;
    private String _name;
    private String _phone_no;
    private String _dis;
    private String _date;
    private int _imgg;
     public Contact(String name, String Ph_no,String dis,String date,int img)
        {
                this._name=name;
                this._phone_no=Ph_no;
            this._dis=dis;
            this._date=date;
            this._imgg=img;
        }
        public Contact(int id, String name, String ph_no,String dis,String date,int img)
        {
            this._id=id;
            this._name=name;
            this._phone_no=ph_no;
            this._dis=dis;
            this._date=date;
            this._imgg=img;
        }
public Contact()
{}
    public int getID()
              {     return this._id;   }

    public void setID(int id)
                { this._id=id ;}

    public  String getNAME()
                { return this._name ;}

    public  void setNAME(String name)
                {this._name=name;}

    public  String getPHONE()
    { return this._phone_no ;}

    public  void setPHONE(String PH)
    {this._phone_no=PH ;}

    public  String getDIS()
    { return this._dis ;}

    public  void setDIS(String name)
    {this._dis=name;}

    public  String getDATE()
    { return this._date ;}

    public  void setDATE(String name)
    {this._date=name;}

    public int getIMGG()
    {     return this._imgg;   }

    public void setIMGG(int id)
    { this._id=_imgg ;}

}
