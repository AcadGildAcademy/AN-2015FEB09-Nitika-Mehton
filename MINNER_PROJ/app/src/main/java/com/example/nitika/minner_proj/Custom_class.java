package com.example.nitika.minner_proj;

import android.app.Activity;

/**
 * Created by NITIKA on 26-Feb-15.
 */
public class Custom_class {

    private int id;
    private String name;
    private String description;
    private String date;

    /*public Custom_class
    {
        super();
    }*/

    public Custom_class(int id, String name, String description, String price) {
        super();


        this.id = id;
        this.name = name;
        this.description = description;
        this.date = price;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return date;
    }

    public void setPrice(String price) {
        this.date = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Custom_class other = (Custom_class) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description="
                + description + ", price=" + date + "]";
    }
}


