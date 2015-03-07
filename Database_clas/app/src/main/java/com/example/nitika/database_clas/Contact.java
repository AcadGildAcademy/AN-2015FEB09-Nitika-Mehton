package com.example.nitika.database_clas;

/**
 * Created by NITIKA on 23-Feb-15.
 */
public class Contact {
    private int _id;
    private String _name;
    private String _dis;
    private String _date;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

     public Contact(String name,String dis,String date,int status)
        {
                this._name=name;
            this._dis=dis;
            this._date=date;
            this.status=status;
        }
        public Contact(int id, String name,String dis,String date,int status)
        {
            this._id=id;
            this._name=name;
            this._dis=dis;
            this._date=date;
            this.status=status;
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


    public  String getDIS()
    { return this._dis ;}

    public  void setDIS(String name)
    {this._dis=name;}

    public  String getDATE()
    { return this._date ;}

    public  void setDATE(String name)
    {this._date=name;}



}
